create table if not exists users (
    id              serial constraint users_pk primary key,
    name            varchar,
    email           varchar,
    password        varchar
);

create table if not exists roles (
    id              serial constraint roles_pk primary key,
    role            varchar unique
);

create table if not exists authority (
    user_id         bigint,
    role_id         bigint,
    foreign key (user_id) references users(id),
    foreign key (role_id) references roles(id),
    primary key (user_id, role_id)
);

insert into roles (id, role) values (1, 'STUDENT');
insert into roles (id, role) values (2, 'TEACHER');
insert into roles (id, role) values (3, 'MODERATOR');
insert into roles (id, role) values (4, 'ADMIN');

insert into users (id, name, email, password) values
    (9999, 'admin', 'admin@org.com', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into authority (user_id, role_id) values (9999, 4);