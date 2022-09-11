package gestionedispositivi.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import gestionedispositivi.auth.roles.ERole;
import gestionedispositivi.auth.roles.Role;
import gestionedispositivi.auth.roles.RoleRepository;

@Component
public class RoleRunner implements ApplicationRunner {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);	
		roleRepository.save(user);

	}

}
