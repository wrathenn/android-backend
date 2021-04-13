create extension pgcrypto;

create table public.foo(
    id uuid primary key,
    name text not null
);