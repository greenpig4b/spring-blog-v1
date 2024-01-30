package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

// DAO 알아서 new
@Repository
public class UserRepository {
    private EntityManager em;

    //데이터 담는곳
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


    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username = ? and password = ?",User.class);
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());

        User user = (User)query.getSingleResult();
        return user;
    }

    //하이버 네이트
    @Transactional //붙지않으면 insert하지않음 select는 리드라서 상광없음
    public void save2(UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);

    }

}


