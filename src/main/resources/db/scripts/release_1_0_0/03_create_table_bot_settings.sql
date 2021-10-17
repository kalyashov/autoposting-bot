--liquibase formatted sql

--changeset kalyashov:create_table_bot_settings rollbackSplitStatements:true
--comment: Создание таблицы с настройками бота
create table bot_settings
(
    settings    json,
);

comment on table bot_settings is 'Настройки бота';

insert into bot_settings values ('{ "status": "ACTIVE" }');

--rollback drop table bot_settings;
