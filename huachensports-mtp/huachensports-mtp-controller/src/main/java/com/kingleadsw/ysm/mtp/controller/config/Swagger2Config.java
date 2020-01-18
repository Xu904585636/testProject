package com.kingleadsw.ysm.mtp.controller.config;

import com.kingleadsw.ysm.http.HttpHead;
import com.kingleadsw.ysm.swagger.Author;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class Swagger2Config {
    public static final String BR = "</br>";
    private String version = "";
    private String title = "";
    private String description = "";
    private String site = "";
    private String author = "";
    @Value("${swagger.package}")
    private String basePackage = "com.kingleadsw.mtp.controller";
    @Value("${swagger.is.enable}")
    private boolean swaggerEnable;


    public Swagger2Config() {
    }

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList();
        pars.add((new ParameterBuilder()).name(HttpHead.TOKEN.getCode()).description(HttpHead.TOKEN.getDesc()).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        pars.add((new ParameterBuilder()).name(HttpHead.TRACE_ID.getCode()).description(HttpHead.TRACE_ID.getDesc()).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        pars.add((new ParameterBuilder()).name(HttpHead.SERIAL_NO.getCode()).description(HttpHead.SERIAL_NO.getDesc()).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        pars.add((new ParameterBuilder()).name(HttpHead.DEVICE_TYPE.getCode()).description(HttpHead.DEVICE_TYPE.getDesc()).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        pars.add((new ParameterBuilder()).name(HttpHead.DEVICE_NO.getCode()).description(HttpHead.DEVICE_NO.getDesc()).modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return (new Docket(DocumentationType.SWAGGER_2)).enable(swaggerEnable).useDefaultResponseMessages(false).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.basePackage(this.basePackage)).paths(PathSelectors.any()).build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title(this.title).description(this.description()).termsOfServiceUrl(this.site).contact(new Author(this.author)).version(this.version).build();
    }

    private String description() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.description).append("</br>");
        sb.append("状态码表述，{}代表动态参数").append("</br>");
        sb.append("<table class=\"fullwidth parameters\">");
        sb.append("<tr><td>状态码</td><td>描述<td>").append(this.statusTR());
        sb.append("</table>");
        return sb.toString();
    }

    private String statusTR() {
        return  "" +
                "200001 操作成功  \n" +
                "200002 新增成功  \n" +
                "200003 修改成功  \n" +
                "200004 删除成功  \n" +
                "400001 {}不能为空  \n" +
                "400002 {}格式错误  \n" +
                "400003 {}长度不能小于{}  \n" +
                "400004 {}长度不能大于{}  \n" +
                "400005 {}值不能小于{}  \n" +
                "400006 {}值不能大于{}  \n" +
                "400007 {}类型不正确   \n" +
                "400008 {}无效的参数  \n" +
                "401001 您还没有登陆  \n" +
                "401002 用户名或密码错误  \n" +
                "401003 您的账户已冻结  \n" +
                "401004 您的账户已过期  \n" +
                "403001 没有权限  \n" +
                "403002 登陆已过期  \n" +
                "403003 无效的令牌  \n" +
                "403004 重复请求  \n" +
                "404001 {}没有找到  \n" +
                "409001 {}已经存在  \n" +
                "500001 系统错误,请联系管理员  \n" +
                "500002 数据转换错误  \n" +
                "500003 加密失败  \n" +
                "500004 解密失败  \n" +
                "500005 初始化错误  \n" +
                "500006 序列化失败  \n" +
                "500007 请求{}系统失败 ";
    }

}
