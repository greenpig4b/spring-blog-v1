package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    //select bt.id, bt.title, bt.content, bt.created_at, bt.user_id, ut.username
    //테이블은 id 통일 데이터받아올때는 별칭을 사용함
    @AllArgsConstructor // 쓰지않으면 No constuctor 에러 뜸 객체생성을 못한다 QLRM 쓸때 무조건 필요
    @Data
    public static class DetailDTO{
        private  Integer id;
        private  String title;
        private  String content;
        private Timestamp createdAt;
        private  Integer userId;
        private String username;

    }

}
