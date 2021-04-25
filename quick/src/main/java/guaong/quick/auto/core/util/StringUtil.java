package guaong.quick.auto.core.util;

import org.springframework.lang.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guaong
 */
public class StringUtil {

    public static String convert2CamelCase(String name) {
        if (name != null) {
            final Pattern pattern = Pattern.compile("_(\\w)");
            name = name.toLowerCase();
            Matcher matcher = pattern.matcher(name);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return "";
    }

    public static String convert2JClassName(String name) {
        String str = convert2CamelCase(name);
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    /**
     * @see org.springframework.util.StringUtils#hasText(String) 使用该方法
     */
    public static boolean hasText(@Nullable String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
