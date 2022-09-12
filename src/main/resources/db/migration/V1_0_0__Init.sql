create table users
(
    id           bigint auto_increment primary key,
    access_token varchar(36)  not null,
    email        varchar(255) not null,
    username     varchar(255) not null,
    constraint UK_access_token unique (access_token),
    constraint UK_email unique (email)
);

create table monitoring_endpoint
(
    id           bigint auto_increment primary key,
    created_at   datetime(6)   not null,
    deleted_at   datetime(6)   null,
    updated_at   datetime(6)   null,
    checked_at   datetime(6)   null,
    http_method  varchar(255)  not null,
    sec_interval int           not null,
    title        varchar(255)  not null,
    url          varchar(2048) not null,
    user_id      bigint        not null,
    constraint FK_user_id foreign key (user_id) references users (id)
);
create index idx_monitoringEndpoint_userId_createdAt on monitoring_endpoint (user_id, created_at);

create table monitoring_endpoint_response
(
    id                     bigint auto_increment primary key,
    created_at             datetime(6) not null,
    http_status_code       int         not null,
    payload                longtext    null,
    monitoring_endpoint_id bigint      null,
    constraint FK_monitoring_endpoint_id foreign key (monitoring_endpoint_id) references monitoring_endpoint (id)
);
create index idx_monitoringEndpointResponse_monitoringEndpointId_createdAt on monitoring_endpoint_response (monitoring_endpoint_id, created_at);