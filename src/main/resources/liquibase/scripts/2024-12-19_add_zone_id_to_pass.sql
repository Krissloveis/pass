-- liquibase formatted sql

-- changeset Kris:3


alter table pass
    add column zone_id bigint;

alter table pass
add constraint fk_zone
foreign key (zone_id) references zone (id)
    on delete cascade ;