package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

// DAO 알아서 new
@Repository
public class UserRepository {
    private EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public void save(UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("insert into user_tb(username,password,email) values(?,?,?)");
        query.setParameter(1,"ssar");
        query.setParameter(2,"1234");
        query.setParameter(3,"ssar@naver.com");

        query.executeUpdate();
    }
    //하이버 네이트
    @Transactional
    public void save2(UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);

    }



}


