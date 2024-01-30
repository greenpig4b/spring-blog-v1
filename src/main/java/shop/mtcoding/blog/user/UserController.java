package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserRepository userRepository;
    private final HttpSession session;


    //로그인만 민감한 정보이기 때문에 get이 아니라 post로 요청한다
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO){
        System.out.println(requestDTO);

       //1 유효성 검사
        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //2 모델 필요
        User user = userRepository.findByUsernameAndPassword(requestDTO);

        //유저가 null 일떄
        if (user == null){
            return "error/401";
        }else{
            //3 응답
            session.setAttribute("sessionUser",user);
            return "redirect:/";
        }

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
