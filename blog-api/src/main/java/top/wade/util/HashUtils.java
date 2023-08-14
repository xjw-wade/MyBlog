package top.wade.util;


import cn.hutool.core.lang.hash.MurmurHash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author xjw
 * @Date 2023/7/17 22:07
 * @Description: Hash工具类
 */
public class HashUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static long getMurmurHash32(String str) {
        int i = MurmurHash.hash32(str);
        long num = i < 0 ? Integer.MAX_VALUE - (long) i : i;
        return num;
    }

    public static boolean matchBC(CharSequence rawPassword, String encoderPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encoderPassword);
    }

    public static String getBC(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }



}
