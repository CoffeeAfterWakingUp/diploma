-- DROP DATABASE qcrm;

-- CREATE DATABASE qcrm;

-- DROP TABLE IF EXISTS USER_ROLE;
-- DROP TABLE IF EXISTS ROLE;
-- DROP TABLE IF EXISTS USER_ACCOUNT;
-- DROP TABLE IF EXISTS COURSE;
-- DROP TABLE IF EXISTS COURSE_VIDEO;
-- DROP TABLE IF EXISTS CATEGORY;

-- DROP TYPE IF EXISTS status_enum;
-- DROP TYPE IF EXISTS course_level_enum;

--
-- SELECT pg_terminate_backend(pg_stat_activity.pid)
-- FROM pg_stat_activity
-- WHERE pg_stat_activity.datname = 'qcrm'
--   AND pid <> pg_backend_pid();

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


CREATE TABLE IF NOT EXISTS ROLE (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS USER_ACCOUNT (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password CHAR(100) NOT NULL,
    biography TEXT,
    image TEXT,
    status STATUS_ENUM NOT NULL,
    locked BOOLEAN NOT NULL DEFAULT FALSE,
    enabled BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS USER_ROLE (
    user_id UUID NOT NULL REFERENCES USER_ACCOUNT(id),
    role_id INT NOT NULL REFERENCES ROLE(id),
    PRIMARY KEY(user_id, role_id)
);

CREATE TABLE IF NOT EXISTS COURSE_VIDEO (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    url TEXT NOT NULL,
    course_id UUID NOT NULL
);


CREATE TABLE IF NOT EXISTS CATEGORY (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    parent_category INTEGER
);

INSERT INTO CATEGORY(name) VALUES ('Development');
INSERT INTO CATEGORY(name) VALUES ('Business');
INSERT INTO CATEGORY(name) VALUES ('Finance & Accounting');
INSERT INTO CATEGORY(name) VALUES ('IT & Software');
INSERT INTO CATEGORY(name) VALUES ('Design');
INSERT INTO CATEGORY(name) VALUES ('Marketing');
INSERT INTO CATEGORY(name) VALUES ('Music');

CREATE TABLE IF NOT EXISTS COURSE (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title VARCHAR(300) NOT NULL,
    subtitle TEXT NOT NULL,
    description TEXT NOT NULL,
    image TEXT NOT NULL,
    promo_video UUID NOT NULL,
    benefits TEXT NOT NULL,
    requirements TEXT NOT NULL,
    course_level COURSE_LEVEL_ENUM NOT NULL,
    course_lang VARCHAR(100) NOT NULL,
    welcome_msg TEXT,
    congrats_msg TEXT,
    price DECIMAL(19,4) NOT NULL,
    price_info TEXT,
    status STATUS_ENUM NOT NULL,
    category INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS TOKEN (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL,
    confirmed_at TIMESTAMPTZ,
    user_id UUID NOT NULL REFERENCES USER_ACCOUNT(id)
);


CREATE TABLE IF NOT EXISTS REVIEW (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    message TEXT NOT NULL,
    rating INTEGER NOT NULL,
    post_date TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL REFERENCES USER_ACCOUNT(id),
    course_id UUID NOT NULL REFERENCES COURSE(id)
);

CREATE TABLE IF NOT EXISTS SECTION (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(300) NOT NULL,
    order_number INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS CONTENT (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(300) NOT NULL,
    type VARCHAR(300) NOT NULL,
    section_id UUID NOT NULL REFERENCES SECTION(id),
    course_id UUID NOT NULL REFERENCES COURSE(id)
);

CREATE TABLE IF NOT EXISTS COURSE_TEACHER (
    course_id UUID NOT NULL REFERENCES course(id),
    teacher_id UUID NOT NULL REFERENCES user_account(id),
    PRIMARY KEY(course_id, teacher_id)
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


-- ALTER TABLE COURSE
--     ADD CONSTRAINT course_fk_subcategory
--         FOREIGN KEY (subcategory)
--             REFERENCES CATEGORY (id)
--             ON DELETE NO ACTION;




