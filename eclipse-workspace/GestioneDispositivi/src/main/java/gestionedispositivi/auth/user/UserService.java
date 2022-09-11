package gestionedispositivi.auth.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gestionedispositivi.auth.roles.ERole;
import gestionedispositivi.auth.roles.Role;
import gestionedispositivi.auth.roles.RoleRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	public User findById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User not found");
		}
		return userRepository.findById(id).get();
	}

	public User postUser(User user) {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new EntityNotFoundException("User already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if(user.getRoles().isEmpty()) {
		Role r = roleRepository.findByRoleName(ERole.ROLE_USER).get();
		Set<Role> defaultRoles = new HashSet<Role>();
		defaultRoles.add(r);

		user.setRoles(defaultRoles);
		}
		
		return userRepository.save(user);
	}

	public User putUser(Long id, User user) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User not found");
		}

		User u = userRepository.findById(id).get();
		u.setUsername(user.getUsername());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setDevices(user.getDevices());
		u.setRoles(user.getRoles());

		return userRepository.save(u);
	}

	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new EntityNotFoundException("User not found");
		}
		userRepository.deleteById(id);
	}

	public List<UserResponse> getAllUsersBasicInformations() {
		return userRepository.findAll().stream()
				.map(user -> UserResponse.builder().userName(user.getUsername())
						.role(user.getRoles().stream().findFirst().get().getRoleName().name()).build())
				.collect(Collectors.toList());
	}

	public UserResponse getUserBasicInformations(String userName) {
		User u = userRepository.findByUsername(userName).get();

		return UserResponse.builder().userName(userName)
				.role(u.getRoles().stream().findFirst().get().getRoleName().name()).build();

	}

}
