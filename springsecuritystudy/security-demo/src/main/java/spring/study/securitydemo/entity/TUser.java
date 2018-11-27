package spring.study.securitydemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="t_user")
public class TUser {

    @Id
    private Long id;

    @Column(length =50)
    private String name;

    @Column
    private String password;

    @Column
    private Integer age;
}
