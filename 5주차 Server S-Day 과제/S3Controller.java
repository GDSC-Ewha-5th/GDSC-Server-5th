package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 영상 인증: https://drive.google.com/file/d/1aoKdLbbNA8EHkJmxeYoLGfrx0gwYSfdg/view?usp=sharing
 */
@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        // 버킷의 object list 조회
        ObjectListing objectListing = amazonS3.listObjects(new ListObjectsRequest().withBucketName(bucketName));

        // object list에서 각 object의 url을 조회해 list 생성
        List<String> urls = objectListing.getObjectSummaries().stream()
                .map(os -> amazonS3.getUrl(bucketName, os.getKey()).toString())
                .collect(Collectors.toList());
        model.addAttribute("urls", urls); // 모델에 추가해 뷰로 전달

        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 전송한 파일 이름 조회
        String fileName = file.getOriginalFilename();

        // 버킷에 파일 업로드
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));
        // 파일의 접근 권한을 public으로 설정
        amazonS3.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);

        return "redirect:/";
    }
}
