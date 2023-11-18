package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
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
        ObjectListing objectListing = amazonS3.listObjects(bucketName);
        List<S3ObjectSummary> s3objects = objectListing.getObjectSummaries();

        List<String> imagesURL = new ArrayList<>();

        for(S3ObjectSummary ob : s3objects){
            String objectKey = ob.getKey();
            imagesURL.add(amazonS3.getUrl(bucketName, objectKey).toString());
        }

        model.addAttribute("fileUrls", imagesURL);

        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
        //putObject와 setObjectAcl로 이미지 업로드하고 ACL 퍼블릭으로 만들기

        //s3에 파일 업로드
        amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), null);
        //업로드한 객체 ACL 설정
        amazonS3.setObjectAcl(bucketName, file.getOriginalFilename(), CannedAccessControlList.PublicRead);

        return "redirect:/";
    }
}
