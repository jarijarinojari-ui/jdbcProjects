ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

CREATE USER study
identified BY 1234
DEFAULT tablespace USERS quota unlimited on USERS;

GRANT connect, resource, create view to study;

-----------------------------------------------------

CREATE TABLE "study"."TABLE"(
  "num"     NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY
  ,"NAME" VARCHAR2(200) NOT NULL
  ,"SEX" VARCHAR2(200) NOT NULL
  ,"AFFILIATION" VARCHAR2(200) NOT NULL
  ,"PHONENUM" VARCHAR2(200) NOT NULL
);

CREATE sequence "KANJI"."SEQ_KANJI"
minvalue 0
MAXVALUE 9999
INCREMENT BY 1
START WITH 1
CACHE 2
noorder
nocycle
NOKEEP
NOSCALE
GLOBAL;

