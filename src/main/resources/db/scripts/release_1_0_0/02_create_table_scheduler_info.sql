--liquibase formatted sql

--changeset kalyashov:create_table_scheduler_info rollbackSplitStatements:true
--comment: Создание таблицы с информацией о работе шедулеров
create table scheduler_info
(
    name         varchar(64) primary key,
    scheduled_at timestamp not null
);

comment on table scheduler_info is 'Информация о работе шедулеров';

insert into scheduler_info values ('content_indexer','2021-01-01 01:01:01.184658');

--rollback drop table scheduler_info;
