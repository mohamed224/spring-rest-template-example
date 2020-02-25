//drop tables if she exist
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS purchase_order;

//create product table
CREATE TABLE product (id LONG AUTO_INCREMENT PRIMARY KEY ,
                      name VARCHAR(25) NOT NULL ,
                      qty_stock INT NOT NULL ,
                      price DOUBLE NOT NULL
);

//create payment table
CREATE TABLE payment(id LONG AUTO_INCREMENT PRIMARY KEY ,
                     order_id INT NOT NULL ,
                     amount DOUBLE NOT NULL ,
                     card_number LONG NOT NULL
);

//create purchaseorder table
CREATE TABLE purchase_order(id LONG AUTO_INCREMENT PRIMARY KEY,
                            product_id LONG NOT NULL ,
                            order_date DATE ,
                            quantity INTEGER NOT NULL ,
                            paid_order BOOLEAN NOT NULL );

