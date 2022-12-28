package fastcampus.springcloud.photoapp.global.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
@Configuration
public class PhotoAppProperties {

    @Value("${photoapp.file.defaultPath}")
    public String defaultPath;

    @PostConstruct
    private void init() {
        log.info("path:: {}", this.defaultPath);
    }
}
