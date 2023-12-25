package ServerStudy6Cloud.ServerStudy6Cloud.Service;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service//1.
@Transactional
@RequiredArgsConstructor

public class RdsService {
    //RdsRepository를 사용해 DB에 저장하는 로직
    private final RdsRepository rdsRepository;//final로 만들어야 변경이 안됨

    @Transactional(readOnly = true)//조회에 최적화되도록
    public List<Book> findBooks() {
        return rdsRepository.findAll();
    }

    //책을 저장
    public Long saveBook(Book book) {
        rdsRepository.save(book);
        return book.getId();//값 저장되었는지 확인하는 용도
    }

}