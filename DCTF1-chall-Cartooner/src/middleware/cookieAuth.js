import jwt from "jsonwebtoken";
import config from "../config";

export const cookieToUser = (req, res, next) => {
    const userCookie = req.cookies.user;
    jwt.verify(userCookie, config.pwn_jwt_secret, function(err, decoded) {
        if (err || !decoded) {
            return res.redirect("/login");
        }
        if (!decoded.name || !decoded.hasOwnProperty("name")) {
            req.flash("error_messages", [{
                message: "Cannot read user data",
            }, ]);
            return res.render("login", { showLoginRedirect });
        }
        req.user = decoded;
        res.locals.user = req.user;
        res.locals.isAdmin = (decoded.name === "admin");
        next();
    });
};