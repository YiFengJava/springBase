package xyz.yudong520.manageadmin.system.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
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
    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "tb_role_community",joinColumns = { @JoinColumn(name = "rid",referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="cid") })
    private Set<Community> communitySet;
    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "tb_role_dept",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="did") })
    private Set<Dept> deptSet;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_role_user",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "userId",name="uid") })
    private Set<User> userSet;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_role_permissions",joinColumns = { @JoinColumn(referencedColumnName = "id",name="rid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="pid") })
    private Set<Permissions> permissionsSet;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(value, role.value) &&
                Objects.equals(descs, role.descs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, descs);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}
