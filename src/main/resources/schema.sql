CREATE TABLE  role (
                         role_id serial NOT NULL,
                         role varchar(255) default NULL,
                         PRIMARY KEY(role_id)
);

DROP TABLE IF EXISTS user_table;
CREATE TABLE  user_table (
                         id serial not null,
                         firstname varchar(255) NOT NULL,
                         lastname varchar(255) NOT NULL,
                         email varchar(255) NOT NULL,
                         password varchar(255) NOT NULL,
                         active int default NULL,
                         PRIMARY KEY  (id)
);

DROP TABLE IF EXISTS user_role;
CREATE TABLE  user_role (
                              user_id int NOT NULL,
                              role_id int NOT NULL,
                              PRIMARY KEY  (user_id,role_id),
                              FOREIGN KEY (user_id) REFERENCES user_table (id),
                              FOREIGN KEY (role_id) REFERENCES role (role_id)
);

DROP TABLE IF EXISTS persistent_logins;
CREATE TABLE  persistent_logins(
                                      username varchar(64) NOT NULL,
                                      series varchar(64) NOT NULL,
                                      token varchar(64) NOT NULL,
                                      last_used timestamp NOT NULL default 'now'::timestamp,
                                      PRIMARY KEY  (series)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO dauphine;