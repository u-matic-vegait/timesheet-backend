insert into "user" (username, password, role) values ('luka','$2a$10$zAnwcRbD/Vwmo/JU1GgUze5MR.bIuGYLRPSAv5IlIGWkft5E6qXaS','ADMIN');
insert into "user" (username, password, role) values ('nikola','$2a$10$zAnwcRbD/Vwmo/JU1GgUze5MR.bIuGYLRPSAv5IlIGWkft5E6qXaS','USER');
insert into "user" (username, password, role) values ('uros','$2a$10$zAnwcRbD/Vwmo/JU1GgUze5MR.bIuGYLRPSAv5IlIGWkft5E6qXaS','USER');

insert into project (name, description, status, isdeleted) values ('Timesheet', 'Internal practise project', 'ACTIVE', '0');
insert into project (name, description, status, isdeleted) values ('Old project', 'Some older project', 'ARCHIVE', '0');
insert into project (name, description, status, isdeleted) values ('Very old project', 'Very older project', 'INACTIVE', '0');
insert into project (name, description, status, isdeleted) values ('Deleted project', 'Inactive deleted project', 'INACTIVE', '1');