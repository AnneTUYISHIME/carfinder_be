ALTER TABLE cars
DROP
CONSTRAINT fk_cars_on_user;

ALTER TABLE reviews
DROP
CONSTRAINT fk_reviews_on_user;

ALTER TABLE reviews
    ADD comment VARCHAR(255);

ALTER TABLE reviews
    ADD owner_id BIGINT;

ALTER TABLE reviews
    ADD rating DOUBLE PRECISION;

ALTER TABLE cars
    ADD condition VARCHAR(255);

ALTER TABLE cars
    ALTER COLUMN condition SET NOT NULL;

ALTER TABLE reviews
    ALTER COLUMN owner_id SET NOT NULL;

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES app_users (id);

ALTER TABLE cars
DROP
COLUMN user_id;

ALTER TABLE reviews
DROP
COLUMN user_id;

ALTER TABLE reviews
    ALTER COLUMN car_id SET NOT NULL;