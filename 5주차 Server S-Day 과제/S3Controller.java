package ServerStudy5Cloud.ServerStudy5Cloud.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        //getUrl로 객체 URL 가져온 후, List<String>에 넣어 index.html에 반환하기
        List<String> files = new ArrayList<>();

        ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        for(S3ObjectSummary os : objects){
            files.add(amazonS3.getUrl(bucketName, os.getKey()).toString());
            //log.info("{}",files.get(files.size()-1));
        }

        model.addAttribute("fileUrls", files);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        //putObject와 setObjectAcl로 이미지 업로드하고 ACL 퍼블릭으로 만들기
        amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), null);
        amazonS3.setObjectAcl(bucketName,file.getOriginalFilename(), CannedAccessControlList.PublicRead);
        return "redirect:/";

    }
}