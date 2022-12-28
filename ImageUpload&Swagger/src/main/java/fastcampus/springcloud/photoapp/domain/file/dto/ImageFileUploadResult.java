package fastcampus.springcloud.photoapp.domain.file.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageFileUploadResult {

    private String fileId;
    private String fileName;
    private Long fileSize;
}
