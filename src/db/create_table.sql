DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `user_name` varchar(40) NOT NULL,
                          `password` varchar(255) NOT NULL,
                          `role` int(4) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_user` VALUES ('1', '测试', '123456', '21');
INSERT INTO `t_user` VALUES ('2', 'admin', '1qaz1qaz', '1');

