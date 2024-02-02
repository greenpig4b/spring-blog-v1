package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;


import java.time.LocalDateTime;

@Data // getter, setter, toString
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    //
    @ManyToOne //참조키  JPA : ORM = DB는 DB 데이터  JAVA 는 JAVA 데이터를 쓰고 알아서 파싱해준다 join데이터 담을 항아리 안만들어도됨
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;
}