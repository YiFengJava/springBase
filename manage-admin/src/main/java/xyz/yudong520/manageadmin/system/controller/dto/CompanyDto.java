package xyz.yudong520.manageadmin.system.controller.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.yudong520.manageadmin.core.valid.MyValid;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@ApiModel(value="公司对象",description="公司信息")
public class CompanyDto {
    @ApiModelProperty(value = "主键id",required=true,name = "id",dataType="string")
    private String id;

    @ApiModelProperty(value = "公司名称",required=true)
    @NotNull(message = "公司名称不能为空")
    private String name;

    @ApiModelProperty(value = "公司编码",required=true)
    private String code;

    @MyValid(message = "测试自定义校验注解")
    @ApiModelProperty(value = "公司描述",required=true)
    private String descs;

    @ApiModelProperty(value = "公司管理人",required=true)
    private String manager;

    @ApiModelProperty(value = "公司父级编码",required=true)
    private String pcode;

    @ApiModelProperty(value = "公司电话",required=true)
    private String tell;

    @ApiModelProperty(value = "创建时间",required=true)
    private Date createdTime;
}
