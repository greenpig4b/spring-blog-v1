package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor // 정의필요!
@Repository
public class BoardRePository {
    private final EntityManager em; // entity 찍혀있는 테이블자료 찾아옴

    private final BoardRePository boardRePository;
    //DAO 매서드를 모아두는 공간

    //페이징네이션을 위한 매서드 : 확인필요!
    public int count(){
        Query query = em.createNativeQuery("select count(*) from taekwanBoard_tb",Board.class);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }


    // 페이지 리스트 가져오는 매서드
    public List<Board> findAll(int page){
        int value = page;
        final int COUNT = 3;

        //limit에 붙은 첫번째 ? 는 보드리스트에서 몇번째 행을 보여줄꺼냐
        //limit에 붙는 두번쨰 ? 는 화면에 몇개씩 보여줄꺼냐
        Query query = em.createNativeQuery("select * from taekwanBoard_tb order by id desc limit ?,?", Board.class);
        query.setParameter(1,page);
        query.setParameter(2,COUNT);

        List<Board> boardList = query.getResultList(); // 리스트에 적혀있는거 싹다 가져옴
        return boardList;
    }

}
