package ServerStudy6Cloud.ServerStudy6Cloud.Service;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RdsService {
    private final RdsRepository rdsRepository;

    @Transactional(readOnly = true)
    public List<Book> findBooks(){
        return rdsRepository.findAll();
    }

    public Long saveBook(Book book){
        rdsRepository.save(book);
        return book.getId();
    }
}
