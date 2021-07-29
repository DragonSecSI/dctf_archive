DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE IF NOT EXISTS `users`
(
    `username` varchar(255) NOT NULL,
    `profilepic` varchar(45) DEFAULT NULL,
    `password` varchar(350) DEFAULT NULL,
    PRIMARY KEY(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS  `posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL DEFAULT '',
  `likes` int(11) NOT NULL DEFAULT 0,
  `username` varchar(45) DEFAULT NULL,
  `dateposted` datetime NOT NULL,
  `img` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
