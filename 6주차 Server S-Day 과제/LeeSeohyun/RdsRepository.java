package ServerStudy6Cloud.ServerStudy6Cloud.Repository;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RdsRepository {
    private final EntityManager em;
    //DB에 새로운 책 저장하는 메서드
    public void save(Book book){
        em.persist(book);
    }
    //DB에서 모든 책 리스트 가져오는 메서드
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class) //JPQL 쿼리와 조회할 class
                .getResultList();
    }
}
