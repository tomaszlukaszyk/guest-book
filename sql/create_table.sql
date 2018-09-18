DROP TABLE entries;
CREATE TABLE entries(
    id SERIAL PRIMARY KEY NOT NULL,
    message TEXT NOT NULL,
    name VARCHAR(50) NOT NULL,
    post_date TIMESTAMP NOT NULL
);
ALTER TABLE entries OWNER TO guestbookadmin;