package ServerStudy6Cloud.ServerStudy6Cloud.Service;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import ServerStudy6Cloud.ServerStudy6Cloud.Repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 명시
@Transactional // JPA의 중요한 로직은 이 안에서 실행
@RequiredArgsConstructor // final이 있는 argument의 생성자를 자동 생성(롬복 제공)
public class RdsService {
    //RdsRepository를 사용해 DB에 저장하는 로직
    private final RdsRepository rdsRepository; // final로 해야 repo가 변경되는 문제를 막을 수 있음

    @Transactional(readOnly = true)
    public List<Book> findBooks(){
        return rdsRepository.findAll();
    }

    public Long saveBook(Book book){
        rdsRepository.save(book);
        return book.getId(); // 값이 저장되었는지 확인하는 용도
    }

}
