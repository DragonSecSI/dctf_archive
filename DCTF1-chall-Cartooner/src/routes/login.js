import express from "express";
import Joi from "joi";
import expressJoi from "express-joi-validation";
import jwt from "jsonwebtoken";
import config from "../config";

const validator = expressJoi.createValidator({
    passError: true,
});
const router = express.Router();

const joiName = Joi.string()
    .alphanum()
    .required()
    .min(4)
    .max(14)
    .not("admin")
    .messages({
        "any.empty": "Name should not be empty",
        "any.required": "Name is missing",
        "any.invalid": "Name already taken",
        "string.min": "Name should be at least {#limit} characters long",
        "string.max": "Name should be at most {#limit} characters long",
        "string.alphanum": "Name contains invalid characters",
    });

const loginQuery = Joi.object().keys({
    username: joiName,
});

const loginRoute = router.route("/login");

loginRoute.get((req, res) => {
    if (req.user) {
        return res.redirect("/home");
    }
    return res.render("login", { hideLoginRedirect: true });
});

loginRoute.post(
    validator.body(loginQuery),

    (req, res) => {
        const { username: name } = req.body;
        const cookie_val = jwt.sign({
                name: name,
            },
            config.pwn_jwt_secret
            /*  {
                      algorithm: "none",
                  } */
        );
        return res
            .cookie("user", cookie_val, {
                maxAge: 24 * 60 * 60 * 1000,
            })
            .redirect("/home");
    },
    (err, req, res, next) => {
        if (err && err.error && err.error.isJoi) {
            const message = err.error.details[0].message;
            return res.render("login", { error_messages: [{ message }] });
        }
        return res.render("login", { error_messages: [{ message: "An error occured!" }] });
    }
);

router.post("/logout", (req, res) => {
    return res.cookie("user", "", { expires: new Date() }).redirect("/login");
});

export default router;