package xyz.yudong520.manageadmin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yudong520.manageadmin.system.dao.SystemDaoImpl;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.service.AuthorityService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private SystemDaoImpl systemDao;

    /**
     * 查询角色的所有的权限集合，给权限列表去重
     * @param rid
     * @return
     */
    @Override
    public Set<Permissions> getPermisseionsTableByRoles(Set<String> rid) {
        List<Permissions> permisseionsByRoles = systemDao.getPermisseionsByRoles(rid);
        Set<Permissions> permissions=new HashSet<>();
        for (Permissions permissions1:permisseionsByRoles){
            permissions.add(permissions1);
        }
        return permissions;
    }
}
