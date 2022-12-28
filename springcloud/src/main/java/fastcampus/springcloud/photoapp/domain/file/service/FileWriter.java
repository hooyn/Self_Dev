package fastcampus.springcloud.photoapp.domain.file.service;

import fastcampus.springcloud.photoapp.global.config.PhotoAppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileWriter {

    private final PhotoAppProperties photoAppProperties;

    public long writeFile(MultipartFile multipartFile, String filePath) {
        try {
            //받아온 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로
            //삭제되기 때문에 MultipartFile객체의 transferTo(File file) 메서드를 이용해서 업로드처리
            multipartFile.transferTo(new File(filePath));
        } catch (IllegalStateException ile) {
            throw new RuntimeException("file write error");
        } catch (IOException ioe) {
            throw new RuntimeException("ioe error");
        }
        return multipartFile.getSize();
    }

    public String getFilePath(String fileId, MultipartFile sourceFile) {
        return photoAppProperties.getDefaultPath() +"\\" + dateStr() + "\\" + fileId + "." + getMimeType(sourceFile.getOriginalFilename());
    }


    private static String getMimeType(String filePath) {
        //파일의 확장자를 Return 한다.
        return FilenameUtils.getExtension(filePath);
    }

    public static String dateStr() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return now.format(dateTimeFormatter);
    }
}
