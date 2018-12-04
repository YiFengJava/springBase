package xyz.yudong520.manageadmin.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.yudong520.manageadmin.system.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    @Query(value = "select u from User u where u.username=:username")
    public User getUserByUsername(@Param(value = "username") String username);
}
