create table public.bar(
    id uuid primary key default gen_random_uuid(),
    name text not null
);