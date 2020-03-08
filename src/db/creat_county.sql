DROP TABLE IF EXISTS `county`;
CREATE TABLE `county` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(40) NOT NULL,
                            `weather_id` varchar(40) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

