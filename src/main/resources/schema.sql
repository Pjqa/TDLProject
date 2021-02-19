DROP TABLE `items`;
DROP TABLE `todolist`;

CREATE TABLE IF NOT EXISTS `todolist` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`completed` BOOLEAN,
	`todolist_id` INT NOT NULL,
	CONSTRAINT `fk_todolist_id` FOREIGN KEY (`todolist_id`)
	REFERENCES `todolist` (`id`)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

