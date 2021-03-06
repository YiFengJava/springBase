package xyz.yudong520.manageadmin.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="tb_company")
public class Company implements Serializable {
    @Id
    private String id;
    @Column(name = "name",nullable=false)
    private String name;
    @Column(name = "code",nullable=false)
    private String code;
    @Column
    private String descs;
    @Column
    private String manager;
    @Column(name = "pcode",nullable=false)
    private String pcode;
    @Column
    private String tell;

    @Temporal(TemporalType.DATE)
    //出参时间格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //入参时，请求报文只需要传入yyyymmddhhmmss字符串进来，则自动转换为Date类型数据
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdTime;

    @ApiModelProperty(hidden=true)
    @JsonIgnore
    @OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="company",orphanRemoval=false)
    private List<Community> communityList;

    @ApiModelProperty(hidden=true)
    @JsonIgnore
    @OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY,mappedBy="company",orphanRemoval=false)
    private List<Dept> deptList;

}
