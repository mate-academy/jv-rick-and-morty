--liquibase formatted SQL
--changeset <alexander>:<create-movie-characters-table>

ALTER TABLE public.movie_characters ADD external_id bigint;

--rollback ALTER TABLE DROP COLUMN external_id;
