drop database if exists university;
create database if not exists university ;
use university;

create table STUDENT
(id int unique auto_increment primary key,
 nume char(50),
 data_nasterii date,
 adresa char (50));
 
 create table CURS
 (id int unique auto_increment primary key,
  nume char(50),
  profesor char(50),
  an_studiu int);
  
  create table INREGISTRARE
  (id int unique auto_increment primary key,
   id_student int,
   id_curs int,
   foreign key (id_student) references STUDENT(id),
   foreign key (id_curs) references CURS(id));
   
insert into STUDENT values
(1,'Matei Cristina','1994-12-08','Cluj-Napoca, Fantanele 63-65'),
(2,'Lazar Raluca', '1995-02-28','Jibou, Trandafirilor 22'),
(3,'Sechel Raluca', '1995-07-15', 'Jibou, Uzinei 20'),
(4,'Dregan Anda', '1995-06-14','Jibou, Bailor 15'),
(5,'Balsa Madalina','1995-06-18','Gilau, Florilor 5'),
(6,'Puia Andreea', '1995-07-21','Cluj-Napoca, Fantanele 40');


insert into CURS values
(1,'SSC','Baruch',3),
(2,'IIA','Groza',3),
(3,'IS','Todoran',3),
(4,'PL','Potolea',3),
(5,'PS','Dinsoreanu',3),
(6,'BD','Ivan',2),
(7,'POO','Vatavu',2),
(8,'CN','Gavrea',2);

insert into INREGISTRARE values
(1,1,1),
(2,1,3),
(3,1,4),
(4,2,2),
(5,2,4),
(6,3,3),
(7,3,5),
(8,4,1),
(9,4,2),
(10,4,5),
(11,5,6),
(12,5,8),
(13,6,6),
(14,6,7);


DROP PROCEDURE IF EXISTS  DELETE_STUDENT;
DELIMITER //
CREATE PROCEDURE DELETE_STUDENT(_nume char(50))
  BEGIN
  	SET @is_student_id = NULL;
  	SELECT @is_student_id := id FROM STUDENT WHERE nume=_nume;
  	IF (@is_student_id IS NOT NULL) THEN
        delete from INREGISTRARE where INREGISTRARE.id_student=@is_student_id;
        delete from STUDENT where STUDENT.id=@is_student_id;
  	END IF;
  END //
DELIMITER ;


DROP PROCEDURE IF EXISTS  UPDATE_STUDENT;
DELIMITER //
CREATE PROCEDURE UPDATE_STUDENT(_nume char(50), _adresa char(50))
  BEGIN
  	SET @is_student_id = NULL;
  	SELECT @is_student_id := id FROM STUDENT WHERE nume=_nume;
  	IF (@is_student_id IS NOT NULL) THEN
        update STUDENT set adresa=_adresa
		where id=@is_student_id;
  	END IF;
  END //
DELIMITER ;


DROP PROCEDURE IF EXISTS  DELETE_CURS;
DELIMITER //
CREATE PROCEDURE DELETE_CURS(_nume char(50))
  BEGIN
  	SET @is_curs_id = NULL;
  	SELECT @is_curs_id := id FROM CURS WHERE nume=_nume;
  	IF (@is_curs_id IS NOT NULL) THEN
        delete from INREGISTRARE where INREGISTRARE.id_curs=@is_curs_id;
        delete from CURS where CURS.id=@is_curs_id;
  	END IF;
  END //
DELIMITER ;


DROP PROCEDURE IF EXISTS  UPDATE_CURS;
DELIMITER //
CREATE PROCEDURE UPDATE_CURS(_nume char(50), _profesor char(50))
  BEGIN
  	SET @is_curs_id = NULL;
  	SELECT @is_curs_id := id FROM CURS WHERE nume=_nume;
  	IF (@is_curs_id IS NOT NULL) THEN
        update CURS set profesor=_profesor
		where id=@is_curs_id;
  	END IF;
  END //
DELIMITER ;


DROP PROCEDURE IF EXISTS  ENROLL;
DELIMITER //
CREATE PROCEDURE ENROLL(_nume_student char(50), _nume_curs char(50))
  BEGIN
  	SET @is_curs_id = NULL;
  	SELECT @is_curs_id := id FROM CURS WHERE nume=_nume_curs;
    SET @is_student_id = NULL;
  	SELECT @is_student_id := id FROM STUDENT WHERE nume=_nume_student;
  	IF (@is_curs_id IS NOT NULL and @is_student_id IS NOT NULL) THEN
        insert into INREGISTRARE (id_student,id_curs) values
        (@is_student_id,@is_curs_id);
  	END IF;
  END //
DELIMITER ;


