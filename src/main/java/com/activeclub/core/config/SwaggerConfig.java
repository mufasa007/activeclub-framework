//package com.activeclub.core.config;
//
//import org.assertj.core.internal.Predicates;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author 59456
// * @Date 2021/11/11
// * @Descrip todo 后续优化
// * @Version 1.0
// */
//@Configuration
//@EnableSwagger2
//public class Swagger2Config {
//    @Bean
//    public Docket webApiConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("webApi")
//                .apiInfo(webApiInfo())
//                .select()
//                //只显示api路径下的页面
//                .paths(Predicates.and(PathSelectors.regex("/XXX/.*")))
//                .build();
//    }
//
//    @Bean
//    public Docket adminApiConfig(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("adminApi")
//                .apiInfo(adminApiInfo())
//                .select()
//                //只显示admin路径下的页面
//                .paths(Predicates.and(PathSelectors.regex("/XXX/.*")))
//                .build();
//    }
//
//    private ApiInfo webApiInfo(){
//        return new ApiInfoBuilder()
//                .title("网站-API文档")
//                .description("本文档描述了网站微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("XXX", "http://XXX.com", "XXX"))
//                .build();
//    }
//
//    private ApiInfo adminApiInfo(){
//        return new ApiInfoBuilder()
//                .title("后台管理系统-API文档")
//                .description("本文档描述了后台管理系统微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("XXX", "http://XXX.com", "XXX"))
//                .build();
//    }
//}
