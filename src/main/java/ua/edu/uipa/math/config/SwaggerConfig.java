package ua.edu.uipa.math.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

/**
 * Swagger configuration
 *
 * @author oleksii.slavik
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * {@link Docket} bean configuration
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.any())
                .apis(withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Additional API information
     *
     * @return {@link ApiInfo} with additional API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Math API")
                .description("Swagger specifications for math APIs")
                .version("v1")
                .build();
    }
}
