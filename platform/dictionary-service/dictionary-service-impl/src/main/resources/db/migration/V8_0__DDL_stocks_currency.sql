ALTER TABLE STOCK_DICT ADD COLUMN CURRENCY_ID VARCHAR(36) REFERENCES CURRENCY_DICT (ID);
UPDATE STOCK_DICT SET CURRENCY_ID = 'fb0d4f8d-7e9f-443e-b0f0-4a446f793d1c'; --USD
ALTER TABLE STOCK_DICT ALTER COLUMN CURRENCY_ID SET NOT NULL;