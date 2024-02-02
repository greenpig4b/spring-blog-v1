package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.PagingUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    //IOC 컨테이너에 세션에 접근할 수 있는 변수가 들어올 수있음 DI하면된다

    private final BoardRepository boardRepository;
    private final HttpSession session;


    // http://localhost:8080?page=0
    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage+1;
        int prevPage = currentPage-1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        boolean first = PagingUtil.isFirst(currentPage);
        boolean last = PagingUtil.isLast(currentPage, boardRepository.count());

        request.setAttribute("first", first);
        request.setAttribute("last", last);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    // 보드 뒤에 들어가는 1은 PK 보드에 {}안에 ID 값 넣어서쓰면 알아서 파싱해서 찾아준다.
    // PathVariable 정의필요
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);
        request.setAttribute("board",responseDTO);

        //권한체크 : error 403

        //1. 해당 페이지의 주인여부
        boolean owner = false;

        //2. 작성자 유저 아이디확인
        int boardUserId = responseDTO.getUserId();

        //3. 로그인 여부 확인
        User sessionUser = (User)session.getAttribute("sessionUser");
        if (sessionUser != null){
            if (boardUserId == sessionUser.getId()){
                owner = true;
            };
        }
        //HashMap 정의필요:

        request.setAttribute("owner",owner);

        return "board/detail";
    }
}