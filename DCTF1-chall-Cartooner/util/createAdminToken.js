const jwt = require("jsonwebtoken");
require("dotenv").config();
const secret = process.env.PWN_JWT_SECRET;
const printout = jwt.sign({
        name: "admin",
    },
    secret, /* { algorithm: "none" } */
);

console.log(printout);