package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/AmazonS3.html
// https://growth-coder.tistory.com/116
@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        //getUrl로 객체 URL 가져온 후, List<String>에 넣어 index.html에 반환하기
        List<String> fileUrls = new ArrayList<>();
        List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName).getObjectSummaries();
        for (S3ObjectSummary os : objectSummaries) {
            String url = "https://" + bucketName + ".s3.amazonaws.com/" + os.getKey();
            fileUrls.add(url);
        }
        model.addAttribute("fileUrls", fileUrls);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        //putObject와 setObjectAcl로 이미지 업로드하고 ACL 퍼블릭으로 만들기
        String fileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
        amazonS3.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
        return "redirect:/";
    }
}