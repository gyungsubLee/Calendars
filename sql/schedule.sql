-- 스케줄 테이블 생성
CREATE TABLE Schedule (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,   -- 일정 ID (자동 증가)
      writer VARCHAR(255) NOT NULL,           -- 작성자
      password VARCHAR(255) NOT NULL,         -- 비밀번호
      title VARCHAR(255) NOT NULL,            -- 제목
      content TEXT,                           -- 내용
      created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 생성일
      updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일
);


-- 일정 생성
INSERT INTO Schedule (writer, password, title, content)
VALUES ('LEE', 'password123', '아침 회의', '아침 인사 및 일정 확인');


-- 전체 일정 조회
SELECT * FROM Schedule
ORDER BY updated_date DESC;


-- 일정 조회
SELECT * FROM Schedule
WHERE id = i;


-- 일정 수정
    -- NPE, 필수 입력 값 확인, 비밀번호의 검증은 비지니스 로직에서 처리
UPDATE Schedule
SET writer = "LEE2", title="저녁 회의"
WHERE id = 1;


-- 일정 삭제
    -- NPE, 비밀번호의 검증은 비지니스 로직에서 처리
DELETE Schedule
WHERE id = 1;