package gestionedispositivi.dto;

import gestionedispositivi.auth.roles.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private Long id;
	private ERole roleName;

}
