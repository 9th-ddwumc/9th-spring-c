SELECT *
FROM review
WHERE mission_template_id = 1234;

select *
from user
where name = 'nickname012';

select *
from mission as m
join mission_template as mt on mt.id = m.mission_template_id
join shop as s on s.id = mt.shop_id
where m.user_id = @user_id
and m.status = 'ing'
order by m.create_at desc, m.id desc
limit 15;

select *
from mission as m
join mission_template as mt on mt.id = m.mission_template_id
join shop as s on s.id = mt.shop_id
left join review as r
on r.user_id = m.user_id
and r.mission_template_id = m.mission_template_id
where m.user_id = @user_id
and m.status = 'end'
order by m.completed_at desc, m.id desc
limit 15;

select *
from mission_template as mt
join shop as s on s.id = mt.shop_id
where s.region_id = @region_id
and mt.id not in (
select *
from mission as m
where m.user_id = @user_id
and m.status in ('ing', 'end')
)
order by mt.id desc
limit 15;