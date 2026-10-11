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
| POST | /problems | 新增题目 | 规划中 |
| GET | /problems | 题目列表（分页 / 按难度标签筛选） | 规划中 |
| PUT | /problems/{id} | 修改题目 | 规划中 |
| DELETE | /problems/{id} | 删除题目 | 规划中 |
| POST | /records | 记录一次刷题（是否 AC、错因、耗时） | 规划中 |
| GET | /records | 刷题记录列表 | 规划中 |
| GET | /reviews/today | 今日待复习队列 | 规划中 |
| POST | /reviews/{id}/done | 标记复习完成 | 规划中 |

## 数据表设计

| 表 | 说明 |
| --- | --- |
| `problem` | 题目：题号、标题、难度、标签、原题链接 |
| `record` | 刷题记录：关联题目、是否 AC、错因、耗时、提交时间 |
| `review` | 复习队列：关联记录、复习日期、是否完成、复习轮次 |
| `user` | 用户 |

## 核心业务：3 / 7 / 14 天复习队列

一次刷题记录（`record`）产生后，自动派生三条复习任务（`review`）：

- 首次 AC 后 **第 3 天** 第一次复习
- 第 3 天复习通过后 → **第 7 天** 第二次复习
- 第 7 天复习通过后 → **第 14 天** 第三次复习
- 任一轮复习未通过，则该题重新排回队列开头（回到 3 天档）

`GET /reviews/today` 只返回 `review_date <= 今天` 且未完成的任务。

## 开发进度

- [x] 项目骨架（Spring Boot 3.5.16 + Maven + JDK 17）
- [x] 本地环境打通，`GET /hello` 正常返回
- [x] 代码托管到 GitHub
- [ ] 数据库设计 + 建库建表
- [ ] MyBatis 集成
- [ ] 题目 / 刷题记录 CRUD
- [ ] 3 / 7 / 14 天复习队列算法
- [ ] 接口测试与部署上线

## 本地运行

```bash
# 克隆
git clone https://github.com/WL-00-7/leetcode-review-api.git

# 运行
./mvnw spring-boot:run

# 验证
curl http://localhost:8080/hello
# => hello, 错题本
```

环境要求：JDK 17、Maven（项目自带 wrapper，无需单独安装）。