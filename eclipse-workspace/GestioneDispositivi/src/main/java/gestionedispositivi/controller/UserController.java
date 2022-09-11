package gestionedispositivi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestionedispositivi.auth.user.User;
import gestionedispositivi.auth.user.UserService;

@RestController
@RequestMapping("/utenti")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<User>> getAllUser() {

		return ResponseEntity.ok(userService.getAllUser());
	}

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		return ResponseEntity.ok(userService.findById(id));
	}

	@PostMapping("/crea")
	public ResponseEntity<User> postUser(@RequestBody User user) {

		return ResponseEntity.ok(userService.postUser(user));
	}

	@DeleteMapping("/elimina/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("Deleted user");
	}
	
	@PutMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) {

		return ResponseEntity.ok(userService.putUser(id, user));
	}

}
