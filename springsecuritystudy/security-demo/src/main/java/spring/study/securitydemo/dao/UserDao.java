package spring.study.securitydemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.securitydemo.entity.TUser;
@Repository
public interface UserDao extends JpaRepository<TUser, Long> {
}
