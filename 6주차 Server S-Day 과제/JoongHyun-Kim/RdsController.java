package ServerStudy6Cloud.ServerStudy6Cloud.Controller;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Service.RdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RdsController {
    private final RdsService rdsService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> readDB(){
        List<Book> bookList = rdsService.findBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> updateDB(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setReason(form.getReason());
        rdsService.saveBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
