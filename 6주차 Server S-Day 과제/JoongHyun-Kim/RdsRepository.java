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
    public void save(Book book){
        em.persist(book);
    }

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }
}
