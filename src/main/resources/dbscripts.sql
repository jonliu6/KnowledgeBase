--Create database and user. Refer to https://jldevtest.blogspot.com/2018/10/set-up-postgressql-database.html
--log on PostgreSQL with user and database information
-- psql -h localhost -p 5432 -U postgres -W -d db_knowledge_base

-- data structure scripts (DDL)
CREATE SEQUENCE public.seq_article_id
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE t_article (
    id INTEGER NOT NULL DEFAULT nextval('seq_article_id'),
    title CHARACTER VARYING(100),
    category CHARACTER VARYING(50),
    description TEXT,
    last_updated_at TIMESTAMP,
    CONSTRAINT pk_article_id PRIMARY KEY (id)
);

-- data scripts (DML)
INSERT INTO t_article
  (title, category, description)
  VALUES ('First Article for testing purpose', 'Test', 'This is a test record from PostgreSQL.');

CREATE TABLE t_user (
    id CHARACTER VARYING(50),
    passwd CHARACTER VARYING(255),
    email CHARACTER VARYING(100),
    role VARCHAR(100),
    CONSTRAINT pk_user_id PRIMARY KEY (id)
);
-- insert a test user with password as tester encrypted
INSERT INTO t_user (id, passwd, email) VALUES ('tester','$2a$10$HEamEC8fsE/rqi.7eFSp3.as73R2sRM25ECJ5ArULswQcq1UbJkrq','tester@freecode.org');

CREATE TABLE t_category (
    id CHARACTER VARYING(50),
    parent_id CHARACTER VARYING(50),
    description CHARACTER VARYING(100),
    CONSTRAINT pk_category_id PRIMARY KEY (id)
);