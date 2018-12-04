package xyz.yudong520.manageadmin.system.dao;

import org.springframework.stereotype.Repository;
import xyz.yudong520.manageadmin.system.entity.Permissions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Repository
public class SystemDaoImpl {
    @PersistenceContext
    private EntityManager em;

    public List<Permissions> getPermisseionsByRoles(Set<String> rid){
        String sql1="SELECT tp.* \n" +
                "FROM tb_permissions tp\n" +
                "LEFT JOIN tb_role_permissions trp ON trp.`pid`=tp.`id`\n" +
                "WHERE 1=1\n" ;
        String sql2="";
        if(rid!=null && rid.size()>0){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("AND trp.rid IN(");
            rid.forEach((String trid)->{
                stringBuffer.append("'"+trid+"',");
            });
            String s = stringBuffer.toString();
            sql2= s.substring(0, s.length() - 1)+")";
        }
        String sql=sql1+sql2;
        Query nativeQuery = em.createNativeQuery(
                sql,Permissions.class
        );
        List resultList = nativeQuery.getResultList();
        return resultList;
    }
}
