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

@RestController
@RequiredArgsConstructor
public class RdsController {
    private final RdsService rdsService;
    //AWS RDS에서 Book list를 가져오는 GetMapping
    @GetMapping("/")
    public ResponseEntity<List<Book>> readDB(){
        List<Book> bookList = rdsService.findBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //AWS RDS에 Book 객체를 저장하는 PostMapping
    @PostMapping("/upload") // upload라는 경로로 가면 저장
    public ResponseEntity<List<Book>> updateDB(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setReason(form.getReason());
        rdsService.saveBook(book); // book 객체 넣어주고
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
