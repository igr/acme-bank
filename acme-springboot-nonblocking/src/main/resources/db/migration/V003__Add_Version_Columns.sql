-- Add version columns for optimistic locking
ALTER TABLE bank ADD COLUMN version bigint NOT NULL DEFAULT 0;
ALTER TABLE customer ADD COLUMN version bigint NOT NULL DEFAULT 0;
ALTER TABLE account ADD COLUMN version bigint NOT NULL DEFAULT 0;
ALTER TABLE transaction ADD COLUMN version bigint NOT NULL DEFAULT 0;

-- Update versions of existing data
UPDATE bank SET version = 1;
UPDATE customer SET version = 1;
UPDATE account SET version = 1;
UPDATE transaction SET version = 1;
