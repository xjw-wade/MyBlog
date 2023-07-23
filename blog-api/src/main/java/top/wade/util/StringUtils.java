package top.wade.util;

/**
 * @Author xjw
 * @Date 2023/7/17 13:17
 * @Description: 字符串校验
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str 待校验字符串
     * @return
     */
    public static boolean isEmpty(String... str) {
        for (String s: str) {
            if (s == null || "".equals(s.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串中是否包含特殊字符
     *
     * @param str 待校验字符串
     * @return
     */
    public static boolean hasSpecialChar(String... str) {
        for (String s : str) {
            if (s.contains("%") || s.contains("_") || s.contains("[") || s.contains("#") || s.contains("*")) {
                return true;
            }
        }
        return false;
    }

}
