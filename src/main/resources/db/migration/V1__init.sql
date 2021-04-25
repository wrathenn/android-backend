create extension if not exists pgcrypto;

create table public.cards(
    id uuid primary key,
    name text not null,
    category_id uuid,
    version integer
);

create table public.card_categories
(
    id       uuid primary key default gen_random_uuid(),
    name     text not null,
    version integer,
    access_level smallint
);

alter table public.cards add constraint fk_card_category foreign key (category_id) references card_categories(id);