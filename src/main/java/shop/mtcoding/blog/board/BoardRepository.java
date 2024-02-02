package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public int count(){
        Query query = em.createNativeQuery("select count(*) from board_tb");
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List<Board> findAll(int page){
        int value = page* Constant.PAGING_COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, Constant.PAGING_COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    //join 은 임시로 데이터를 받을 장소를 만들기 위해서  Board 안에 따로 테이블을만들지않고 다른방법 사용
    public BoardResponse.DetailDTO findById(int id) {
        // entity가 아닌것은 jpa가 파싱해주지않는다.보드에 못받음 결과를 받을 항아리가 없음. 조인해서 entity가 아님
        // java.lang.RuntimeException: No constructor taking: 오류는 entity가 아니기때문에 select,뒤에 테이블자료를 가져올수없어서 에러가 터진다... [error 해결]
        //	shop.mtcoding.blog.board.[Board] 이보드가 튀어나오면 안된다.
        // 받아올값이 하나면 single 아니면 ResultList
        Query query = em.createNativeQuery("select bt.id, bt.title, bt.content, bt.created_at, bt.user_id, ut.username from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id = ?");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO = rm.uniqueResult(query,BoardResponse.DetailDTO.class);

        return responseDTO;

        //왜 DTO인가 통신을해서 데이터베이스값을 조회해서 받아오는거
    }


}