CREATE TABLE bivouacs(
    bivouac_id serial NOT NULL PRIMARY KEY,
    host_id integer NOT NULL,
    name varchar(80) NOT NULL,
    price numeric NOT NULL,
    rental_type varchar(30),
    field_type varchar(80),
    area numeric,
    description varchar(200) NOT NULL,
    is_pmr boolean NOT NULL,
    privacy varchar(7) NOT NULL
);

CREATE TABLE equipments(
    equipment_id serial NOT NULL PRIMARY KEY,
    label varchar(30) NOT NULL,
    icon varchar(30) NOT NULL
);

CREATE TABLE bivouac_equipments
(
    bivouac_id integer NOT NULL REFERENCES bivouacs (bivouac_id),
    equipment_id integer NOT NULL REFERENCES equipments (equipment_id)
);

INSERT INTO bivouacs(host_id,name,price,rental_type,field_type,area,description,is_pmr,privacy) VALUES(1,'Le meilleur Bivouac',34,'nothing','forest',56,'Trop bien', false, 'public');
INSERT INTO equipments(label,icon) VALUES('water','tint');
INSERT INTO equipments(label,icon) VALUES('electricity','bolt');
INSERT INTO equipments(label,icon) VALUES('shelter','shower');
INSERT INTO equipments(label,icon) VALUES('wifi','wifi');
INSERT INTO equipments(label,icon) VALUES('toilet','shower');
INSERT INTO equipments(label,icon) VALUES('shower','shower');
INSERT INTO equipments(label,icon) VALUES('campfire','fire');
INSERT INTO equipments(label,icon) VALUES('picnic','table');