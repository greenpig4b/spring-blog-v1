package shop.mtcoding.blog.user;


import lombok.Data;

/*
* 요청하는 DTO :  Data Transfer Object 클라이언트로 받는 데이터 오브젝트
* 질문 1 : 왜 부모클래스는 public 으로 잡아놓고 자식 클래스는 static으로 잡는가(궁금)
* 질문 2 : 데이터값을 왜 객체에 담아서 가져오는 이유? 좋은점? (궁금)
* */
public class UserRequest {
    @Data
    public static class JoinDTO{
        private String username;
        private String email;
        private String password;
    }

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }


}
