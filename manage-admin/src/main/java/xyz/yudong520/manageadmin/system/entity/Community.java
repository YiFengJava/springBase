package xyz.yudong520.manageadmin.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="tb_community")
public class Community implements Serializable {
    @Id
    private String id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column
    private String address;

    @ManyToOne(optional=false)
    private Company company;
    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "tb_role_community",joinColumns = { @JoinColumn(referencedColumnName = "id",name="cid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

}
