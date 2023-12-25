package ServerStudy6Cloud.ServerStudy6Cloud.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm { //폼에서 입력받은 필드를 그대로 적음
    //책 이름, 책 좋아하는 이유
    private String name;
    private String reason;
}
