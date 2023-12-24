/*
 ## 과제 수행 인증 동영상
 https://drive.google.com/file/d/10d7Rp4JSPHYUbRt4dOeozRHmkafxJ32R/view?usp=sharing
 
 ##메서드별 기능 생각해보기
     1. GetMapping("/"):
    - 이 메서드는 웹 어플리케이션의 루트 경로("/")에 대한 GET 요청을 처리
    - **`listFiles(Model model)`** 메서드는 S3 버킷 내의 파일 목록을 가져와서 **`index.html`**에 반환
    - 여기서 **`Model`** 객체를 사용하여 파일 목록을 뷰로 전달
    - **`index.html`**은 파일 목록을 화면에 표시할 템플릿 파일, 파일 목록을 가져와 화면에 표시하도록 해당 템플릿을 구성

        2. PostMapping("/upload"):
    - 이 메서드는 **`/upload`** 엔드포인트에 POST 요청을 처리
    - **`uploadFile(@RequestParam("file") MultipartFile file)`** 메서드는 클라이언트로부터 업로드된 파일을 처리
    - **`MultipartFile`**은 HTTP 요청에서 전송된 파일을 나타내며, 해당 파일을 Amazon S3에 업로드하는 작업을 수행
    - **`amazonS3.putObject()`** 또는 관련 메서드를 사용하여 파일을 S3 버킷에 업로드하고, 업로드된 파일에 대한 ACL(Access Control List)을 설정하여 해당 파일을 퍼블릭으로 만들 수 있음
    - 이후에는 **`redirect:/`**를 사용하여 파일 업로드가 완료된 후에는 루트 경로로 리디렉션하여 홈 화면으로 이동하도록 설정

    위 내용 바탕으로 아래에 코드 채워넣기
*/

package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
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

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        List<String> fileUrls = new ArrayList<>();
        
        ListObjectsV2Result objects = amazonS3.listObjectsV2(bucketName);
        for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
            String fileUrl = amazonS3.getUrl(bucketName, objectSummary.getKey()).toString();
            fileUrls.add(fileUrl);
        }

        model.addAttribute("fileUrls", fileUrls);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), null);
            amazonS3.setObjectAcl(bucketName, file.getOriginalFilename(), CannedAccessControlList.PublicRead);
        } catch (IOException e) {
            // 에러 처리 로직 추가
            e.printStackTrace();
        }

        return "redirect:/";
    }
}