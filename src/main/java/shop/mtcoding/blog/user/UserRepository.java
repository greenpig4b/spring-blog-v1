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


    @Transactional
    public void save(UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("insert into user_tb(username, password, email) values(?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());
        query.executeUpdate();
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from taekwan_tb where username = ? and password = ?",User.class);
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());

        User user = (User)  query.getSingleResult();
        return user;
    }

    public User findByUsername(String username){
        Query query = em.createNativeQuery("select * from user_tb where username=?",User.class);
        query.setParameter(1,username);
        try{
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }

}
