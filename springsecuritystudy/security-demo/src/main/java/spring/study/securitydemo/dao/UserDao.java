package spring.study.securitydemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.study.securitydemo.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = :username") //不加nativeQuery应使用HQL
    User getUserByUsername(@Param(value = "username") String username);

    @Query("select u from User u where u.userId = :userId") //不加nativeQuery应使用HQL
    User getUserByUserId(String userId);
}
