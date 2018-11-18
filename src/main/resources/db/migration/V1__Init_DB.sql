create table book (
  id integer not null,
  author varchar(255) not null,
  book_condition integer not null,
  publish_year varchar(255) not null,
  publisher varchar(255) not null,
  status integer not null,
  title varchar(255) not null,
  primary key (id)
) engine=MyISAM;

create table exchangebooks (
  id integer not null,
  end_date datetime,
  open_exchange_date datetime,
  start_date datetime,
  book_id integer not null,
  owner_id integer not null,
  reader_id integer,
  primary key (id)
) engine=MyISAM;

create table librarybooks (
  id integer not null,
  end_date datetime,
  start_date datetime,
  book_id integer not null,
  reader_id integer not null,
  primary key (id)
) engine=MyISAM;

create table orderbooks (
  id integer not null,
  end_date datetime,
  start_date datetime not null,
  book_id integer not null,
  supplier_id integer not null,
  primary key (id)
) engine=MyISAM;

create table user (
  id integer not null,
  active bit,
  name varchar(255) not null,
  password varchar(255) not null,
  username varchar(255) not null,
  primary key (id)
) engine=MyISAM;

create table hibernate_sequence (
  next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table userroles (
  user_id integer not null,
  roles varchar(255)
) engine=MyISAM;

alter table exchangebooks add constraint FKa5aqmoc99ufa911orq5bomck3 foreign key (book_id) references book (id);
alter table exchangebooks add constraint FKow2n69il2b2ty61i1sa8qd72s foreign key (owner_id) references user (id);
alter table exchangebooks add constraint FKp56rdijiatg9329i8na3xmcwi foreign key (reader_id) references user (id);
alter table librarybooks add constraint FKsoill3oo5ox436cb96g3xsp8e foreign key (book_id) references book (id);
alter table librarybooks add constraint FK5g8ajm6dtr08cy4q60i4nknt7 foreign key (reader_id) references user (id);
alter table orderbooks add constraint FKsyjguoe2qip46di9ibatnbagu foreign key (book_id) references book (id);
alter table orderbooks add constraint FK49b9meoaaeblfaf0l2ibit6id foreign key (supplier_id) references user (id);
alter table userroles add constraint FKoxgydmg26io5fqwy8gb7dbn14 foreign key (user_id) references user (id);
