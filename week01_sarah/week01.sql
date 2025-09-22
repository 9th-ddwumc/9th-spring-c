--- 
INSERT INTO Users (user_id, username, password, email) VALUES (1, '닉네임1234', '1234', 'dlapdlf@naver.com'); 

--- 
INSERT INTO Regions (region_id, region_name) VALUES (1, '마포구'); 
INSERT INTO Regions (region_id, region_name) VALUES (2, '안암동'); 
INSERT INTO Regions (region_id, region_name) VALUES (3, '강남구'); 

--- 
INSERT INTO Stores (store_id, region_id, store_name, address) VALUES (1, 2, '가게이름a', '서울특별시 안암동 고려대로'); 
INSERT INTO Stores (store_id, region_id, store_name, address) VALUES (2, 1, '가게이름b', '서울특별시 마포구 서강동'); 
INSERT INTO Stores (store_id, region_id, store_name, address) VALUES (3, 3, '가게이름c', '서울특별시 강남구 삼성2동'); 

--- 
INSERT INTO Missions (mission_id, store_id, mission_title, mission_desc, reward_point) VALUES (1, 1, '리뷰 작성 미션', '맛집 리뷰를 남기면 포인트 지급', 100); 
INSERT INTO Missions (mission_id, store_id, mission_title, mission_desc, reward_point) VALUES (2, 2, '식사 적립 미션', '10,000원 이상의 식사시 500p 적립', 500); 

--- 
INSERT INTO Mission_Assignments (assignment_id, user_id, mission_id, status) VALUES (1, 1, 1, 'completed'); 
INSERT INTO Mission_Assignments (assignment_id, user_id, mission_id, status) VALUES (2, 1, 2, 'completed'); 

--- 1. 리뷰 작성 쿼리 
INSERT INTO Reviews (review_id, assignment_id, rating, comment) VALUES (1, 1, 5, '음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였습니다. 다음에 또 올게요!!'); 

--- 2. 마이페이지 작성 쿼리 
SELECT 
    u.username AS nickname,     
    u.email, 
    u.points, 
    r.review_id, 
    r.rating, 
    r.comment, 
    DATE(r.created_at) AS review_date, 
    m.mission_title 
FROM Users u 
LEFT JOIN Mission_Assignments ma ON u.user_id = ma.user_id 
LEFT JOIN Reviews r ON ma.assignment_id = r.assignment_id 
LEFT JOIN Missions m ON ma.mission_id = m.mission_id 
WHERE u.user_id = 1;

--- 3. 내가 진행중/완료한 미션 모아보기 (페이징 포함) 
SELECT 
    ma.assignment_id, 
    m.mission_title, 
    m.mission_desc, 
    ma.status, 
    ma.assigned_at, 
    ma.completed_at 
FROM Mission_Assignments ma 
JOIN Missions m ON ma.mission_id = m.mission_id 
WHERE ma.user_id = 1 
ORDER BY ma.assigned_at DESC 
LIMIT 10 OFFSET 0;

--- 4. 홈 화면 쿼리 (현재 선택된 지역에서 도전 가능한 미션 목록, 페이징 포함) 
SELECT 
    m.mission_id, 
    m.mission_title, 
    m.mission_desc, 
    m.reward_point, 
    s.store_name, 
    s.address, 
    r.region_name 
FROM Missions m 
JOIN Stores s ON m.store_id = s.store_id 
JOIN Regions r ON s.region_id = r.region_id 
WHERE r.region_name = '안암동'
ORDER BY m.mission_id DESC 
LIMIT 10 OFFSET 0;