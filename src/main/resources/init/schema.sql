-- auto-generated definition
create table GROUP_MESSAGE
(
    ID           LONG auto_increment,
    GROUP_CODE   INT not null,
    GROUP_MEMBER INT not null,
    MESSAGE      VARCHAR,
    constraint GROUP_MESSAGE_PK
        primary key (ID)
);

