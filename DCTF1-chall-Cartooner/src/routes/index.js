import homepageRoutes from "./homepage";
import adminRoutes from "./admin";
import { cookieToUser } from "../middleware/cookieAuth";
import authRoutes from "./login";
import { dobiPoste } from "../controllers";

export default (app) => {
    //Transfer from last flash to res locals

    app.use("/admin", cookieToUser, adminRoutes);
    app.use("/home", cookieToUser, homepageRoutes);
    app.use("/", authRoutes);
    app.use("/", (req, res) => {
        res.redirect("/home");
    });
    app.use((err, req, res, next) => {
        console.error(err);
        dobiPoste((err, posts) => {
            return res.render("home", {
                posts,
                error_messages: [{
                    name: "500",
                    message: "Internal server error",
                }, ],
            });
        });
    });
};