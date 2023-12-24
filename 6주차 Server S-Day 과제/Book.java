package ServerStudy6Cloud.ServerStudy6Cloud.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "book_info")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_info")
    private Long id;

    private String name;//책 이름
    private String reason;//해당 책을 좋아하는 이유
    // Entity 완성
    //를 입력받고 AWS rds에 저장하는 실습
}
