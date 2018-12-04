package xyz.yudong520.manageadmin.system.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="tb_role",indexes = {@Index(name = "role_index_id_and_value",  columnList="id,value", unique = true),
        }
//        ,uniqueConstraints = {
//        @UniqueConstraint(name = “索引名称”, columnNames = {“字段1”,“字段2”})
//}
    )
public class Role implements Serializable {
    @Id
    private String id;

    @Column(name = "name",nullable=false)
    private String name;

    @Column(name = "value",nullable=false)
    private String value;

    @Column(name = "descs")
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
