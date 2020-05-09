--Create database and user. Refer to https://jldevtest.blogspot.com/2018/10/set-up-postgressql-database.html
--log on PostgreSQL with user and database information
-- psql -h localhost -p 5432 -U postgres -W -d db_knowledge_base

-- data structure scripts (DDL)
CREATE SEQUENCE public.article_id_seq
    START WITH 10000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
CREATE TABLE t_article (
    id INTEGER NOT NULL DEFAULT nextval('article_id_seq'),
    title CHARACTER VARYING(100),
    category CHARACTER VARYING(50),
    description TEXT,
    last_updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
    CONSTRAINT pk_article_id PRIMARY KEY (id)
);

-- data scripts (DML)
INSERT INTO t_article
  (title, category, description)
  VALUES ('First Article for testing purpose', 'Test', 'This is a test record from PostgreSQL.');
