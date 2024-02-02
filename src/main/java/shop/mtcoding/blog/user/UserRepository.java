package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;



// DAO 알아서 new
@Repository

public class UserRepository {
    private EntityManager em;

    //데이터 담는곳
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional //붙지않으면 insert하지않음 select는 리드라서 상광없음, 같이 사용할일 없는 독립적인 트렉젝션
    public void save(UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("insert into user_tb(username, password, email) values(?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());
        query.executeUpdate();
    }

//    public void save2(UserRequest.JoinDTO requestDTO){
//        User user = new User();
//        user.setUsername(requestDTO.getUsername());
//        user.setPassword(requestDTO.getPassword());
//        user.setEmail(requestDTO.getEmail());
//
//        em.persist(user);
//
//    }
    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username = ? and password = ?",User.class);
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());

        try{
            User user = (User)query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }

    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username=?", User.class);
        query.setParameter(1, username);
        try {
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }

}


