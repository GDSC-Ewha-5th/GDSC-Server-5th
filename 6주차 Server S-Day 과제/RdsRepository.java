package ServerStudy6Cloud.ServerStudy6Cloud.Repository;

import ServerStudy6Cloud.ServerStudy6Cloud.Domain.Book;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // final이 붙은 필드에 자동으로 생성자 만들어주는 annotation
public class RdsRepository {
    private final EntityManager em; //EntityManager : 엔티티 객체와 데베 간 상호작용 관리, DB와 맵핑되려면 꼭 필요
    //DB에 새로운 책 저장하는 메서드
    public void save(Book book){ // 책 객체 저장 메소드
        em.persist(book); // 영속상태 : 자동으로 JPA가 엔티티의 변경을 추적하고 관리할 수 있는 상태
    }

    //DB에서 모든 책 리스트 가져오는 메서드
    public List<Book> findAll(){
        // JPQL은 객체 지향적인 방식으로 데베 검색을 정의할 수 있는 쿼리 언어, 이를 생성하는 메소드
        // JQL쿼리와 조회할 class : Book이라는 클래스에서 해당 쿼리를 진행
        return em.createQuery("select b from Book b", Book.class)
                .getResultList(); // 그 결과를 리스트로 반환
    }
}
