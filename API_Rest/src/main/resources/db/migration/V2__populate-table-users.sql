CREATE TABLE public.users (
    id bigint NOT NULL,
    user_name character varchar(50) NOT NULL,
    address character varying(100) NOT NULL,
    gender character varchar(6) NOT NULL,

);



INSERT INTO public.users VALUES (3, 'João', 'QR200', 'Male');
INSERT INTO public.users VALUES (4, 'João', 'QR200', 'Male');
INSERT INTO public.users VALUES (5, 'João', 'QR200', 'Male');

