package com.example.finalproject_clinic.context;

import com.example.finalproject_clinic.FinalProjectClinicApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(FinalProjectClinicApplication.class.getPackage().getName()))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Clinica API",
                "API REST de la clinica",
                "1.0",
                "Terms of service",
                new Contact( "Gianluca", "www.example.com", "gian.donofrio2000@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}


