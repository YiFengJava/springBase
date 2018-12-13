package xyz.yudong520.manageadmin.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name="tb_permissions")
public class Permissions implements GrantedAuthority ,Serializable {
    @Id
    private String id;
    @Column(name ="name",nullable = false)
    private String name;

    @Column(name ="ionc")
    private String ionc;

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

    @JsonIgnore
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "tb_role_permissions",joinColumns = { @JoinColumn(referencedColumnName = "id",name="pid") }, inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "id",name="rid") })
    private Set<Role> roleSet;

    @Override
    public String getAuthority() {
        return "admin";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(ionc, that.ionc) &&
                Objects.equals(value, that.value) &&
                Objects.equals(type, that.type) &&
                Objects.equals(code, that.code) &&
                Objects.equals(pcode, that.pcode) &&
                Objects.equals(descs, that.descs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, ionc, value, type, code, pcode, descs);
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ionc='" + ionc + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", pcode='" + pcode + '\'' +
                ", descs='" + descs + '\'' +
                '}';
    }
}
