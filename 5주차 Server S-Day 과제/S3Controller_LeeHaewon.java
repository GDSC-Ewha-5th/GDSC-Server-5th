package GDSC.ServerStudyCloud5.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
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

/* 

      [Cloud 5주차 과제]
      1. S3에 객체 업로드가 가능한 @PostMapping 구현
      2. S3 객체 조회가 가능한 @GetMapping 구현

      영상 인증 : https://drive.google.com/file/d/1oLtqv3YUAEVZu-bkpyFeafFT2Jj8jysW/view?usp=sharing
      
*/

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @GetMapping("/")
    public String listFiles(Model model) {
        List<String> imageUrls = getImageUrls();
        model.addAttribute("fileUrls", imageUrls); // fileUrls 값 

        return "index"; // index.html 반환
    }

    private List<String> getImageUrls() {

        // S3 버킷 내의 모든 객체의 키(파일 경로)를 가져오기
        List<String> objectKeys = amazonS3.listObjects(bucketName).getObjectSummaries()
                .stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey())
                .collect(Collectors.toList());

        // 이미지 URL로 변환
        return objectKeys.stream()
                .map(objectKey -> amazonS3.getUrl(bucketName, objectKey).toString()) // 객체 URL 가져오기
                .collect(Collectors.toList());
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
        
        String originalFilename = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize()); // 메타데이터 설정
        metadata.setContentType(file.getContentType()); // 메타데이터 설정

        amazonS3.putObject(bucketName, originalFilename, file.getInputStream(), metadata); // 이미지 업로드
        amazonS3.setObjectAcl(bucketName, originalFilename, CannedAccessControlList.PublicRead); // ACL 퍼블릭으로 만들기
        
        return "redirect:/";

    }
}