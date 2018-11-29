package xyz.yudong520.manageadmin.system.entity;

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
    @Column(name ="name",nullable = false)
    private String name;

    @Column(name ="value",nullable = false)
    private String value;

    @Column(name ="type",nullable = false)
    private String type;

    @Column(name ="code",nullable = false)
    private String code;

    @Column(name ="pcode",nullable = false)
    private String pcode;

    @Column(name = "descs")
    private String descs;

    @ManyToMany()
    @JoinTable(name = "tb_role_permissions",joinColumns = { @JoinColumn(referencedColumnName = "id",name="pid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

    @Override
    public String getAuthority() {
        return "admin";
    }
}
