alter table FUND add column created_date timestamp;
update FUND set created_date = now();
ALTER TABLE FUND ALTER COLUMN created_date SET NOT NULL;