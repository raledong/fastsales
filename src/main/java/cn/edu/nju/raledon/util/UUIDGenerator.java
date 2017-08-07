package cn.edu.nju.raledon.util;

import java.util.UUID;

/**
 * Created by rale on 6/6/17.
 * 使用jdk生成唯一的UUID
 */
public class UUIDGenerator {
    private static String[] chars = new String[]
            {
                    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
            };

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 生成八位纯数字的UUID
     * @return
     */
    public static String generateShortUUID()
    {
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < 8; i++)
        {
            String str      = uuid.substring(i * 4, i * 4 + 4);
            int strInteger  = Integer.parseInt(str, 16);
            stringBuffer.append(chars[strInteger % 0x3E]);
        }

        return stringBuffer.toString();
    }

}
