package spring.study.securitydemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="tb_company")
public class Company {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private String descs;
    @Column
    private String manager;
    @Column
    private String pcode;
    @Column
    private String tell;

    @Temporal(TemporalType.DATE)
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdTime;

    @OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="company",orphanRemoval=false)
    private List<Community> communityList;

    @OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="company",orphanRemoval=false)
    private List<Dept> deptList;

}
