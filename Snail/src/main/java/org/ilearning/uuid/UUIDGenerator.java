package org.ilearning.uuid;

import java.util.UUID;

/**
 * 功能描述：UUID生成器
 *
 * @author yuwenbo
 * @since 2022-01-28
 */
public class UUIDGenerator {
    public static synchronized String generate32UUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            System.out.println(generate32UUID());
        }
    }
}
