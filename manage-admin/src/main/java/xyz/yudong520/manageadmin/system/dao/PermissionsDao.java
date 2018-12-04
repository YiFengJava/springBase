package xyz.yudong520.manageadmin.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.yudong520.manageadmin.system.entity.Permissions;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionsDao  extends JpaRepository<Permissions, String> {
//    @Query(value="select p from Permissions p where p.id in ")
//    List<Permissions> getPermisseionsByRoles(Set<String> rid);
}
