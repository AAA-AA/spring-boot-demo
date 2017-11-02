package ren.com.cn.common.utils;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA ^_^
 * Author : renhongqiang
 * Date: 2017/5/7 22:13
 * Email: renhongqiang1397@gmail.com
 */
public class BaseCodeUtils {

    public static final char[] digits = {
            '0', '3', '4', '8', '9', 'i', 'j', 'P', 'A', 'S',

            'a', '1', 'b', '2', 'c', 'd', 'e', 'f', 'g', 'h',

            'n', 'o', 'p', 'q', 'r', 's', 't', 'X', 'C', 'V',

            'u', 'v', 'w', 'x', 'y', 'z', 'Q', 'W', 'E', 'R',

            'T', 'Y', 'U', 'I', 'O', '5', '6', '7', 'D', 'F',

            'K', 'L', 'Z', 'k', 'l', 'm', 'B', 'N', 'M', 'G',

            'H', 'J',

    };

    public static String _10_to_62(long number) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(digits[new Long((rest - (rest / 62) * 62)).intValue()]);
            rest = rest / 62;
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.toString();

    }

    public static long _62_to_10(String sixty_str) {
        int multiple = 1;
        long result = 0;
        Character c;
        for (int i = 0; i < sixty_str.length(); i++) {
            c = sixty_str.charAt(sixty_str.length() - i - 1);
            result += _62_value(c) * multiple;
            multiple = multiple * 62;
        }
        return result;
    }

    private static int _62_value(Character c) {
        for (int i = 0; i < digits.length; i++) {
            if (c == digits[i]) {
                return i;
            }
        }
        return -1;
    }

}
