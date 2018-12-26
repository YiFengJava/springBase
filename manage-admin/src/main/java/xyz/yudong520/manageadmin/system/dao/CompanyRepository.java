package xyz.yudong520.manageadmin.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.yudong520.manageadmin.system.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,String>
        ,JpaSpecificationExecutor<Company> {

}
