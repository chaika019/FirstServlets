CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(124) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(124) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    role VARCHAR(32) NOT NULL,
    gender VARCHAR(32) NOT NULL
)




create table if not exists public.tickets
(
    id              bigint default nextval('ticket_id_seq'::regclass) not null
        constraint ticket_pkey
            primary key,
    passport_number varchar(20)                                       not null,
    passenger_name  varchar(100)                                      not null,
    flight_id       bigint                                            not null,
    seat_number     varchar(10)                                       not null,
    cost            numeric(10, 2)                                    not null
);

alter table public.tickets
    owner to postgres;





create table if not exists public.flights
(
    id                     bigserial
        primary key,
    flight_number          varchar(10),
    departure_date         timestamp,
    departure_airport_code varchar(5),
    arrival_date           timestamp,
    arrival_airport_code   varchar(5),
    aircraft_id            integer,
    status                 varchar(20)
);

alter table public.flights
    owner to postgres;





