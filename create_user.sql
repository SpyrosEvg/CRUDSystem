CREATE USER 'CrudSystem'@'localhost' IDENTIFIED BY 'CrudSystem';

GRANT ALL PRIVILEGES ON * . * TO 'CrudSystem'@'localhost';

ALTER USER 'CrudSystem'@'localhost' IDENTIFIED WITH mysql_native_password BY 'CrudSystem';