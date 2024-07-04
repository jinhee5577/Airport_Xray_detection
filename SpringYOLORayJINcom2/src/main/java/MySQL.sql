CREATE TABLE BOARD(
   IDX INT NOT NULL AUTO_INCREMENT,
   TITLE VARCHAR(100) NOT NULL,
   CONTENT VARCHAR(2000) NOT NULL,
   WRITER VARCHAR(30) NOT NULL,
   INDATE DATETIME DEFAULT NOW(),
   COUNT INT DEFAULT 0,
   PRIMARY KEY(IDX)
);

-- 실행 단축기 알트 + x

SELECT * FROM BOARD;



-- test 데이터 넣기
insert into member(memId, memPassword, memName, memAge, memGender, memEmail)
values('admin', '1234', '관리자', 20, '여자', 'admin@gmail.com');
insert into member(memId, memPassword, memName, memAge, memGender, memEmail)
values('smart', '1234', '스마트', 30, '남자', 'smart@gmail.com');
insert into member(memId, memPassword, memName, memAge, memGender, memEmail)
values('juhee', '1111', '이주희', 19, '여자', 'juhee@gmail.com');



-- yoloray table
select * from COMPANY;
select * from Notice;
select * from WORKLOAD;
select * from MEMBER;
select * from DETECTION;
select * from MODEL;
select * from TYPE;


UPDATE MODEL SET MODEL_DETAIL='칼,총,가위,랜치,펜치 탐지' WHERE MODEL_FILE='v1.0.1_240613.pt';

SELECT * FROM MODEL ORDER BY MODEL_AT DESC;

select * from COMPANY where IDENTIFI_ID='123456';

insert into MEMBER(MEMBER_ID, MEMBER_NAME, MEMBER_AUTH)
values('456789', 'jinhee oh', 'ture');
insert into MEMBER(MEMBER_ID, MEMBER_NAME, MEMBER_AUTH)
values('test1', '손채영', 'ture');
insert into MEMBER(MEMBER_ID, MEMBER_NAME, MEMBER_AUTH)
values('test2', '오종원', 'ture');


INSERT INTO Notice(NOTICE_TITLE, NOTICE_DETAIL)
VALUES('2건발견','yoloray성능이 너무 좋습니다');
INSERT INTO Notice(NOTICE_TITLE, NOTICE_DETAIL)
VALUES('4건발견','권총이 탐지되었습니다.');
INSERT INTO Notice(NOTICE_TITLE, NOTICE_DETAIL)
VALUES('3','마약 탐지되었습니다.');



INSERT INTO COMPANY (IDENTIFI_ID)
values('456789');


INSERT INTO MODEL (MODEL_NAME, MODEL_DETAIL, MODEL_FILE) values('model_v1_7', 'Kubotan 탐지추가','v1.0.1_240618.pt');


insert into COMPANY(IDENTIFI_ID, COMPANY_NAME, COMPANY_PW)
values('123456', 'nexon', 'jini');


INSERT INTO WORKLOAD (WORK_IDX,IDENTIFI_ID, WORK_PRESENCE)
VALUES(1,'747474', 'Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_NUM, WORK_PRESENCE)
VALUES('test1', 80, 'Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_NUM, WORK_PRESENCE)
VALUES('test1', 120, 'Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_NUM, WORK_PRESENCE)
VALUES('test1', 100, 'Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_NUM, WORK_PRESENCE)
VALUES('test2', 150, 'Y');

INSERT INTO WORKLOAD (MEMBER_ID, WORK_PRESENCE)
VALUES('test2','N');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_PRESENCE)
VALUES('test2','N');
INSERT INTO WORKLOAD (MEMBER_ID,WORK_PRESENCE)
VALUES('456789','Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_PRESENCE)
VALUES('456789','Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_PRESENCE)
VALUES('test1','Y');
INSERT INTO WORKLOAD (MEMBER_ID, WORK_PRESENCE)
VALUES('test1','Y');



INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM, DETECTION_FILE)
VALUES ('test2', 110, 'file12.txt');
INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM, DETECTION_FILE)
VALUES ('747474', 152, 'file23.txt');
INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM, DETECTION_FILE)
VALUES ('789456', 91, 'file21.txt');
INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM, DETECTION_FILE)
VALUES ('747474', 91, 'file25.txt');
INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM, DETECTION_FILE)
VALUES ('789456', 91, 'file21.txt');


