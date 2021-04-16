create table public.card_categories
(
    id       uuid primary key default gen_random_uuid(),
    name     text not null,
    is_adult boolean          default false
);

alter table public.cards add column category_id uuid;

alter table public.cards add constraint fk_card_category foreign key (category_id) references card_categories(id);
