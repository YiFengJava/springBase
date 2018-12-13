package xyz.yudong520.manageadmin.system.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import xyz.yudong520.manageadmin.system.dao.SystemDaoImpl;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.entity.User;
import xyz.yudong520.manageadmin.system.service.AuthorityService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
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

    @Cacheable(value = "user", key = "#root.targetClass + #username", unless = "#result eq null")
    @Override
    public String testEcach(String username) {
        log.info("在从数据库中去数据！");
        return "我在测试缓存";
    }

    /**
     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     * @param  gsid
     * @return
     */
    @Cacheable(value = "BasicDataCache",keyGenerator = "wiselyKeyGenerator",unless ="#result eq null")
    public User getTokenByGsid(String gsid){
        log.info("在从数据库中去数据！");
        User user = new User();
        user.setUsername("yudong");
        user.setAge(1);
        user.setNickName("yifenf");
        return  user;
    }


    //    /**
//     * @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
//     * unless 表示条件表达式成立的话不放入缓存
//     * @param username
//     * @return
//     */
//    @Cacheable(value = "user", key = "#root.targetClass + #username", unless = "#result eq null")
//    public Person getPersonByName(String username) {
//        Person person = personRepo.getPersonByName(username);
//        return person;
//    }
//
//    /**
//     * @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
//     * @param person
//     * @return
//     */
//    @CachePut(value = "user", key = "#root.targetClass + #result.username", unless = "#person eq null")
//    public Person savePerson(Person person) {
//        return personRepo.savePerson(person);
//    }
//
//    /**
//     * @CacheEvict 应用到删除数据的方法上，调用方法时会从缓存中删除对应key的数据
//     * @param username
//     * @return
//     */
//    @CacheEvict(value = "user", key = "#root.targetClass + #username", condition = "#result eq true")
//    public boolean removePersonByName(String username) {
//        return personRepo.removePersonByName(username) > 0;
//    }
//
//    public boolean isExistPersonName(Person person) {
//        return personRepo.existPersonName(person) > 0;
//    }
}
