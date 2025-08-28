create table if not exists transaction (
    transaction_id uuid not null default gen_random_uuid(),
    from_account_id uuid not null,
    to_account_id uuid not null,
    amount bigint not null,
    timestamp timestamp not null,
    status text not null,
    primary key (transaction_id),
    foreign key (from_account_id) references account(account_id),
    foreign key (to_account_id) references account(account_id)
);