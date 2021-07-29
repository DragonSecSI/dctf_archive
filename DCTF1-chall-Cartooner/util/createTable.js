const mysql = require("mysql2/promise");
const path = require("path");
const fs = require("fs");

require("dotenv").config({
    path: path.join(__dirname, "..", ".env"),
});

const uri_main = process.env.MAIN_URI;

const localFile = (f) => {
    return path.join(__dirname, f);
};

const execSqlFile = (connection, relpth) =>
    new Promise(async(resolve, reject) => {
        const filePath = localFile(relpth);
        console.log("Executing sql from " + filePath);
        fs.readFile(filePath, "utf8", async(err, data) => {
            if (err) return reject(err);
            try {
                await connection.query("START TRANSACTION");
                console.log("Beginning transaction.");
                await connection.query(data);
                await connection.commit();
                console.log("Commit.");
                return resolve(true);
            } catch (e) {
                console.log("Error.");
                await connection.query("ROLLBACK");
                console.log("Rollback.");
                return reject(e);
            }
        });
    });

const setup = (conn) =>
    execSqlFile(conn, "create_tables.sql")
    .then(() => execSqlFile(conn, "fill_users.sql"))
    .then(() => execSqlFile(conn, "fill_posts.sql"));

console.log(uri_main);
mysql
    .createConnection({
        uri: uri_main,
        multipleStatements: true,
    })
    .then((conn) => {
        return conn.connect().then(() => {
            console.log("Connected!");
            return setup(conn).finally(() => {
                conn.destroy();
                return Promise.resolve();
            });
        });
    })
    .catch((err) => console.error(err));