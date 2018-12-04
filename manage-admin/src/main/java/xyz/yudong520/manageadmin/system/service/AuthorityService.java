package xyz.yudong520.manageadmin.system.service;

import xyz.yudong520.manageadmin.system.entity.Permissions;

import java.util.List;
import java.util.Set;

public interface AuthorityService {

    /**
     * 根据角色id的集合查询到所以的权限
     * @param rid
     * @return
     */
    Set<Permissions> getPermisseionsTableByRoles(Set<String> rid);
}
