CREATE DATABASE address_api;

\c address_api;

CREATE TABLE "address" (
    "id" VARCHAR NOT NULL,
    "street" VARCHAR(100) NOT NULL,
    "city" VARCHAR(50) NOT NULL,
    CONSTRAINT "PK_ADDRESS" PRIMARY KEY ("id")
);