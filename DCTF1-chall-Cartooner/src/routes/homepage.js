import express from "express";
import { dbPwn } from "../db_connection";
import { dobiPoste } from "../controllers";
const router = express.Router();

router.get("/", (req, res) => {
    dobiPoste((err, posts) => {
        if (err)
            return res.render("home", {
                error_messages: [{
                    message: "Database error occured!",
                }, ],
            });
        return res.render("home", { posts });
    });
});

export default router;