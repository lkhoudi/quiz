DROP TABLE IF EXISTS user_table;
CREATE TABLE  user_table (
                         id serial not null,
                         username varchar(255) NOT NULL,
                         password varchar(255) NOT NULL,
                         PRIMARY KEY  (id)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO dauphine;