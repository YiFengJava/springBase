package xyz.yudong520.manageadmin.system.entity;

import lombok.Data;

@Data
public class BasePageEntity {
    //当前页码
    private Integer limit;
    //每页的数据条数
    private Integer offset;
    private String sort;
    private String order;
}
