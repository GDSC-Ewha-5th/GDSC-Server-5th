package ServerStudy6Cloud.ServerStudy6Cloud.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name= "book_info")
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long id;
    
    private String name; //책 이름
    private String reason; //해당 책을 좋아하는 이유
}
