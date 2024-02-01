package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@RequiredArgsConstructor
@Controller
public class BoardController {

    private  final HttpSession session;
    private final BoardRePository boardRePository;


    // http://localhost:8080?page=0
    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0")int page) {
        List<Board> boardList = boardRePository.findAll(page);
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }
}
