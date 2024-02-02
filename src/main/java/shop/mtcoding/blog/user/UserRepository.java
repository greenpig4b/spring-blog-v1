package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


// 질문 1 :
@Repository
public class UserRepository {
    private EntityManager em;

    // 질문1 :  엔티티메니저 만들고 나서 생성자를 만들어야하는 이유?(데이터 담는곳)인데 데이터를 담으려면 생성자를 초기해야하는지 기본 개념이 필요

    @Transactional //붙지않으면 insert하지않음 select는 리드라서 상관없음
    public void save2(UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());
        em.persist(user);

    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from taekwan_tb where username = ? and password = ?",User.class);
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());

        User user = (User)  query.getSingleResult();
        return user;
    }
}
