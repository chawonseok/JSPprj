SELECT * FROM NOTICES;

INSERT INTO NOTICES(CODE, TITLE, CONTENT, WRITER) VALUES('1082','�����ּ���','������ �ʿ��ؿ�','�ظ���');

SELECT ISNULL(MAX(CONVERT(INT,CODE)),0)+1 AS CODE FROM NOTICES
SELECT ISNULL(MAX(CAST(CODE AS INT)),0)+1 AS CODE FROM NOTICES


SELECT *  
	FROM(
	SELECT ROWNUM NUM, N.* 
		FROM( 
	SELECT *
	FROM NOTICES_VIEW
	WHERE TITLE LIKE '%%'
	ORDER BY REGDATE DESC
	) N	
)    
WHERE NUM BETWEEN 1 AND 10 

SELECT 
	ROW_NUMBER() OVER (ORDER BY REGDATE DESC) NUM, 
	NOTICES.*
FROM NOTICES
WHERE NUM BETWEEN 1 AND 10


---��� ���� ����ϱ� ���� ��
SELECT N.*, COUNT(C.CODE) AS CMTCNT
FROM 
	NOTICES N LEFT OUTER JOIN COMMENTS C ON N.CODE = C.NOTICECODE
GROUP BY N.CODE, N.TITLE, N.WRITER, N.CONTENT, N.REGDATE, N.HIT

SELECT * FROM NOTICES_VIEW

--getList query
SELECT * FROM (
	SELECT 
		ROW_NUMBER() OVER (ORDER BY REGDATE DESC) 'NUM', 
		NOTICES_VIEW.*
	FROM NOTICES_VIEW
	WHERE TITLE LIKE '%%'
	) N
WHERE NUM BETWEEN 1 AND 10


--getCount����
SELECT COUNT(CODE) COUNT FROM NOTICES_VIEW WHERE TITLE LIKE '%%'


SELECT MAX(CODE) FROM NOTICES
select * from noticefiles
----------------------------------------------
SELECT * FROM NOTICEFILES WHERE NOTICECODE = 0
INSERT INTO
		NOTICEFILES(CODE, SRC, NOTICECODE)
		VALUES()
		
		rollback
		
select * from noticefiles

-- 이전글(시퀄에만 있음)
select top 1 * from notices
where code > cast(1339 as int)
order by regdate asc

-- 다음글
select top 1 * from notices
where code < 1339
order by regdate desc

--조회수 1업
select cmtcnt+1 from notices_view
where code = 1339

update notices set hit = hit+1
where code = 1339






