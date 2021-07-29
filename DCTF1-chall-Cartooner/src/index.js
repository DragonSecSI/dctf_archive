import express from "express";
import cookieParser from "cookie-parser";
import morgan from "morgan";
import helmet from "helmet";
import config from "./config";
import configureRoutes from "./routes";
import { connectDB } from "./db_connection";

const app = express();
app.use(
    helmet({
        contentSecurityPolicy: {
            reportOnly: true
        },
    })
);
app.use(morgan("combined"));
app.use(express.urlencoded({ extended: false }));
app.use("/public", express.static("public"));
/* app.use(
    session({
        secret: "falseljhkljgh",
        resave: false,
        saveUninitialized: true,
        cookie: { maxAge: 60000, secure: false },
    })
);
app.use(flash()); */
app.use(express.json());
app.use(cookieParser());
app.set("view engine", "pug");

configureRoutes(app);

connectDB((connectError) => {
    if (connectError) {
        console.error(connectError);
        return;
    }

    app.listen(config.port, () => {
        console.log("Oh, mama!");
        console.log(`http://localhost:${config.port}`);
    });
}, 12);