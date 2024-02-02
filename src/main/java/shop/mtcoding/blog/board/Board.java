package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data  // @Getter @Setter @Tostring
@Entity // 데이터읽어서 테이블을 만들어준다
@Table(name = "taekwanBoard_tb")

public class Board {

    @Id //Primary-key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토인크리먼트
    private int id;
    private String title;
    private String content;
    private int userid ;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
