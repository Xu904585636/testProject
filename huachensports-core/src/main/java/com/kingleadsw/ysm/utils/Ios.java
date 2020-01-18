package com.kingleadsw.ysm.utils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ios
{
    private static final Logger log = LogManager.getLogger(Ios.class);

    public static void close(Closeable closeable)
    {
        try
        {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException e){
            log.error("ios"+e.getMessage());
        }
    }

    public static byte[] toByteArray(BufferedReader input, Charset encoding)
            throws IOException
    {
        String temp = "";
        String s = "";
        while ((temp = input.readLine()) != null) {
            s = s + temp;
        }
        if (Asserts.isNull(s)) {
            return null;
        }
        return s.getBytes(encoding);
    }
}
