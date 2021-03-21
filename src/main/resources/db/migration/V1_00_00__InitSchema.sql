create table profile
(
    id            int auto_increment
        primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    date_of_birth varchar(10)    not null,
    gender        int          not null,
    phone_number  varchar(255) not null
);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table user
(
    id         int auto_increment
        primary key,
    email      varchar(255)  not null,
    password   varchar(255)  not null,
    status     int default 1 not null,
    profile_id int           not null,
    constraint FK_user_profile
        foreign key (profile_id) references profile (id)
);

create table department
(
    id            int auto_increment
        primary key,
    name          varchar(255)                         not null,
    status        int      default 1                   not null,
    manager_id    int null,
    constraint FK_department_user
        foreign key (manager_id) references user (id)
);

create table team
(
    id            int auto_increment
        primary key,
    name          varchar(255)                         not null,
    status        int      default 1                   not null,
    department_id int                                  not null,
    constraint FK_team_department
        foreign key (department_id) references department (id)
);

create table team_user
(
    team_id int not null,
    user_id int not null,
    constraint FK_team_assign_team
        foreign key (team_id) references team (id),
    constraint FK_team_assign_user
        foreign key (user_id) references user (id)
);

create table user_role
(
    user_id int not null,
    role_id int not null,
    constraint FK_user_role_role
        foreign key (role_id) references role (id),
    constraint FK_user_role_user
        foreign key (user_id) references user (id)
);