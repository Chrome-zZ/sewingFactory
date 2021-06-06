delete from employees;

insert into employees (id, name, surname, password, login, role)
values (3, 'Valery', 'Orlov', 12345, 'admin' , 'ADMIN'),
        (4, 'Viktor', 'Chizhikov', 12345, 'v_chizh', 'USER'),
        (5, 'Vladimir', 'Vorobyev', 12345, 'v_voro', 'USER'),
        (6, 'Vitaly', 'Sokolov', 12345, 'v_sokol', 'USER');