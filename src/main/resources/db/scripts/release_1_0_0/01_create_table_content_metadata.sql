--liquibase formatted sql

--changeset kalyashov:create_table_content_metadata rollbackSplitStatements:true
--comment: Создание таблицы с метаинформацией о контенте
create table content_metadata
(
    id         uuid primary key,
    name       varchar(256) not null,
    path       varchar      not null,
    created_at timestamp    not null,
    posted_at  timestamp
);

comment on table content_metadata is 'Метаинформация о контенте';

--rollback drop table content_metadata;
