package gestionedispositivi.auth.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import gestionedispositivi.auth.roles.Role;
import gestionedispositivi.device.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@Column(length = 50)
	private String firstName;

	@Column(length = 50)
	private String lastName;

	@Column(length = 50)
	private String email;

	@NotBlank
	@Size(max = 100)
	private String password;

	@OneToMany
	@Column(length = 30)
	@JoinTable(name = "user_devices", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
	private Set<Device> devices = new HashSet<Device>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	public User(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

}
