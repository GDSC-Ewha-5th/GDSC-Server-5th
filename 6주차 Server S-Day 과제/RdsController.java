package ServerStudy6Cloud.ServerStudy6Cloud.Controller;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Service.RdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
//    @GetMapping("/")
//    public String readDB(Model model){
//        model.addAttribute("bookForm", new BookForm());
//        model.addAttribute("books", rdsService.findBooks());
//        return "index";
//    }
    @GetMapping("/")
    public ResponseEntity<List<Book>> readDB(){
        List<Book> bookList = rdsService.findBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //AWS RDS에 Book 객체를 저장하는 PostMapping
//    @PostMapping("/upload")
//    public String updateDB(BookForm form){
//        Book book = new Book();
//        book.setName(form.getName());
//        book.setReason(form.getReason());
//        rdsService.saveBook(book);
//        return "redirect:/";
//    }
    @PostMapping("/upload")
    public ResponseEntity<Void> updateDB(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setReason(form.getReason());
        rdsService.saveBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
