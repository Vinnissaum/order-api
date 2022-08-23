INSERT INTO tb_item (DESCRIPTION, COST, TYPE) VALUES ('Apartment 3 rooms', 500000.00, 'P');
INSERT INTO tb_item (DESCRIPTION, COST, TYPE) VALUES ('Sanitation', 1000.00, 'S');

INSERT INTO tb_order (NUMBER, DATE, percentage_Discount, total_Value) VALUES (25, NOW(), 0.2, 200.00);
INSERT INTO tb_order (NUMBER, DATE, percentage_Discount, total_Value) VALUES (100, NOW(), 0.5, 200.00);

INSERT INTO tb_order_item (item_Id, order_Id, QUANTITY) VALUES (1, 1, 5);
INSERT INTO tb_order_item (item_Id, order_Id, QUANTITY) VALUES (2, 1, 10);