CREATE DATABASE person_api;

\c person_api;

CREATE TABLE "person" (
    "id" VARCHAR NOT NULL,
    "name" VARCHAR(100) NOT NULL,
    "address" VARCHAR(300) NOT NULL,
    "address_id" VARCHAR NOT NULL,
    CONSTRAINT "PK_PERSON" PRIMARY KEY ("id")
);