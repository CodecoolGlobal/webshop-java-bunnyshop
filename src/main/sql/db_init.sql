DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS order_line CASCADE;
DROP TABLE IF EXISTS order_info CASCADE;



CREATE TABLE Product(
                        id SERIAL,
                        name VARCHAR(255),
                        default_price FLOAT,
                        currency_string VARCHAR (7),
                        description VARCHAR (255),
                        product_category_id INT,
                        supplier_id INT,
                        PRIMARY KEY (id)
);

CREATE TABLE Product_Category(
                                 id SERIAL,
                                 name VARCHAR(255),
                                 department VARCHAR (255),
                                 description VARCHAR (255),
                                 PRIMARY KEY (id)
);

CREATE TABLE Supplier(
                         id SERIAL,
                         name VARCHAR(255),
                         description VARCHAR (255),
                         PRIMARY KEY (id)
);

CREATE TABLE Order_Line(
                          id SERIAL,
                          number_of_products INT,
                          name VARCHAR(255),
                          description VARCHAR(255),
                          product_id INT,
                          order_info_id INT,
                          PRIMARY KEY (id)
);

CREATE TABLE Order_Info(
                           id SERIAL,
                           name VARCHAR(255),
                           description VARCHAR (255),
                           total_sum INT,
                           currency VARCHAR (255),
                           contact_info_id INT,
                           PRIMARY KEY (id)
);

CREATE TABLE Contact_Info(
                             id SERIAL,
                             name VARCHAR(255),
                             email VARCHAR (255),
                             phone_number VARCHAR (255),
                             billing_address VARCHAR (255),
                             shipping_address VARCHAR (255),
                             order_info_id INT,
                             PRIMARY KEY (id)
);

ALTER TABLE Order_Info
    ADD CONSTRAINT fk_contact_info_id FOREIGN KEY (contact_info_id) REFERENCES contact_info(id);

ALTER TABLE Product
    ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

ALTER TABLE Product
    ADD CONSTRAINT fk_product_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category(id);

ALTER TABLE Order_Line
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product(id);

ALTER TABLE Order_Line
    ADD CONSTRAINT fk_order_info_id FOREIGN KEY (order_info_id) REFERENCES order_info(id);

ALTER TABLE Contact_Info
    ADD CONSTRAINT fk_order_info_id FOREIGN KEY (order_info_id) REFERENCES order_info(id);


INSERT INTO product_category (name, department, description) VALUES ('Alaska', 'animal', 'Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.');
INSERT INTO product_category (name, department, description) VALUES ('Angora', 'animal', 'The Angora rabbit (Turkish: Ankara tavşanı), which is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool, that are gathered by shearing, combing, or plucking.');
INSERT INTO product_category (name, department, description) VALUES ('Californian', 'animal', 'The Californian, also known as the California White, is a breed of domestic rabbit originally developed for the fur and meat industries by George S. West of Lynnwood, California, starting in 1923.');
INSERT INTO product_category (name, department, description) VALUES ('Holland Lop', 'animal', 'Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands Governing Rabbit Council in 1984');
INSERT INTO product_category (name, department, description) VALUES ('Lionhead', 'animal', 'The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone.');


INSERT INTO supplier (name, description) VALUES ('Perfect Bunnies', 'I am a hobbyist breeder of these wonderful little creatures. Our rabbits that we use for breeding are our family pets and it is purely for enjoyment that we do it.');
INSERT INTO supplier (name, description) VALUES ('Blue Clover Rabbitry', 'We are blessed to live here in the Pacific Northwest! We currently have an acre of land where we raise Holland lop bunnies, bees, and chickens. We are located in Kent Washington which is about 25 minutes south of Seattle');
INSERT INTO supplier (name, description) VALUES ('Bethanys Bunnies', 'I am based in Memphis, TN, near Hwy 385 and Riverdale. I have been breeding bunnies for over 18 years. I am currently only breeding Mini Rex, Holland Lops, English Spots and Continental Giants');
INSERT INTO supplier (name, description) VALUES ('Backyard Bunny Breeder', 'I have been breeding rabbits for 3 years now. I have 5 breeders rabbits and sell all of the kits when they are of age. My breeders range from pure Flemish Giant, to New Zealand and Flemish mix.');
INSERT INTO supplier (name, description) VALUES ('Bunny Rescue', 'Kis csapatunk igyekszik a bajba jutott, elszökött, rosszul tartott nyuszikat megmenteni, illetve súlyos okok miatt gazdát kereső nyusziknak segíteni.');


INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Black Alaska', 49, 'USD', 'Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.', 1, 4);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Brown Alaska', 47, 'USD', 'Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.', 1, 3);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Grey Angora', 289, 'USD', 'The Angora rabbit is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool', 2, 1);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('White Angora', 289, 'USD', 'The Angora rabbit is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool', 2, 2);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Californian', 1, 'USD', 'The Californian, also known as the California White, is a breed of domestic rabbit originally developed for the fur and meat industries', 3, 5);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Brown Holland Lop', 189, 'USD', 'Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands Governing Rabbit Council in 1984', 4, 3);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Black and White Holland Lop', 1, 'USD', 'Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands Governing Rabbit Council in 1984', 4, 5);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Brown Lionhead', 89, 'USD', 'The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone.', 5, 4);
INSERT INTO product (name, default_price,currency_string,description,product_category_id,supplier_id)  VALUES ('Brown and Grey Lionhead', 89, 'USD', 'The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone.', 5, 1);
