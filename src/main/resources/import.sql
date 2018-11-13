 insert into course(type, main_description, sub_description, image_url, line_image_url, level, estimated_time, price) values (0, '첫번째 이거슨 데이트 코스', '데이트 코스 서브 설명', 'www.image.example.com1', 'www.image.example.com1', 0, 3, 500);
 insert into course(type, main_description, sub_description, image_url, line_image_url, level, estimated_time, price) values (1, '첫번째 이거슨 역사기행 코스', '역사기행 코스 서브 설명', 'www.image.example.com2', 'www.image.example.com1', 1, 4, 400);
 insert into course(type, main_description, sub_description, image_url, line_image_url, level, estimated_time, price) values (2, '첫번째 이거슨 탐험 코스', '탐험 코스 서브 설명', 'www.image.example.com3', 'www.image.example.com1', 2, 3.5, 550);
 insert into user(id, authority, dmz_point, email, nickname, password, phone_number) values (1,'USER' ,500, 'example@gmail.com', '최유성', '$2a$10$oXEj64jIfbb/2MR/k9JYp.ejMGknQ9VMkZeoIVhQda6QAK9sgUOYO', '010-0000-0000')
 INSERT INTO PURCHASED_COURSE_BY_USER VALUES(1, true, 1, 1)
 INSERT INTO PLACE VALUES(1,1,1,1,1,1,1,1,1,1,1,1,1)
 INSERT INTO MISSION_HISTORY(PLACE_ID, PURCHASED_COURSES_BY_USER_ID) VALUES(1,1)