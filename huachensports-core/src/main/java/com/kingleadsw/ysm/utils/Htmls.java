package com.kingleadsw.ysm.utils;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/3 20:53
 * @Description:
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Htmls
{
    private static final Logger log = LogManager.getLogger(Htmls.class);
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String regEx_html = "<[^>]+>";
    private static final String regEx_space = "\\s*|\t|\r|\n";

    public static String getTextFromHtml(String htmlStr)
    {
        if (Asserts.isNull(htmlStr)) {
            return "";
        }
        htmlStr = delHTMLTag(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr;
    }

    public static String delHTMLTag(String htmlStr)
    {
        Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        Pattern p_html = Pattern.compile("<[^>]+>", 2);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");

        Pattern p_space = Pattern.compile("\\s*|\t|\r|\n", 2);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        return htmlStr.trim();
    }

    public static String delHTMLTagByReplace(String strHtml)
    {
        String txtcontent = strHtml.replaceAll("</?[^>]+>", "");
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
        return txtcontent;
    }

    public static String delHTMLTagByPattern(String inputString)
    {
        String htmlStr = inputString;
        String textStr = "";
        try
        {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            String regEx_html = "<[^>]+>";
            Pattern p_script = Pattern.compile(regEx_script, 2);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("");
            Pattern p_style = Pattern.compile(regEx_style, 2);
            Matcher m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("");
            Pattern p_html = Pattern.compile(regEx_html, 2);
            Matcher m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("");
            textStr = htmlStr;
        }
        catch (Exception e)
        {
            log.error("delHTMLTagByPattern error", e);
        }
        textStr = textStr.replaceAll("[ ]+", " ");
        textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr;
    }

    public static String getBody(String html)
    {
        if ((Strings.indexOf(html, "<body>") > 0) && (Strings.indexOf(html, "<body>") > 0))
        {
            String start = "<body>";
            String end = "</body>";
            int s = html.indexOf(start) + start.length();
            int e = html.indexOf(end);
            return html.substring(s, e);
        }
        return html;
    }
}
