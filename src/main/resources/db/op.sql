# 查询30级以上且没有车的玩家
select a.user_id, b.user_id, a.user_name, a.lv from v1_users a
left join (select user_id from v1_user_vehicles group by user_id)b
on a.user_id = b.user_id
where a.lv >= 30 and b.user_id is null;