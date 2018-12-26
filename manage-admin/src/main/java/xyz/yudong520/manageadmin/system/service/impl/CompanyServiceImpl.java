package xyz.yudong520.manageadmin.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import xyz.yudong520.manageadmin.system.dao.CompanyRepository;
import xyz.yudong520.manageadmin.system.entity.BasePageEntity;
import xyz.yudong520.manageadmin.system.entity.Company;
import xyz.yudong520.manageadmin.system.service.CompanyService;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl  implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Page<Company> getCompanyPageTableByParams(String name, String manager, BasePageEntity pageEntity) {
        Specification<Company> specification = new Specification<Company>() {
            @Override
            public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(name)){
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%" );
                    list.add(predicate);
                }
                if(StringUtils.isNotBlank(manager)){
                    Predicate predicate = criteriaBuilder.like(root.get("manager").as(String.class),"%"+manager+"%"  );
                    list.add(predicate);
                }
                if(list.size()==0){
                    return  null;
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        };
        Sort sort=null;
        List<String> sortBy=new ArrayList<>();
        if(pageEntity!=null && StringUtils.isNotBlank(pageEntity.getSort())){
            String[] split = pageEntity.getSort().split(",");
            if(split!=null && split.length>0){
                for(String sp:split){
                    sortBy.add(sp);
                }
            }
            sort=new Sort(Sort.Direction.valueOf(pageEntity.getOrder().toUpperCase()),sortBy);
        }else{
            sort=new Sort(Sort.Direction.DESC,"id");
        }
        Integer limit = pageEntity.getLimit();
        Integer offset = pageEntity.getOffset();
        PageRequest of = PageRequest.of(limit, offset, sort);
        Page<Company> all = companyRepository.findAll(specification, of);
        return all;
    }

}
