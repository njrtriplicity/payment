package com.cb.viooh.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).directModelSubstitute(XMLGregorianCalendar.class, Date.class)
                .groupName("njrtriplicity").select()
                .apis(RequestHandlerSelectors.basePackage("com.cb.viooh.payment")).paths(PathSelectors.any())
                .build().apiInfo(metaData());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("NJR Payment")
                .description("\"Payment Component \"")
                .contact(new Contact("njrtriplicity", "", "njrtriplicity@gmail.com")).version("1.0.0").build();
    }
}
