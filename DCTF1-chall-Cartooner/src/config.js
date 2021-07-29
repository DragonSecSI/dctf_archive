const { NODE_ENV } = process.env;

if (NODE_ENV === "development") {
    require("dotenv").config();
}

const keys = {
    PWN_URI: "pwn_uri",
    PORT: "port",
    PWN_JWT_SECRET: "pwn_jwt_secret",
};
const read = {
    pwn_uri: undefined,
    port: 5001,
    pwn_jwt_secret: undefined,
};

const errors = [];
Object.entries(keys).forEach(([k, v]) => {
    if (!(k in process.env) && !read[v])
        return errors.push(new Error(`Missing env value for: ${k}`));
    else
        read[v] = process.env[k];
});
if (errors.length) {
    throw new Error(errors.join("\n"));
}

read.port = parseInt(read.port) || 5001;
export default read;