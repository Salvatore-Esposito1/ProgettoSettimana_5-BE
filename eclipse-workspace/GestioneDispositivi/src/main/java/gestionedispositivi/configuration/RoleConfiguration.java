package gestionedispositivi.configuration;

import org.springframework.context.annotation.Configuration;

import gestionedispositivi.auth.roles.Role;

@Configuration
public class RoleConfiguration {
	
	public Role newRole() {
		return new Role();
	}
}
