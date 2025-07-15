-- Safely drop constraints if they exist
DO $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM information_schema.table_constraints 
        WHERE constraint_name = 'fk_cars_on_user'
    ) THEN
ALTER TABLE cars DROP CONSTRAINT fk_cars_on_user;
END IF;

    IF EXISTS (
        SELECT 1 FROM information_schema.table_constraints 
        WHERE constraint_name = 'fk_reviews_on_user'
    ) THEN
ALTER TABLE reviews DROP CONSTRAINT fk_reviews_on_user;
END IF;
END$$;

-- Safely add new columns
ALTER TABLE reviews ADD COLUMN IF NOT EXISTS comment VARCHAR(255);
ALTER TABLE reviews ADD COLUMN IF NOT EXISTS owner_id BIGINT;
ALTER TABLE reviews ADD COLUMN IF NOT EXISTS rating DOUBLE PRECISION;
ALTER TABLE cars ADD COLUMN IF NOT EXISTS condition VARCHAR(255);

-- Safely modify constraints and column definitions
ALTER TABLE cars ALTER COLUMN condition SET NOT NULL;
ALTER TABLE reviews ALTER COLUMN owner_id SET NOT NULL;

-- Add foreign key constraint (safe if not already exists)
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.table_constraints 
        WHERE constraint_name = 'FK_REVIEWS_ON_OWNER'
    ) THEN
ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES app_users (id);
END IF;
END$$;

-- Drop old columns safely
ALTER TABLE cars DROP COLUMN IF EXISTS user_id;
ALTER TABLE reviews DROP COLUMN IF EXISTS user_id;

-- Ensure car_id is not null
ALTER TABLE reviews ALTER COLUMN car_id SET NOT NULL;
