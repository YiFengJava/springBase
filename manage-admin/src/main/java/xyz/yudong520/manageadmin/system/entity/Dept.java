package xyz.yudong520.manageadmin.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="tb_dept")
public class Dept {
    @Id
    private String id;
    @Column(name = "name",nullable=false)
    private String name;
    @Column(name = "code",nullable=false)
    private String code;
    @Column(name = "pcode",nullable=false)
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
