package gestionedispositivi.dto;

import java.util.HashSet;
import java.util.Set;

import gestionedispositivi.auth.roles.Role;
import gestionedispositivi.device.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Set<Device> dispositivi = new HashSet<Device>();
	private Set<Role> roles = new HashSet<Role>();

}
