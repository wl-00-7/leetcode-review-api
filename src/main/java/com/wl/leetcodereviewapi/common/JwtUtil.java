package com.wl.leetcodereviewapi.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    /** 密钥：HS256 要求至少 32 字节。生产必须放环境变量 */
    private static final String SECRET = "wl-leetcode-review-secret-key-2026-abc";
    /** 有效期 7 天 */
    private static final long EXPIRE_MS = 7L * 24 * 3600 * 1000;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(java.nio.charset.StandardCharsets.UTF_8));

    /** 签发 */
    public String generate(Long userId) {
        return Jwts.builder()                                // ① 开始构建
                .subject(String.valueOf(userId))             // ② payload: sub = "1"
                .issuedAt(new Date())                        // ③ payload: iat = 当前时间
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_MS))       // ④ payload: exp = 7 天后
                .signWith(key)                               // ⑤ 用密钥签名 → 生成第三段
                .compact();                                  // ⑥ 拼成 Header.Payload.Signature`
    }

    /** 校验 + 解析，失败会抛异常 */
    public Long parseUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}