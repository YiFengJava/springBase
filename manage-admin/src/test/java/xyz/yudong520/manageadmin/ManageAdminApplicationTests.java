package xyz.yudong520.manageadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.yudong520.manageadmin.system.entity.Company;
import xyz.yudong520.manageadmin.system.entity.Permissions;
import xyz.yudong520.manageadmin.system.service.AuthorityService;
import xyz.yudong520.manageadmin.system.service.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageAdminApplicationTests {


	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private CompanyService companyService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void tea(){
		HashSet<String> objects = new HashSet<>();
		objects.add("1");
		objects.add("2");
		Set<Permissions> permisseionsTableByRoles = authorityService.getPermisseionsTableByRoles(objects);
	}

	@Test
	public void test1(){
//		Page<Company> page = (Page<Company >)companyService.getPage(null, null);
//		System.out.println(page.getContent().get(0).getDeptList());
	}

}
