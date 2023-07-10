package top.wade.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 * @Author xjw
 * @Date 2023/7/8 21:50
 * @Description: JWT工具类
 */
@Component
public class JwtUtils {
    private static long expireTime;
    private static String secretKey;

    /**
     * 判断token是否存在
     *
     * @param token
     * @return
     */
    public static boolean judgeTokenIsExist(String token) {
        return token != null && !"".equals(token) && !"null".equals(token);
    }

    /**
     * 获取tokenBody同时校验token是否有效（无效则会抛出异常）
     *
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer", "")).getBody();
        return claims;
    }

}
