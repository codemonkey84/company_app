CREATE USER company_owner WITH PASSWORD 'sparkforthewin';
CREATE DATABASE company;
\connect company
GRANT ALL PRIVILEGES ON DATABASE company TO company_owner;

CREATE TABLE companies (
    id uuid primary key,
    name text not null,
    city text not null,
    country text not null,
    address text not null,
    email text,
    phone text
);

CREATE TABLE beneficial_owners (
    id uuid primary key,
    company_id uuid references companies(id),
    name text
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO company_owner;