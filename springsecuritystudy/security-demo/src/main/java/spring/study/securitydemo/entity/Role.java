package spring.study.securitydemo.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tb_role")
public class Role {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String descs;
    @ManyToMany()
    @JoinTable(name = "tb_role_community",joinColumns = { @JoinColumn(name = "rid",referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="cid") })
    private Set<Community> communitySet;
    @ManyToMany()
    @JoinTable(name = "tb_role_dept",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="did") })
    private Set<Dept> deptSet;
    @ManyToMany()
    @JoinTable(name = "tb_role_user",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "userId",name="uid") })
    private Set<User> userSet;

    @ManyToMany()
    @JoinTable(name = "tb_role_permissions",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="pid") })
    private Set<Permissions> permissionsSet;

}
