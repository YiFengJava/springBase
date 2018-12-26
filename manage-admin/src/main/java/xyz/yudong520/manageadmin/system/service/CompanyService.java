package xyz.yudong520.manageadmin.system.service;

import org.springframework.data.domain.Page;
import xyz.yudong520.manageadmin.system.entity.BasePageEntity;
import xyz.yudong520.manageadmin.system.entity.Company;

public interface CompanyService {
    Page<Company> getCompanyPageTableByParams(String name, String manager, BasePageEntity pageEntity);
}
