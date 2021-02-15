create extension if not exists pgcrypto;

update coaches set password = crypt(password, gen_salt('bf', 8));
update trainees set password = crypt(password, gen_salt('bf', 8));