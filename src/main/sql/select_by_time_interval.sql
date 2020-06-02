-- 按时间间隔取出数据

-- 先查询最小时间
select unix_timestamp(min(created_date)) as min_created_date from sample_user;

-- 比较当前记录行的时间和最小时间差值，并分组得出数据
select created_date from
(
  select created_date, floor((unix_timestamp(created_date)-min_created_date))/300) as time_interval
  from sample_user
) t
group by t.time_interval;
