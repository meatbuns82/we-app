package com.luwh.we.app.message.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lu.wh
 * @date 2023/10/08 10/55/06
 * @description
 */
public class MessageIdGenerator {
    private static Logger logger = LoggerFactory.getLogger(MessageIdGenerator.class);
    private static String UUID_BASIC_SEED; // UUID基础种子
    private static AtomicLong UUID_INCREASE_SEED = new AtomicLong(0l); // UUID 增长种子
    static {
        try {
            // 获取本地主机上的所有网络接口
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // 获取网络接口的硬件地址
                byte[] macAddressBytes = networkInterface.getHardwareAddress();

                if (macAddressBytes != null) {
                    // 将硬件地址转换为十六进制字符串
                    StringBuilder macAddressBuilder = new StringBuilder();
                    for (int i = 0; i < macAddressBytes.length; i++) {
                        macAddressBuilder.append(String.format("%02X%s", macAddressBytes[i], (i < macAddressBytes.length - 1) ? "-" : ""));
                    }
                    UUID_BASIC_SEED = macAddressBuilder.toString();

                }
            }
            logger.info("use mac address as uuid seed:{}", UUID_BASIC_SEED);
        } catch (SocketException e) {
            UUID_BASIC_SEED = "1";
            logger.info("can not get mac address as uuid seed, use default uuid seed:{}", UUID_BASIC_SEED);
        }
    }

    public static String generate(){
        Long seed = UUID_BASIC_SEED.hashCode() + UUID_INCREASE_SEED.get();
        String id = seed.toString() + System.currentTimeMillis();
        UUID_INCREASE_SEED.incrementAndGet();
        return id;
    }
}
