INSERT INTO PROJECT(p_id,pname,points) VALUES(1,'p1',8);
COMMIT;

INSERT INTO FEATURE(f_id,fname,p_id,fpoints) VALUES(1,'f1',1,8);
INSERT INTO FEATURE(f_id,fname,p_id,fpoints) VALUES(2,'f2',1,0);
COMMIT;

INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(1,'t1',1,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(2,'t2',1,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(3,'t3',1,2);
COMMIT;