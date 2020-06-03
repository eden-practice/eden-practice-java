/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
