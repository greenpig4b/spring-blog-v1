package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserRepository userRepository;
    private final HttpSession session;


    //로그인만 민감한 정보이기 때문에 get이 아니라 post로 요청한다
    //error있을때 DB랑 관련없음

    //로그인----------------------------------------------
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO){
        System.out.println(requestDTO);

        // 1. 유효성 검사
        if(requestDTO.getUsername().length() < 3){
            return "error/400";
        }
        // 2. 모델 필요 select * from user_tb where username=? and password=?
        User user = userRepository.findByUsernameAndPassword(requestDTO);
        if(user == null){
            return "error/401";
        }else{
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }

        //DB에 연동을할때 Insert 입력하기때문에 데이터가 한번에 많이 들어가면 Rock이 걸리기때문에 자료만 보는 select로 먼저확인하는게 빠르다 `

    }

    //회원가입--------------------------------------
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //1. 유효성검사
        if (requestDTO.getUsername().length() < 3) {
            return "error/400";
        }

        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null){
            //3 모델에게위임
            userRepository.save(requestDTO);
        }else {

            return "error/400";
        }

        //2. DTO(Model)에게 위임하기
        userRepository.save(requestDTO);
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

        session.invalidate();  //invalidate = 세션삭제 : 저장안했을때 세션저장소의 수명30분 web.xml에서 수정가능
        return "redirect:/";
    }
    /*------------------------*/
}
