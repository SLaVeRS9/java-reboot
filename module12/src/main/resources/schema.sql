CREATE TABLE IF NOT EXISTS users (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(50) NOT NULL,
    age integer NOT NULL,
    password varchar(100) NOT NULL,
    user_role varchar(20) NOT NULL
);