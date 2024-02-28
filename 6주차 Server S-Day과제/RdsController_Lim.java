package ServerStudy6Cloud.ServerStudy6Cloud.Controller;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Service.RdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//api로 보여줌
@RequiredArgsConstructor
public class RdsController {

    //서비스에 있는 모든 메소드 사용가능
    private final RdsService rdsService;

    //AWS RDS에서 Book list를 가져오는 GetMapping
    @GetMapping("/")
    public ResponseEntity<List<Book>> readDB(){
        List<Book> bookList = rdsService.findBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //AWS RDS에 Book 객체를 저장하는 PostMapping
    @PostMapping("/upload")
    public ResponseEntity<Void> updateDB(BookForm form){
        Book book = new Book();
        book.setName(form.getName());//form에서 받아온 이름으로 책이름 설정
        book.setReason(form.getReason());
        rdsService.saveBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}