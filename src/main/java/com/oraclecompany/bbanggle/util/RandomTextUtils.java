package com.oraclecompany.bbanggle.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomTextUtils {

    /**
     * 6자리 인증키 생성
     */
    public static int generateAuthNo() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
}