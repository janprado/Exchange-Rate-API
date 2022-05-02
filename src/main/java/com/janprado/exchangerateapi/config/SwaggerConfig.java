package com.janprado.exchangerateapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*@Value("${swagger.host:exchange.rate.api-jan.prado}")
    private String host;*/


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                /*.host(host)*/
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.janprado.exchangerateapi.controller"))
                .paths(regex("/api.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Exchange Rate API",
                "API project to fetches, compare exchange rates and to conversion of currencies values..",
                "1.0",
                null,
                new Contact("Jan Prado", "https://www.linkedin.com/in/janprado/",
                        "janpradoadm@gmail.com"),
                "",
                "", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
