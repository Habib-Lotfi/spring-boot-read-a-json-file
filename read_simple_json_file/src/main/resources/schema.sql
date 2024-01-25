DROP TABLE IF EXISTS Post;

CREATE TABLE Post (
                      id varchar NOT NULL PRIMARY KEY,
                      category varchar(255) NOT NULL,
                      date date NOT NULL,
                      cost DECIMAL(10, 2),
                      version INT
);