INSERT INTO WORKLOAD (IDENTIFI_ID, WORK_AT, WORK_PRESENCE)
VALUES ('123456', '2024-05-15', 'Y');
INSERT INTO WORKLOAD (IDENTIFI_ID, WORK_AT, WORK_PRESENCE)
VALUES ('123456', '2024-05-16', 'Y');

INSERT INTO DETECTION (IDENTIFI_ID, TYPE_NUM,DETECTION_DATE, DETECTION_FILE)
VALUES ('747474', 4,'2024-05-22', 'file123.txt');



SELECT * FROM DETECTION ORDER BY DETECTION_DATE DESC;
SELECT * FROM WORKLOAD ORDER BY WORK_AT DESC;



-- DETECTION 테이블 최신날짜순으로 정렬후 가져옴.
SELECT * 
FROM DETECTION 
ORDER BY DETECTION_DATE DESC;


-- 트리거 상태 확인
SELECT trigger_name, status
FROM user_triggers
WHERE trigger_name = 'WORKLOAD_AI_TRG';

-- 트리거 컴파일 오류 확인
SELECT text
FROM user_errors
WHERE name = 'WORKLOAD_AI_TRG';


-- 월별로 탐지물품 건수 합산 쿼리 
-- ex1)
SELECT
    TO_CHAR(WORK_AT, 'YYYY-MM') AS month,
    SUM(WORK_NUM) AS total_work
FROM WORKLOAD
GROUP BY TO_CHAR(WORK_AT, 'YYYY-MM');


-- ex2)
SELECT
    TO_CHAR(WORK_AT, 'MM') AS month,
    SUM(WORK_NUM) AS total_work
FROM WORKLOAD
GROUP BY TO_CHAR(WORK_AT, 'MM');


-- ex3)
SELECT
    TO_CHAR(WORK_AT, 'MM') AS month,
    COUNT(*) AS total_y
FROM WORKLOAD
WHERE WORK_PRESENCE = 'Y'
GROUP BY TO_CHAR(WORK_AT, 'MM');


-- ex4)
SELECT
    COUNT('Y') AS total_y
FROM WORKLOAD
WHERE WORK_AT = SYSDATE
GROUP BY WORK_PRESENCE;


-- 우선 오늘 날짜를 먼저 가져와야한다.
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD') FROM DUAL;

-- 금일 y데이터 총갯수 구하는 쿼리
SELECT COUNT(WORK_PRESENCE) AS TODAY_Y
FROM WORKLOAD
WHERE TO_CHAR(WORK_AT, 'YYYY-MM-DD') ='2024-06-04'
AND work_presence = 'Y'
   

SELECT * FROM WORKLOAD;


-- 해당 유저의 탐지된품목 번호로 탐지(DETECTION)테이블과 품목 이름 결합하여 출력하는 쿼리
SELECT TYPE_NAME_ENG,DETECTION_IDX,MEMBER_ID,DETECTION_DATE,DETECTION_FILE
FROM TYPE A, DETECTION B
WHERE A.TYPE_NUM(+) = B.TYPE_NUM AND MEMBER_ID= '747474';


-- 알림내용 가져오는 쿼리
SELECT TYPE_NAME_ENG,IDENTIFI_ID
 FROM TYPE A, DETECTION B
 WHERE A.TYPE_NUM(+) = B.TYPE_NUM AND IDENTIFI_ID='747474'
 ORDER BY DETECTION_DATE DESC;


