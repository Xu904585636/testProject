package com.kingleadsw.ysm.constants;


import java.util.HashMap;
import java.util.Map;

public class ServerEntry
{
    private static final Map<Integer, String> ENTRY = new HashMap();

    static
    {
        ENTRY.put(Integer.valueOf(200001), "操作成功");
        ENTRY.put(Integer.valueOf(200002), "新增成功");
        ENTRY.put(Integer.valueOf(200003), "修改成功");
        ENTRY.put(Integer.valueOf(200004), "删除成功");


        ENTRY.put(Integer.valueOf(400001), "{}不能为空");
        ENTRY.put(Integer.valueOf(400002), "{}格式错误");
        ENTRY.put(Integer.valueOf(400003), "{}长度不能小于{}");
        ENTRY.put(Integer.valueOf(400004), "{}长度不能大于{}");
        ENTRY.put(Integer.valueOf(400005), "{}值不能小于{}");
        ENTRY.put(Integer.valueOf(400006), "{}值不能大于{}");
        ENTRY.put(Integer.valueOf(400007), "{}类型不正确 ");
        ENTRY.put(Integer.valueOf(400008), "{}无效的参数");


        ENTRY.put(Integer.valueOf(401001), "您还没有登陆");
        ENTRY.put(Integer.valueOf(401002), "用户名或密码错误");
        ENTRY.put(Integer.valueOf(401003), "您的账户已冻结");
        ENTRY.put(Integer.valueOf(401004), "您的账户已过期");


        ENTRY.put(Integer.valueOf(403001), "没有权限");
        ENTRY.put(Integer.valueOf(403002), "登陆已过期");
        ENTRY.put(Integer.valueOf(403003), "无效的令牌");
        ENTRY.put(Integer.valueOf(403004), "重复请求");


        ENTRY.put(Integer.valueOf(404001), "{}没有找到");


        ENTRY.put(Integer.valueOf(409001), "{}已经存在");


        ENTRY.put(Integer.valueOf(500001), "系统错误,请联系管理员");
        ENTRY.put(Integer.valueOf(500002), "数据转换错误");
        ENTRY.put(Integer.valueOf(500003), "加密失败");
        ENTRY.put(Integer.valueOf(500004), "解密失败");
        ENTRY.put(Integer.valueOf(500005), "初始化错误");
        ENTRY.put(Integer.valueOf(500006), "序列化失败");
        ENTRY.put(Integer.valueOf(500007), "反序列化失败");
        ENTRY.put(Integer.valueOf(500007), "文件上传失败");
        ENTRY.put(Integer.valueOf(500007), "请求{}系统失败");
    }

    public static Map<Integer, String> entry()
    {
        return ENTRY;
    }
}
