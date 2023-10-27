--liquibase formatted SQL
--changeset <alexander>:<create-movie-character-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.movie_character_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP TABLE movie_character_id_seq;