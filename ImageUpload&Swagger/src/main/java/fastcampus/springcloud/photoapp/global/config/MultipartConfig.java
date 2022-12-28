package fastcampus.springcloud.photoapp.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class MultipartConfig {

    @Value("${photoapp.file.defaultPath}")
    public String defaultPath;

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //파일 또는 매개 변수 액세스 시점에서 다중 파트 요청을 느리게 해결할지 여부
        multipartResolver.setResolveLazily(true);
        //최대 업로드 가능한 바이트 크기, -1은 제한이 없음을 의미, 기본값은 -1
        multipartResolver.setMaxUploadSize(1024 * 1024 * 10);
        //요청을 파싱할 때 사용할 캐릭터 인코딩.
        multipartResolver.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        try{
            log.info("path: " + defaultPath);
            //업로드된 파일이 저장되는 임시 디렉토리를 설정합니다.
            //기본값은 웹 응용 프로그램에 대한 서블릿 컨테이너의 임시 디렉터리입니다.
            multipartResolver.setUploadTempDir(new FileSystemResource(defaultPath));
        } catch (IOException e) {
            log.error("init error: " + e.getMessage());
        }

        return multipartResolver;
    }
}
