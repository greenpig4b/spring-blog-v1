package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;


// 질문 1 :
@Repository
public class UserRepository {
    private EntityManager em;

    // 질문1 :  엔티티메니저 만들고 나서 생성자를 만들어야하는 이유?(데이터 담는곳)인데 데이터를 담으려면 생성자를 초기해야하는지 기본 개념이 필요
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("insert into taekwan_tb(username,password,email) values(?,?,?)");
        query.setParameter(1,"ssar");
        query.setParameter(2,"1234");
        query.setParameter(3,"ssar@naver.com");

        query.executeUpdate();
    }

    @Transactional //붙지않으면 insert하지않음 select는 리드라서 상광없음
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
