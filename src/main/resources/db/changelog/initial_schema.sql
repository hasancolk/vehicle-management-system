create table company
(
    id       serial
        constraint company_pk
            primary key,
    name     varchar(50) not null,
    industry varchar(50) not null
);

alter table company
    owner to postgres;

create table "group"
(
    id              serial
        constraint group_pk
            primary key,
    name            varchar(50) not null,
    parent_group_id integer
        constraint fk_group_parent_group_id
            references "group"
            on delete cascade,
    company_id      integer     not null
        constraint group_company_id_fk
            references company
            on delete cascade
);

alter table "group"
    owner to postgres;

create table vehicle
(
    id         serial
        constraint vehicle_pk
            primary key,
    plate      varchar(50) not null,
    chassis_no varchar(50),
    label      varchar(50),
    brand      varchar(50) not null,
    model      varchar(50) not null,
    model_year integer     not null,
    company_id integer     not null
        constraint vehicle_company_id_fk
            references company
            on delete cascade,
    group_id   integer
        constraint vehicle_group_id_fk
            references "group"
            on delete set null
);

alter table vehicle
    owner to postgres;

create table "user"
(
    id         serial
        constraint user_pk
            primary key,
    name       varchar(30)  not null,
    surname    varchar(30)  not null,
    username   varchar(30)  not null
        unique,
    password   varchar(255) not null,
    company_id integer
        constraint user_company_id_fk
            references company
            on delete set null,
    role_id    integer
);

alter table "user"
    owner to postgres;

create table user_group_permission
(
    id       serial
        constraint user_group_permission_pk2
            primary key,
    user_id  integer not null
        constraint user_group_permission_user_id_fk
            references "user"
            on delete cascade,
    group_id integer not null
        constraint user_group_permission_group_id_fk
            references "group"
            on delete cascade,
    role_id  integer not null,
    constraint user_group_permission_pk
        unique (user_id, group_id)
);

alter table user_group_permission
    owner to postgres;

create table user_vehicle_permission
(
    id         serial
        constraint user_vehicle_permission_pk2
            primary key,
    user_id    integer not null
        constraint user_vehicle_permission_user_id_fk
            references "user"
            on delete cascade,
    vehicle_id integer not null
        constraint user_vehicle_permission_vehicle_id_fk
            references vehicle
            on delete cascade,
    role_id    integer not null,
    constraint user_vehicle_permission_pk
        unique (user_id, vehicle_id)
);

alter table user_vehicle_permission
    owner to postgres;