package ServerStudy6Cloud.ServerStudy6Cloud.Controller;

import lombok.Getter;
import lombok.Setter;

//BookForm: 폼에서 입력받은 내용과 entity에 넣을 내용 분리 위해 만든 클래스
@Getter @Setter
public class BookForm {
    //책 이름, 책 좋아하는 이유
    private String name;
    private String reason;
}