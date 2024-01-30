package shop.mtcoding.blog.user;

import lombok.Data;

//앉에 넣는이유 : ""
/*
* 요청 DTO :  Data Transfer Object
* 통신데이터 DTO : 클라이언트로부터 받는 데이터 오브젝트
*
* */

public class UserRequest {
    @Data // getter setter 포함
    public static class JoinDTO{
        private String username;
        private String password;
        private String email;
    }
    /*------------------------*/
    @Data // getter setter 포함
    public static class LoginDTO{
        private String username;
        private String passoword;
    }
    /*------------------------*/
}
