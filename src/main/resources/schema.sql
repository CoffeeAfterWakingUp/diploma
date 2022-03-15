CREATE DATABASE qcrm;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TYPE status_enum AS ENUM (
    'ACTIVE',
    'DISABLED',
    'PENDING'
);

CREATE TYPE course_level_enum AS ENUM (
    'BEGINNER LEVEL',
    'INTERMEDIATE LEVEL',
    'EXPERT LEVEL',
    'ALL LEVELS'
);



CREATE TABLE IF NOT EXISTS USER_ACCOUNT (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password CHAR(100) NOT NULL,
    biography TEXT,
    image TEXT,
    status STATUS_ENUM NOT NULL
);

CREATE TABLE IF NOT EXISTS COURSE_VIDEO (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    url TEXT NOT NULL,
    course_id UUID NOT NULL
);


CREATE TABLE IF NOT EXISTS CATEGORY (
    id UUID PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    parent_category UUID
);

CREATE TABLE IF NOT EXISTS COURSE (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(300) NOT NULL,
    subtitle TEXT NOT NULL,
    description TEXT NOT NULL,
    image TEXT NOT NULL,
    promo_video UUID NOT NULL,
    benefit TEXT NOT NULL,
    requirements TEXT NOT NULL,
    course_level COURSE_LEVEL_ENUM NOT NULL,
    course_lang VARCHAR(100) NOT NULL,
    welcome_msg TEXT,
    congrats_msg TEXT,
    price DECIMAL(19,4) NOT NULL,
    price_info TEXT,
    status STATUS_ENUM NOT NULL,
    category UUID NOT NULL,
    subcategory UUID NOT NULL
);



ALTER TABLE COURSE
    ADD CONSTRAINT course_fk_promo_video
        FOREIGN KEY (promo_video)
            REFERENCES COURSE_VIDEO (id)
            ON DELETE NO ACTION;

ALTER TABLE COURSE_VIDEO
    ADD CONSTRAINT course_video_fk_course_id
        FOREIGN KEY (course_id)
            REFERENCES COURSE (id)
            ON DELETE NO ACTION;


ALTER TABLE COURSE
    ADD CONSTRAINT course_fk_category
        FOREIGN KEY (category)
            REFERENCES CATEGORY (id)
            ON DELETE NO ACTION;


ALTER TABLE COURSE
    ADD CONSTRAINT course_fk_subcategory
        FOREIGN KEY (subcategory)
            REFERENCES CATEGORY (id)
            ON DELETE NO ACTION;




