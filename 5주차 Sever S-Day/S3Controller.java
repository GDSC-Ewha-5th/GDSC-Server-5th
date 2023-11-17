package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//영상 링크 : https://drive.google.com/file/d/17tmgRlWb4g0ZRuUKt6pvBlmmw-NqpDHR/view?usp=sharing
@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        List<String> fileUrls = new ArrayList<>();
        List<S3ObjectSummary> objectSummaries = amazonS3.listObjects(bucketName).getObjectSummaries();
        // 객체 URL을 리스트에 추가
        for (S3ObjectSummary objectSummary : objectSummaries) {
            fileUrls.add(amazonS3.getUrl(bucketName, objectSummary.getKey()).toString());
        }
        // 모델에 URL 리스트 추가
        model.addAttribute("fileUrls", fileUrls);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            /// ObjectMetadata 인스턴스 생성
            ObjectMetadata metadata = new ObjectMetadata();

            // content type (MIME type)세팅
            metadata.setContentType(file.getContentType());

            // S3에 파일 업로드
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), metadata);

            // ACL를 public-read로
            amazonS3.setObjectAcl(bucketName, file.getOriginalFilename(), CannedAccessControlList.PublicRead);


            return "redirect:/";
        } catch (IOException e) {
            // Handle the exception appropriately (e.g., log it or show an error message)
            return "error";
        }

    }
}
