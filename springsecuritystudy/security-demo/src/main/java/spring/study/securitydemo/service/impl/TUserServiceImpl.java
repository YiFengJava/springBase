package spring.study.securitydemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.study.securitydemo.dao.UserDao;
import spring.study.securitydemo.entity.TUser;
import spring.study.securitydemo.service.TUserService;

@Service
public class TUserServiceImpl implements TUserService{
    @Autowired
    private UserDao userDao;

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public TUser insertTUser(Long id) throws Exception {
//        TUser user=new TUser();
//        user.setId(id);
//        user.setName("yudong");
//        user.setAge(12);
//        TUser save = userDao.save(user);
//        delelt(id);
//        return save;
//    }
//,propagation= Propagation.SUPPORTS
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public void delelt(Long id) throws Exception {
        if(id==100){
            throw  new Exception();
        }
        userDao.deleteById(id);
    }

    @Override
    public TUser insertTUser(Long id) throws Exception {
        return null;
    }
}
