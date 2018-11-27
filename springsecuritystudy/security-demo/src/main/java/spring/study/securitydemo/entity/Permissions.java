package spring.study.securitydemo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tb_permissions")
public class Permissions implements GrantedAuthority {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String code;
    @Column
    private String pcode;

    @ManyToMany()
    @JoinTable(name = "tb_role_permissions",joinColumns = { @JoinColumn(referencedColumnName = "id",name="pid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

    @Override
    public String getAuthority() {
        return "admin";
    }
}
