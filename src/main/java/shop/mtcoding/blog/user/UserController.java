package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


//유효성 검사페이지
@RequiredArgsConstructor // 질문 :
@Controller
public class UserController {


    // 질문 : 의존성 주입이유 ?

    private final UserRepository userRepository;
    private final HttpSession session;  // 질문 세션을 써야 ? DB에 있는 값 비교가능?

    // 로그인은 중요한 자료이기 떄문에 예외적으로 get이 아닌 PostMapping 자료를 쓴다.
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO){
        System.out.println(requestDTO);

        //1.  유효성 검사
        if (requestDTO.getUsername().length() < 3){
            return "error/401";
        }else{
            User user = userRepository.findByUsernameAndPassword(requestDTO);
            //로그인 자료가 DB에있는거랑 비교해봐야한다
            if (user == null){
                return "error/401";
            }else{
                session.setAttribute("sessionUser",user);
                return "redirect:/";
            }

        }
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        //1. 유효성검사
        System.out.println(requestDTO);

        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //2 Model에게 위임하기
        userRepository.save2(requestDTO);

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {

        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
