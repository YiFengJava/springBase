package xyz.yudong520.manageadmin.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import xyz.yudong520.manageadmin.common.util.R;
import xyz.yudong520.manageadmin.system.controller.dto.CompanyDto;
import xyz.yudong520.manageadmin.system.controller.valid.ValidReturnMessage;
import xyz.yudong520.manageadmin.system.entity.BasePageEntity;
import xyz.yudong520.manageadmin.system.entity.Company;
import xyz.yudong520.manageadmin.system.entity.User;
import xyz.yudong520.manageadmin.system.service.CompanyService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api("公司管理") //描述类/接口的主要用途
@Controller
@RequestMapping(value = "/company")
public class CompanyController  extends  BaseController{

    private final String PAGE_PREFIX_COMPANY="system/company";

    @Autowired
    private CompanyService  companyService;

    @GetMapping()
//    @ApiIgnore()// 忽略某类/方法/参数的文档
    public String companyManagerPage(){
        return  PAGE_PREFIX_COMPANY+"/company.html";
    }

    @GetMapping(value = "/list")
    @Transactional
    @ResponseBody
    public R getCompanyTableData(String name, String manager, BasePageEntity pageEntity){
        User currentUser = getCurrentUser();
        Page<Company> page =companyService.getCompanyPageTableByParams(name,manager,pageEntity);
        return  R.ok(page);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value="公司新增") //描述方法用途
//    @ApiImplicitParam(name = "company", value = "公司信息", dataType = "Company")  //描述方法的参数
//    @ApiImplicitParams({})  //描述方法的参数(Multi-Params)
    public  R add(@Valid  @ModelAttribute  CompanyDto company, @ApiIgnore(value = "检验异常信息封装") BindingResult result){
        if(result.hasErrors()){
            List<ValidReturnMessage> returnList=new ArrayList<>();
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError ob: allErrors){
                ValidReturnMessage validReturnMessage = new ValidReturnMessage(ob.getObjectName(), ob.getDefaultMessage());
                returnList.add(validReturnMessage);
            }
            return  R.build(500,"参数错误！",returnList);
        }
        return  R.ok();
    }


}
