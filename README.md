# leetcode-review-api

LeetCode 刷题记录与错题本管理 API —— 基于 **3 / 7 / 14 天间隔复习算法** 的刷题管理系统后端。

解决的是我自己的真实痛点：刷过的题"做过就忘"。这个系统用来记录每道题的首次 AC 情况、错因与思路，并按 3 天 / 7 天 / 14 天的节奏自动生成复习队列，把"刷题"变成可追踪、可复盘的流程。

## 技术栈

| 层次 | 技术 |
| --- | --- |
| 语言 / JDK | Java 17 |
| 框架 | Spring Boot 3.5.16 |
| 持久层 | MyBatis |
| 数据库 | MySQL 8.4 |
| 构建工具 | Maven |
| 开发工具 | IntelliJ IDEA |

## 接口

| 方法 | 路径 | 说明 | 状态 |
| --- | --- | --- | --- |
| GET | /hello | 环境自检 | ✅ 已完成 |
| GET | /problems | 题目列表 | ✅ 已完成 |
| POST | /reviews/records | 记录一次刷题，自动派生 3/7/14 复习任务 | ✅ 已完成 |
| GET | /reviews/today | 今日待复习队列（含欠账） | ✅ 已完成 |
| POST | /reviews/{id}/done | 标记复习完成 | ✅ 已完成 |

### 接口示例

```bash
# 记录做一题（problemId 对应 problem 表，isAc=true 表示 AC）
curl -X POST "http://localhost:8080/reviews/records?problemId=1&isAc=true"

# 查今日待复习
curl "http://localhost:8080/reviews/today?userId=1"

# 标记某条复习完成
curl -X POST "http://localhost:8080/reviews/1/done"
```

## 数据表设计

| 表 | 说明 | 关联 |
| --- | --- | --- |
| `problem` | 题目：题号、标题、难度、标签、原题链接 | 归属 `user` |
| `record` | 刷题记录：关联题目、是否 AC、错因、耗时、提交时间 | 关联 `problem` |
| `review` | 复习队列：关联记录、复习轮次、复习日期、是否完成 | 关联 `record` |
| `user` | 用户 | — |

> 表之间**不使用物理外键**，关联完整性由应用层（Service）保证，符合大厂规范（并发写锁开销小、分库分表后可维护）。

## 核心业务：3 / 7 / 14 天复习队列

一次刷题记录（`record`）产生后，自动派生三条复习任务（`review`），`round` 字段 1/2/3 分别对应第 3 / 7 / 14 天：

- 首次 AC 后 **第 3 天** 第一次复习（round=1）
- 第 3 天复习通过后 → **第 7 天** 第二次复习（round=2）
- 第 7 天复习通过后 → **第 14 天** 第三次复习（round=3）

`GET /reviews/today` 只返回 `review_date <= 今天` 且未完成的任务（欠账顺延，不会漏）。

## 开发进度

- [x] 项目骨架（Spring Boot 3.5.16 + Maven + JDK 17）
- [x] 本地环境打通，`GET /hello` 正常返回
- [x] 数据库设计 + 建库建表（problem / record / review / user）
- [x] MyBatis 集成 + 实体 / Mapper / XML
- [x] 题目列表接口（GET /problems）
- [x] 3 / 7 / 14 天复习队列算法（record 派生 3 条 review）
- [x] 今日复习查询（三表联表）与标记完成
- [ ] 部署上线（公网可访问）

## 本地运行

```bash
# 克隆
git clone https://github.com/WL-00-7/leetcode-review-api.git

# 运行（项目自带 maven wrapper）
./mvnw spring-boot:run

# 验证
curl http://localhost:8080/hello
# => hello, 错题本
```

环境要求：JDK 17、MySQL 8.4（本地数据库 `leetcode_review`，执行 `sql/schema.sql` 建表）。