package fastcampus.springcloud.photoapp.domain.file.controller;

import fastcampus.springcloud.photoapp.domain.file.dto.ImageFileUploadResult;
import fastcampus.springcloud.photoapp.domain.file.entity.ImageFile;
import fastcampus.springcloud.photoapp.domain.file.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api
@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ApiOperation("이미지 파일 업로드")
    @PostMapping("/v1.0/upload/image")
    public ImageFileUploadResult transfer(
            @RequestParam("userKey") String userKey,
            @RequestPart("imageFile") MultipartFile multipartFile) {
        ImageFile imageFile = fileUploadService.upload(multipartFile);
        return ImageFileUploadResult.builder()
                .fileName(imageFile.getFileName())
                .fileId(imageFile.getFileId())
                .fileSize(imageFile.getFileSize())
                .build();
    }
}
