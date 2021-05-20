drop table PROBLEMS;
drop table COMMENTS;


CREATE TABLE PROBLEMS
(
    id          int NOT NULL AUTO_INCREMENT,
    name        varchar(100),
    status      varchar(25),
    author      varchar(20),
    description varchar(500),
    date        varchar(15),
    dateTime        varchar(20),
    PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id        int NOT NULL AUTO_INCREMENT,
    problemId int,
    author    varchar(20),
    status    varchar(25),
    text      varchar(1000),
    dateTime      varchar(20),
    PRIMARY KEY (id)
);

INSERT INTO PROBLEMS(name, status, author, description, date,dateTime)
VALUES ('Internal error in annual PDF report', 'Resolved', 'Jhohn Smith',
        'Annual financial report always return internal error to user. It’s work on previous release.', '10/05/2021','10/05/2021  15:20'),
       ('Invalid layout for index page', 'Created', 'Adam Sendler',
        'You need to swap field 1 and field 4, since field 4 is a more important parameter.', '11/05/2021','11/05/2021  11:50'),
       ('Error on save user', 'Closed', 'Benedict Cumberbatch',
        'Error on save user', '11/04/2021','11/04/2021  13:07'),
       ('Invalid label on users page', 'Closed', 'Benedict Cumberbatch',
        'Invalid label on users page', '15/05/2021','15/05/2021  17:19');


    INSERT INTO comments(problemId, author, status, text, dateTime)
VALUES (1,  'Bill Jonhson', 'Resolved', 'Error was in security component. Fixed in revision 123.','17/05/2021  15:20' ) ;
