import { dbPwn } from "../db_connection";

export function dobiPoste(query, callback) {
    if (!callback) {
        [query, callback] = [undefined, query];
    }

    const formattedQuery = (query ?
        "SELECT p.text AS description,\
        p.likes,\
        date_format(p.dateposted,'%e. %c. %Y') AS time,\
        p.img,\
        u.username AS user,\
        u.profilepic AS profilePic, \
        u.password AS secret\
            FROM posts p\
            INNER JOIN users u ON (p.username = u.username)\
            WHERE p.text LIKE '%" +
        query +
        "%'\
            ORDER BY p.dateposted DESC;" :
        "SELECT p.text AS description,\
        p.likes,\
        date_format(p.dateposted,'%e. %c. %Y') AS time,\
        p.img,\
        u.username AS user,\
        u.profilepic AS profilePic, \
        u.password AS secret\
            FROM posts p\
            INNER JOIN users u ON (p.username = u.username)\
            ORDER BY p.dateposted DESC;");

    dbPwn.query(formattedQuery, (err, data) =>
        callback(err, data, formattedQuery)
    );
}