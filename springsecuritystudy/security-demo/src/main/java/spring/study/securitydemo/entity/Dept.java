package spring.study.securitydemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tb_dept")
public class Dept {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String pcode;
    @Column
    private String descs;

    @ManyToOne(optional=false)
    private Company company;
    @ManyToMany()
    @JoinTable(name = "tb_role_dept",joinColumns = { @JoinColumn(referencedColumnName = "id",name="did") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

}
