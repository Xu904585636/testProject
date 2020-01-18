package com.kingleadsw.ysm.sequence;

import java.util.Random;
import java.util.UUID;

/**
 * @author  zhoujie
 *
 * 序列生成器
 */
public class Sequences
{
    private static final Sequence worker = new Sequence();

    public static long getId()
    {
        return worker.nextId();
    }

    public static String getNo()
    {
        return String.valueOf(worker.nextId());
    }

    public  static String  random(int value){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append((char) ('0' + random.nextInt(10)));
        }
       return  sb.toString();
    }

    public static synchronized String get32UUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

