package ServerStudy6Cloud.ServerStudy6Cloud.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "book_info")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;
    private String name;
    private String reason;
}
