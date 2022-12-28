package fastcampus.springcloud.photoapp.domain.file.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageFile {

    private String fileId;
    private Long fileSize;
    private String fileName;
    private String fileType;
    private String filePath;
}
