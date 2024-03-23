package ServerStudy6Cloud.ServerStudy6Cloud.Service;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor //final이 있는 argument의 생성자를 자동으로 만들어준다.
public class RdsService {
    //RdsRepository를 사용해 DB에 저장하는 로직
    private final RdsRepository rdsRepository;

    @Transactional(readOnly = true) //위의 transactional을 override
    public List<Book> findBooks(){
        return rdsRepository.findAll();
    }

    public Long saveBook(Book book){
        rdsRepository.save(book);
        return book.getId(); //값이 저장되었는지 확인하는 용도
    }

}
