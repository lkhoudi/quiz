CREATE DATABASE quiz /*!40100 DEFAULT CHARACTER SET utf8 */;

DROP TABLE IF EXISTS role;
CREATE TABLE  role (
                         role_id int (11) NOT NULL auto_increment,
                         role varchar(255) default NULL,
                         PRIMARY KEY  (role_id)
);

DROP TABLE IF EXISTS user_table;
CREATE TABLE  user_table (
                         id int(11) NOT NULL auto_increment,
                         firstname varchar(255) NOT NULL,
                         lastname varchar(255) NOT NULL,
                         email varchar(255) NOT NULL,
                         password varchar(255) NOT NULL,
                         active int(11) default NULL,
                         PRIMARY KEY  (id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE  user_role (
                              user_id int(11) NOT NULL,
                              role_id int(11) NOT NULL,
                              PRIMARY KEY  (user_id,role_id),
                              KEY user_role_key (role_id),
                              FOREIGN KEY (user_id) REFERENCES user_table (id),
                              FOREIGN KEY (role_id) REFERENCES role (role_id)
);

DROP TABLE IF EXISTS persistent_logins;
CREATE TABLE  persistent_logins(
                                      sername varchar(64) NOT NULL,
                                      series varchar(64) NOT NULL,
                                      token varchar(64) NOT NULL,
                                      last_used timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                      PRIMARY KEY  (series)
);

INSERT INTO role VALUES (1,'ADMIN');