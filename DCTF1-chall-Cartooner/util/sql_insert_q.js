const { NODE_ENV } = process.env;
if (NODE_ENV === "development")
    require("dotenv").config();
if (!process.env.PWN_FLAG)
    throw new Error("No flag in env!")
const users = [
    ["johnnyb9", "imbeautiful69"],
    ["mcstrela", "sp33d_sp33d"],
    ["big_boy3", "kr4v4taGaZm4ga"],
    ["2moto", "iL1ke7hemCh0nky"],
    ["admin", process.env.PWN_FLAG],
];

const picFromLib = (s) => {
    return `/public/images/library/${s}`;
};

const profilePic = (user) => {
    return `/public/images/users/${user}.png`;
};

const likesRange = [30, 100];
const dateRange = [new Date(2021, 1, 13), new Date()];

function randomLike(start, end) {
    return Math.floor(Math.random() * (end - start)) + start;
}

function randomDate() {
    return new Date(
            dateRange[0].getTime() +
            Math.random() * (dateRange[1].getTime() - dateRange[0].getTime())
        )
        .toISOString()
        .slice(0, 19)
        .replace("T", " ");
}

randomDate(new Date(2012, 0, 1), new Date());

const posts = [{
        user: "johnnyb9",
        img: picFromLib("JohnnyBravo1.png"),
        description: "Hey, Baby! Anybody ever tell you I have beautiful eyes?",
        likes: 19,
    },
    {
        user: "2moto",
        img: picFromLib("MotoMoto.png"),
        description: "A name so nice, you'll say it twice",
        likes: 84,
    },
    {
        user: "big_boy3",
        img: picFromLib("BossBaby.png"),
        description: "Whether you think you or you think you can't, you're right.",
        likes: 26,
    },
    {
        user: "johnnyb9",
        img: picFromLib("JohnnyBravo2.jpg"),
        description: "It's a beautiful day. But not as beautiful as me.",
        likes: 4,
    },
    {
        user: "mcstrela",
        img: picFromLib("LightningMcQueen.jpg"),
        description: "Focus. Speed. I am speed. One winner, forty-two losers.",
        likes: 64,
    },
].map((v, i) => {
    v.id = i + 1;
    v.profilePic = profilePic(v.user);
    v.likes = randomLike(30, 100);
    v.time = randomDate();
    return v;
});

const iterate_atob = (s, n) => {
    const r = atob(s);
    if (r.length > n) return r;
    return iterate_atob(r, n);
};

const atob = (s) => {
    return Buffer.from(s, "utf-8").toString("base64");
};

const joinQuoted = (a, sep, q) => {
    const qte = q.toString();
    return (
        qte + a.map(v => v.toString().replace(new RegExp(qte, 'gi'), qte + qte)).join(qte + sep.toString() + qte) + qte
    );
};

/* const toRow = (l) => joinQuoted(l, ";", '"');
    /// CSV
    console.log(toRow(["username", "profilePic", "password"]));
    users.forEach(([ime, pass]) => {
        const hashed = iterate_atob(pass, 20);
        console.log(toRow([ime, profilePic(ime), hashed]));
    }); */

queries = [];

users.forEach(([ime, pass]) => {
    const hashed = iterate_atob(pass, 20);
    const data = joinQuoted([ime, profilePic(ime), hashed], ",", "'");
    const p =
        "INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES (" +
        data +
        ");";
    queries.push(p);
    console.log(p);
});

posts.forEach(({ description, img, user, likes, id, time }) => {
    const data = joinQuoted([id, description, likes, user, time, img], ",", "'");
    const p =
        "INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES (" +
        data +
        ");";
    queries.push(p);
    console.log(p);
});

module.exports = queries;