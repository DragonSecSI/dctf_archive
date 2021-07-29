import mysql from 'mysql2'
import config from '../config'

const { pwn_uri } = config;

export const dbPwn = mysql.createConnection({ uri: pwn_uri, multipleStatements: true, });

export const connectDB = (callback, nTries) => {
    dbPwn.connect((err)=>{
        if(err && nTries > 0){
            setTimeout(connectDB, 2*1000, callback, nTries-1)
            return;
        }
        callback(err)
    });
};