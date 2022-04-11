INSERT INTO PROJECT(p_id,pname,points) VALUES(1,'p1',8);
INSERT INTO PROJECT(p_id,pname,points) VALUES(2,'p2',8);
COMMIT;

INSERT INTO FEATURE(f_id,fname,p_id,fpoints) VALUES(1,'f1',1,8);
INSERT INTO FEATURE(f_id,fname,p_id,fpoints) VALUES(2,'f2',2,8);
COMMIT;

INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(1,'t1',1,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(2,'t2',1,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(3,'t3',1,2);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(4,'t1',2,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(5,'t2',2,3);
INSERT INTO TASK(t_id,tname,f_id,tpoints) VALUES(6,'t3',2,2);
COMMIT;

INSERT INTO TASK_RELATION(t_rel_id,feature_id,project_id,task_dep_id,task_id) VALUES(1,1,1,2,1);
INSERT INTO TASK_RELATION(t_rel_id,feature_id,project_id,task_dep_id,task_id) VALUES(3,2,2,5,4);
INSERT INTO TASK_RELATION(t_rel_id,feature_id,project_id,task_dep_id,task_id) VALUES(4,2,2,6,4);
COMMIT;