-- 1) 리뷰 등록
INSERT INTO review (content, star, created_at, updated_at, user_id, store_id)
VALUES ('너무 맛있고 좋았어요', 4.5, NOW(), NOW(), 1, 3);

-- 2) 마이 페이지 
SELECT 
	u.user_id,
	u.name AS user_nickname, 
	u.email AS user_email,
	u.phone_number AS user_phone_number, 
	u.point AS user_point  
FROM user AS u
WHERE u.user_id = 1;

-- 3) 미션 진행 중/완료 목록 조회 
SELECT 
	um.user_mission_id, 
	um.mission_id,
	s.name AS store_name, 
	m.point,
	m.cost,
	um.is_complete,
	m.deadline
FROM user_mission AS um
JOIN mission AS m ON um.mission_id = m.mission_id
JOIN store AS s ON m.store_id = s.store_id
WHERE um.user_id = 1
ORDER BY 
	um.is_complete ASC, 
	m.deadline ASC,
	um.user_mission_id ASC
LIMIT 15 OFFSET 0; 		

-- 4) 홈 페이지 
SELECT 
	l.name AS location_name,
	u.point AS user_point,
	s.name AS store_name, 
	s.type AS store_type,
	m.cost,
	m.point AS mission_point,
	m.deadline
FROM mission AS m
JOIN 
	store AS s ON m.store_id = s.store_id
JOIN 
	location AS l ON s.location_id = l.location_id
JOIN 
	user AS u ON u.user_id = 1 
JOIN 
	user_mission AS um ON m.mission_id = um.mission_id AND um.user_id = u.user_id
WHERE
	l.name = '월곡동' AND m.deadline >= CURDATE() AND um.is_complete = false
ORDER BY
	m.deadline ASC,
	m.mission_id ASC
LIMIT 15 OFFSET 0; 