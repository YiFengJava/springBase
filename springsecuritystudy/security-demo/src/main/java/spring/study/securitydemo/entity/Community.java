package spring.study.securitydemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tb_community")
public class Community {
    @Id
    private String id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column
    private String address;

    @ManyToOne(optional=false)
    private Company company;
    @ManyToMany()
    @JoinTable(name = "tb_role_community",joinColumns = { @JoinColumn(referencedColumnName = "id",name="cid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

}
