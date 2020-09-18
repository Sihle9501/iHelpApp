

create table question (
    id serial not null primary key,
    question text,
    subject text,
    asked_by text
);

create table answer (
    id serial not null primary key,
    question_id int not null,
    answer text,
    answered_by text,
    foreign key (question_id) references question(id)
);

create table student (
  username text

);
create table teacher (
    username text

);
create table subject (
   id serial not null primary key,
   name text
);

insert into question (question, subject, asked_by) values ('what is gravity?', 'Physics', 'Kuhle');
insert into answer (question_id, answer, answered_by) values ('0', 'Its a force that pulls objects towards the center of the earth', 'Mr.Mabuza');




