-- liquibase formatted sql
-- changeset junior:202508280926
-- comment: set unblock_reason nullable

ALTER TABLE BLOCKS MODIFY COLUMN unblock_reason VARCHAR(255) NULL;

-- rollback ALTER TABLE board.blocks COLUMN unblock_reason varchar(255) NOT NULL;
