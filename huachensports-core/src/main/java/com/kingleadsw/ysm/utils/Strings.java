package com.kingleadsw.ysm.utils;

import com.kingleadsw.ysm.lang.FormattingTuple;
import com.kingleadsw.ysm.lang.MessageFormatter;

import java.util.UUID;

/**
 * @author  zhoujie
 *
 * String 操作共用类
 */
public class Strings {

    public static String format(String messagePattern, Object... args)
    {
        FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messagePattern, args);
        return formattingTuple.getMessage();
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq)
    {
        if ((seq == null) || (searchSeq == null)) {
            return -1;
        }
        return indexOf(seq, searchSeq, 0);
    }

    public static int indexOf(CharSequence cs, CharSequence searchChar, int start)
    {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex)
    {
        if (array == null) {
            return null;
        }
        int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return "";
        }
        String separatorCp = "";
        if (Asserts.notNull(separator)) {
            separatorCp = separator;
        }
        StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++)
        {
            if (i > startIndex) {
                buf.append(separatorCp);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static String substring(String string, int fromIndex, int toIndex)
    {
        int len = string.length();
        if (fromIndex < 0)
        {
            fromIndex = len + fromIndex;
            if (toIndex == 0) {
                toIndex = len;
            }
        }
        if (toIndex < 0) {
            toIndex = len + toIndex;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (toIndex > len) {
            toIndex = len;
        }
        if (fromIndex >= toIndex) {
            return "";
        }
        return string.substring(fromIndex, toIndex);
    }

    public static String join(Object[] array, String separator)
    {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }
    
	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
}
