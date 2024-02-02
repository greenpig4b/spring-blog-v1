package shop.mtcoding.blog.user;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
//데이터 담는 테이블 만드는 클래스
//User 데이버 받을공간
@Data
@Entity // 리플랙션해서 해당 필드 분석해서 테이블 생성함
@Table(name ="user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    private int id;

    @Column(unique = true)
    private String username;

    @Column(length = 60,nullable = false)
    private String password;

    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
