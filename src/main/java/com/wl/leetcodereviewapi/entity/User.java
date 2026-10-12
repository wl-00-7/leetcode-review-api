package com.wl.leetcodereviewapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表 user 对应的实体
 * 注：v1 暂不做登录功能，先保留实体
 */
@Data
public class User {

    /** 主键 */
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码（后续需加密存储） */
    private String password;

    /** 昵称 */
    private String nickname;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}