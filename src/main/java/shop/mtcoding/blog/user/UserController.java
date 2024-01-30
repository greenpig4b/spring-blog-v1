package shop.mtcoding.blog.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/*
* 컨트롤러 책임
* - 사용자의 요청(주소 URI)을 받아 View통해반환
* - http body는 DTO
* - 기본 Mime 전략 : x-www-from-urlencoded (username=ssar&password=1234)
* - 유효성 검사
* - 클라이언트가 View만 원하는지 혹은 DB 처리 후 View도 원하는지
* - View만 원하면 View를 응답
* - Db 처리를 원하면 Model(DAO)에게 위임 후 View 응답
* */
@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //1. 유효성검사
        if (requestDTO.getUsername().length() < 3) {
            return "error/400";
        }

        //2. DTO(Model)에게 위임하기
       userRepository.save2(requestDTO);
        return "redirect:/loginForm";
    }
    /*------------------------*/
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }
    /*------------------------*/
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
    /*------------------------*/
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
    /*------------------------*/
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
    /*------------------------*/
}
