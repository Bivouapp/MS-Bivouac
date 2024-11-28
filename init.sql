DROP TABLE IF EXISTS bivouac_equipments;
DROP TABLE IF EXISTS bivouac_photos;
DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS bivouacs;
DROP TABLE IF EXISTS equipments;

CREATE TABLE bivouacs(
    bivouac_id serial NOT NULL PRIMARY KEY,
    host_id integer NOT NULL,
    address_id integer NOT NULL,
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
    equipment_id integer NOT NULL REFERENCES equipments (equipment_id),
    PRIMARY KEY (bivouac_id, equipment_id)
);

CREATE TABLE photos(
    photo_id serial NOT NULL PRIMARY KEY,
    file_name varchar(255) NOT NULL,  -- Nom du fichier
    file_path varchar(255) NOT NULL,  -- Chemin du fichier
    file_type varchar(50)            -- Type du fichier (image/jpeg, etc.)
);

CREATE TABLE bivouac_photos(
    bivouac_id integer NOT NULL REFERENCES bivouacs (bivouac_id),
    photo_id integer NOT NULL REFERENCES photos (photo_id),
    PRIMARY KEY (bivouac_id, photo_id)
);

INSERT INTO bivouacs(host_id,address_id,name,price,rental_type,field_type,area,description,is_pmr,privacy) VALUES(1,3,'Le meilleur Bivouac',34,'nothing','forest',56,'Trop bien', false, 'public');
INSERT INTO equipments(label,icon) VALUES('water','tint');
INSERT INTO equipments(label,icon) VALUES('electricity','bolt');
INSERT INTO equipments(label,icon) VALUES('shelter','shower');
INSERT INTO equipments(label,icon) VALUES('wifi','wifi');
INSERT INTO equipments(label,icon) VALUES('toilet','shower');
INSERT INTO equipments(label,icon) VALUES('shower','shower');
INSERT INTO equipments(label,icon) VALUES('campfire','fire');
INSERT INTO equipments(label,icon) VALUES('picnic','table');