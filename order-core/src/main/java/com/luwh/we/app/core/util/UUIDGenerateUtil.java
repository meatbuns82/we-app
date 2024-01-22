package com.luwh.we.app.core.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lu.wh
 * @date 2023/11/30 14/10/42
 * @description
 */
public class UUIDGenerateUtil {

    private static volatile AtomicLong seed = new AtomicLong(0);
    private static String[] ID_CODES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "i", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"
            , "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Integer DEFAULT_LEN = 8;

    /**
     * 有序的code
     * @return
     */
    public static synchronized String generateUniqueId() {
        StringBuilder id = new StringBuilder();
        id.append(seed.getAndIncrement());
        for (Integer i = 0; i < DEFAULT_LEN; i++) {
            int index = (int)((Math.random()) * ID_CODES.length);
            id.append(ID_CODES[index]);
        }
        return id.toString();
    }
}
