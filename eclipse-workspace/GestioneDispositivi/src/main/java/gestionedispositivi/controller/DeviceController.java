package gestionedispositivi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import gestionedispositivi.device.Device;
import gestionedispositivi.device.DeviceService;

@RestController
@RequestMapping("/dispositivi")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Device>> findAll(Pageable pageable) {
		return ResponseEntity.ok(deviceService.findAll(pageable));
	}

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Device> findById(@PathVariable Long id) {

		return ResponseEntity.ok(deviceService.findById(id));
	}

	@PostMapping("/crea")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<Device> postDevice(@RequestBody Device device) {

		return ResponseEntity.ok(deviceService.postDevice(device));
	}

	@DeleteMapping("/elimina/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteDevice(@PathVariable Long id) {

		deviceService.deleteDevice(id);
		return ResponseEntity.ok("Deleted user");
	}
	
	@PutMapping("/modifica/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<Device> putDevice(@PathVariable Long id, @RequestBody Device device) {

		return ResponseEntity.ok(deviceService.putDevice(id, device));
	}
}
