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

-- 通过分组取出最大值
-- 例如找出每个课程考试成绩最高的学生

-- 实现方式1：使用 IN 子查询获取所有
SELECT
    t1.course, t1.student
FROM
    student_scores t1
WHERE
    t1.scores IN
    (
        SELECT MAX(t2.scores) FROM student_scores t2 GROUP BY t2.course
    );

-- 实现方式2：使用 ROW_NUMBER() OVER，通过 PARTITION BY 对课程分组，然后使用 ORDER BY 降序（PARTITION BY 分析函数，性能最差）
SELECT
    t2.course, t2.student,
FROM
(
    SELECT
        t1.course, t1.student,
        ROW_NUMBER() OVER (PARTITION BY t1.course ORDER BY t1.scores DESC) rn1
    FROM student_scores t1
) t2
WHERE t1.rn = 1;


