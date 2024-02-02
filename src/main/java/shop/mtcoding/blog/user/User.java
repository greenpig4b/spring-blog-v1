package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// 테이터를 담는 테이블 만드는 공간
@Data
@Entity //리플랙션해서 해당 필드를 분석하여 테이블 생성
@Table(name = "taekwan_tb")
public class User {
    @Id
    @GeneratedValue // AUTO INCREMENT 어노테이션
    private int id;

    @Column(unique = true) // 컬럼에 Unique 추가해서 중복되지 않도록 설정
    private String username;

    @Column(length = 60,nullable = false) // 최대 글자수 설정 60 미만, 공백없음
    private String password;

    private String email;

    @CreationTimestamp
    private LocalDateTime creatAt;

}

