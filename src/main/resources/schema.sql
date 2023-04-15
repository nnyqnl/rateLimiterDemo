CREATE TABLE `sys_user` (
  `id` int NOT NULL,
  `username` varchar(30) DEFAULT NOT NULL,
  `password` varchar(30) DEFAULT NOT NULL,
  `token` varchar(64) DEFAULT NOT NULL,
  PRIMARY KEY (`id`)
);