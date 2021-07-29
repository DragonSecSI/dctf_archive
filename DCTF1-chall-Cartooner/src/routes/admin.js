import express from "express";
import { dobiPoste } from "../controllers";
const router = express.Router();

const disallowed = [
    "union",
    "if",
    "by",
    "drop",
    "delete",
    `[${"-;|@&^*<>".split("").map(v=>"\\"+v).join("")}]`
];
const re = new RegExp(`(${disallowed.join("|")})`, "gi");

console.log(re);

const getWAFerror = (d) => {
    if (d && d.match(re))
        return {
            name: "400",
            message: `Error: Input error.`,
        };
    return false;
};

router.post("/posts", (req, res) => {
    console.log(req.body);
    const { search: query, sql, debug } = req.body;
    const dbgFlag = parseInt(debug) == 1 || false;
    if (sql !== "mysql") {
        // Error
        dobiPoste((err, posts) => {
            if (err)
                return res.render("home", {
                    error_messages: [{
                        name: "500",
                        message: "Internal server error",
                    }, ],
                    posts,
                });
            return res.render("home", {
                error_messages: [{
                    name: "400",
                    message: "Wrong SQL version",
                }, ],
                posts,
            });
        });
        return;
    }

    const wafRet = getWAFerror(query);
    if (wafRet) {
        dobiPoste((err, posts) => {
            if (err)
                return res.render("home", {
                    error_messages: [{
                        name: "500",
                        message: "Internal server error",
                    }, ],
                    posts,
                });
            if (dbgFlag) res.locals.dbgtext = "(Sql-DEinjector-Error) Query matches " + re;
            return res.render("home", {
                error_messages: [{
                    name: "400",
                    message: "Input error",
                }, ],
                posts,
            });
        });
        return;
    }

    dobiPoste(query, (err, posts, executedQuery) => {
        if (err) {
            dobiPoste((err, posts) => {
                console.log("ERROR on " + executedQuery);
                if (dbgFlag) res.locals.dbgtext = "(ERROR) " + executedQuery;
                return res.render("home", {
                    error_messages: [{
                        name: "500",
                        message: "Internal server error",
                    }, ],
                    posts,
                    pagetext: "Results for " + query,
                });
            });
            return;
        }
        if (dbgFlag) res.locals.dbgtext = executedQuery;
        return res.render("home", { posts, pagetext: "Results for " + query });
    });
});

export default router;