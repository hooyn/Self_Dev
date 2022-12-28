package fastcampus.springcloud.photoapp.global.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                //true로 설정하면  태그 및 작업에 대한 딥 링크를 활성화합니다.
                .deepLinking(true)
                //작업 목록에서 operationId의 표시를 제어합니다
                .displayOperationId(false)
                //모델 예제 섹션의 모델에 대한 기본 확장 깊이입니다.
                .defaultModelExpandDepth(3)
                .defaultModelExpandDepth(3)
                //작업 및 태그에 대한 기본 확장 설정을 제어합니다. 'LIST'(태그만 확장)
                .docExpansion(DocExpansion.LIST)
                //요청에 대한 요청 기간(밀리초) 표시를 제어합니다.
                .displayRequestDuration(false)
                //기능이 활성화된 HTTP 메서드 목록입니다.
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                //null 로 설정하면 유효성 검사가 비활성화됩니다.
                .validatorUrl(null)
                .build();
    }

    @Bean
    public Docket api() {
        // Swagger 연결하기 위한 Bean 생성
        return new Docket(DocumentationType.SWAGGER_2)
                //Docket Bean이 하나일 경우 기본값은 default로 생략가능하지만,
                //Docket Bean이 여러개일 경우 고유 값을 명시해줘야 한다.
                .groupName("PHOTO_APP")
                .apiInfo(apiInfo())
                .select()
                //ApiOperation 어노테이션이 붙은 부분들을 찾아서 Swagger 설정
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //모든 경로에 대한 문서화
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Photo Upload API")
                .description("사진 업로드 API Document")
                .version("1.0.0")
                .build();
    }
}
