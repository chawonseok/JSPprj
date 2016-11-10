--GET LIST
--페이지 정렬 만든 애
SELECT *  
	FROM(
	SELECT ROWNUM NUM, N.* 
		FROM( 
	SELECT *
	FROM NOTICE_VIEW
	WHERE TITLE LIKE '%%'
	ORDER BY REGDATE DESC
	) N	
)    
WHERE NUM BETWEEN 11 AND 20 

-------------------------------INSERT() QUERY------------------------------------------
--NULL일 경우 0으로 채워라
--NULL일경우 1을 더해줘도 NULL이 된다
--INSERT구문에서 CODE값 가지는 쿼리
--숫자로 바뀌는 문자는 숫자덧셉 바뀜
SELECT NVL(MAX(TO_NUMBER(CODE)),0)+1 FROM NOTICE;

---------------------------------------------------------------------------------------

SELECT * FROM NOTICE;

DELETE COMMENT WHERE CODE IN (
SELECT CODE, COUNT(CODE) FROM "COMMENT" GROUP BY CODE HAVING COUNT(CODE) > 1
);

SELECT CODE FROM NOTICE;
	

INSERT INTO 
	NOTICE(CODE, TITLE, WRITER, CONTENT, REGDATE, HIT) VALUES(?,?,?,?,?,?);
	
	SEELCT
	
	
UPDATE MEMBER SET PHONE='010-0000-0000'WHERE NAME = '양수원';