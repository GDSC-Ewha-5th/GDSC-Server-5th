/*package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
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


@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        //getUrl로 객체 URL 가져온 후, List<String>에 넣어 index.html에 반환하기
        //업로드할 URL 파일 목록을 저장할 리스트 생성
        List<String> fileUrls = new ArrayList<>();

        //S3 내의 객체 목록 가져오기
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();

        // 객체 URL 가져와서 리스트에 추가
        for (S3ObjectSummary objectSummary : objectSummaries) {
            String fileUrl = amazonS3.getUrl(bucketName, objectSummary.getKey()).toString();
            fileUrls.add(fileUrl);
        }

        // 모델에 URL 리스트 추가 
        model.addAttribute("fileUrls", fileUrls);

        return "index";
    }



    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException{

        //putObject와 setObjectAcl로 이미지 업로드하고 ACL 퍼블릭으로 만들기
        //파일 이름가져오기
        String originalFilename = file.getOriginalFilename();

        //메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // putObject로 s3에 파일 업로드
        amazonS3.putObject(bucketName, originalFilename, file.getInputStream(),metadata);

        //업로드한 객체에 ACL퍼블릭 설정
        amazonS3.setObjectAcl(bucketName, originalFilename, CannedAccessControlList.PublicRead);

        return "redirect:/";


    }
}

//https://drive.google.com/file/d/1o9uRjxExZHYJOCKX6aTS8y8KKc-vic0d/view?usp=sharing