-- 해당 유저의 이상탐지기록 :탐지된품목 번호로 탐지(DETECTION)테이블과 품목이름 결합하여 6개까지 출력하는 기능.
SELECT TYPE_NAME_ENG,DETECTION_IDX,IDENTIFI_ID,DETECTION_DATE,DETECTION_FILE
FROM TYPE A, DETECTION B
WHERE A.TYPE_NUM(+) = B.TYPE_NUM AND IDENTIFI_ID='898989' AND ROWNUM <= 6
ORDER BY DETECTION_DATE DESC;


-- 위해물품 탐지 주간 합산 전체 작업량 보기
SELECT 
    TRUNC(WORK_AT, 'IW') AS WEEK_START_DATE,  -- 주 시작일로 변환
    COUNT(*) AS TOTAL_Y
FROM WORKLOAD
WHERE WORK_PRESENCE = 'Y'
GROUP BY TRUNC(WORK_AT, 'IW')
ORDER BY WEEK_START_DATE;


-- 주 시작일을 'MM/DD' 형식으로 변환
SELECT 
    TO_CHAR(TRUNC(WORK_AT, 'IW'), 'MM/DD') AS week_start,
    COUNT(*) AS TOTAL_COUNT
FROM WORKLOAD
WHERE WORK_PRESENCE = 'Y'
GROUP BY TRUNC(WORK_AT, 'IW')
ORDER BY TRUNC(WORK_AT, 'IW');


-- 이번주 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
WITH this_week AS (
    SELECT DETECTION_DATE, type_num
    FROM DETECTION
    WHERE DETECTION_DATE BETWEEN TRUNC(SYSDATE, 'IW') AND NEXT_DAY(TRUNC(SYSDATE, 'IW') - 1, '일')
),
type_count AS (
    SELECT type_num, COUNT(*) AS type_count
    FROM this_week
    GROUP BY type_num
)
SELECT type_num AS thistype,
	   type_count AS this_typecnt
FROM type_count
WHERE type_count = (SELECT MAX(type_count) FROM type_count);



-- 저번주 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
WITH last_week AS (
    SELECT DETECTION_DATE, TYPE_NUM
    FROM DETECTION
    WHERE DETECTION_DATE BETWEEN TRUNC(SYSDATE, 'IW') - 7 AND NEXT_DAY(TRUNC(SYSDATE, 'IW') - 8, '일')
),
type_count AS (
    SELECT TYPE_NUM, COUNT(*) AS type_count
    FROM last_week
    GROUP BY TYPE_NUM
)
SELECT type_num AS lasttype,
       type_count AS last_typecnt
FROM type_count
WHERE type_count = (SELECT MAX(type_count) FROM type_count);



select TYPE_NAME_ENG AS type_name
FROM TYPE WHERE TYPE_NUM = 4;




-- 이번달 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
WITH this_month AS (
    SELECT DETECTION_DATE, TYPE_NUM
    FROM DETECTION
    WHERE DETECTION_DATE BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE)
),
type_count AS (
    SELECT TYPE_NUM, COUNT(*) AS type_count
    FROM this_month
    GROUP BY TYPE_NUM
)
SELECT TYPE_NUM, type_count
FROM type_count
WHERE type_count = (SELECT MAX(type_count) FROM type_count);


-- 저번달 가장 많은 TYPE_NUM구하는 오라클 SQL쿼리
WITH last_month AS (
    SELECT DETECTION_DATE, type_num
    FROM DETECTION
    WHERE DETECTION_DATE BETWEEN TRUNC(ADD_MONTHS(SYSDATE, -1), 'MM') AND LAST_DAY(ADD_MONTHS(SYSDATE, -1))
),
type_count AS (
    SELECT type_num, COUNT(*) AS type_count
    FROM last_month
    GROUP BY type_num
)
SELECT type_num, type_count
FROM type_count
WHERE type_count = (SELECT MAX(type_count) FROM type_count);


select * from DETECTION;


















   