INSERT INTO PROJECT(p_id,pname) VALUES(1,'p1');
COMMIT;

INSERT INTO FEATURE(f_id,fname,p_id) VALUES(1,'f1',1);
INSERT INTO FEATURE(f_id,fname,p_id) VALUES(2,'f2',1);
COMMIT;