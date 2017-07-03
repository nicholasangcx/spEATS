mysql -h speatsdb.clw2p9tkzgyv.ap-southeast-1.rds.amazonaws.com -P 3306 -u speats -p

CREATE TABLE `bbe`.`bbe_users` (
`username` VARCHAR(20),
`password` VARCHAR(40),
PRIMARY KEY (`username`));

INSERT into `bbe`.`bbe_users` (username, password) VALUES ('admin', '5f4dcc3b5aa765d61d8327deb882cf99');