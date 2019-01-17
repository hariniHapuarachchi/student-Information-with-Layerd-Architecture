create DATABASE ijsedb;

create table batchDet
(
  course      varchar(10)  not null,
  bId         varchar(10)  not null
    primary key,
  startDate   varchar(30)  not null,
  description varchar(100) not null,
  capacity    int          null,
  constraint batchDet_ibfk_1
  foreign key (course) references courseDet (cid)
);

create table courseDet
(
  cid         varchar(10)  not null
    primary key,
  cname       varchar(30)  not null,
  description varchar(100) not null,
  duration    varchar(10)  null
);

create table parentDet
(
  stId           varchar(10)  not null
    primary key,
  parentName     varchar(100) null,
  parentMobile1  varchar(11)  null,
  parentMobile2  varchar(11)  null,
  parentMail     varchar(50)  null,
  designation    varchar(60)  null,
  workingPlace   varchar(100) null,
  workingAddress varchar(100) null,
  constraint parentDet_ibfk_1
  foreign key (stId) references studentDet (sId)
);


create table studentDet
(
  sId        varchar(10)  not null
    primary key,
  sName      varchar(100) not null,
  fullName   varchar(100) not null,
  address    varchar(100) not null,
  city       varchar(50)  not null,
  teleHome   varchar(11)  null,
  email      varchar(50)  null,
  dob        varchar(30)  null,
  gender     varchar(20)  null,
  nic        varchar(10)  null,
  school     varchar(100) null,
  uni        varchar(100) null,
  faculty    varchar(50)  null,
  teleMobile varchar(11)  null,
  higherEd   varchar(300) null
);


create table studentQualification
(
  qualID         varchar(10)  not null
    primary key,
  studentId      varchar(10)  not null,
  qualification  varchar(100) null,
  institute      varchar(100) null,
  awardDate      varchar(50)  null,
  specialization varchar(100) null,
  constraint studentQualification_ibfk_1
  foreign key (studentId) references studentDet (sId)
);


create table studentWithBatch
(
  batch   varchar(10) not null,
  student varchar(10) not null,
  primary key (batch, student),
  constraint studentWithBatch_ibfk_1
  foreign key (batch) references batchDet (bId),
  constraint studentWithBatch_ibfk_2
  foreign key (student) references studentDet (sId)
);