---
INSERT INTO Users (user_id, username, password, email)
VALUES (1, '닉네임1234', '1234', 'dlapdlf@naver.com');

---
INSERT INTO Regions (region_id, region_name)
VALUES (1, '마포구');

INSERT INTO Regions (region_id, region_name)
VALUES (2, '안암동');

INSERT INTO Regions (region_id, region_name)
VALUES (3, '강남구');
---

INSERT INTO Stores (store_id, region_id, store_name, address)
VALUES (1, 2, '가게이름a', '서울특별시 안암동 고려대로');

INSERT INTO Stores (store_id, region_id, store_name, address)
VALUES (2, 1, '가게이름b', '서울특별시 마포구 서강동');

INSERT INTO Stores (store_id, region_id, store_name, address)
VALUES (3, 3, '가게이름c', '서울특별시 강남구 삼성2동');

---
INSERT INTO Missions (mission_id, store_id, mission_title, mission_desc, reward_point)
VALUES (1, 1, '리뷰 작성 미션', '맛집 리뷰를 남기면 포인트 지급', 100);

INSERT INTO Missions (mission_id, store_id, mission_title, mission_desc, reward_point)
VALUES (2, 2, '식사 적립 미션', '10,000원 이상의 식사시 500p 적립', 500);
