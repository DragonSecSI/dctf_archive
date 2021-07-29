CREATE DATABASE `cartoonerdb`;
USE `cartoonerdb`;


CREATE USER 'siteadmin'@'%' IDENTIFIED BY 'pwnuserpass123fd01602018b08e149a355d23cae54541';

REVOKE ALL PRIVILEGES ON *.* FROM `siteadmin`@'%';
GRANT SELECT ON `cartoonerdb`.* TO `siteadmin`@'%';

FLUSH PRIVILEGES;

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



INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES ('johnnyb9','/public/images/users/johnnyb9.png','YVcxaVpXRjFkR2xtZFd3Mk9RPT0=');
INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES ('mcstrela','/public/images/users/mcstrela.png','YzNBek0yUmZjM0F6TTJRPQ==');
INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES ('big_boy3','/public/images/users/big_boy3.png','YTNJMGRqUjBZVWRoV20wMFoyRT0=');
INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES ('2moto','/public/images/users/2moto.png','YVV3eGEyVTNhR1Z0UTJnd2JtdDU=');
INSERT INTO `users` (`username`,`profilepic`,`password`) VALUES ('admin','/public/images/users/admin.png','ZENURnt3aDBhX000TTQhX2E1NjM1ZDkwYn0=');


INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES ('1','Hey, Baby! Anybody ever tell you I have beautiful eyes?','60','johnnyb9','2021-03-15 02:34:39','/public/images/library/JohnnyBravo1.png');
INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES ('2','A name so nice, you''ll say it twice','92','2moto','2021-04-16 22:08:26','/public/images/library/MotoMoto.png');
INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES ('3','Whether you think you or you think you can''t, you''re right.','30','big_boy3','2021-04-07 01:11:20','/public/images/library/BossBaby.png');
INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES ('4','It''s a beautiful day. But not as beautiful as me.','55','johnnyb9','2021-03-19 16:41:42','/public/images/library/JohnnyBravo2.jpg');
INSERT  INTO `posts` (`id`,`text`,`likes`,`username`,`dateposted`,`img`) VALUES ('5','Focus. Speed. I am speed. One winner, forty-two losers.','60','mcstrela','2021-04-28 09:51:52','/public/images/library/LightningMcQueen.jpg');