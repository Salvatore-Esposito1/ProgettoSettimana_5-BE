package gestionedispositivi.device;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	
	public Page<Device> findAll(Pageable pageable) {
		return deviceRepository.findAll(pageable);
	}
	
	
	public Device findById(Long id) {
		if(!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		
		return deviceRepository.findById(id).get();
	}
	
	
	public void deleteDevice(Long id) {
		if (!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		deviceRepository.deleteById(id);
	}
	
	public Device postDevice(Device device) {
		return deviceRepository.save(device);
	}

	public Device putDevice(Long id, Device device) {
		if (!deviceRepository.existsById(id)) {
			throw new EntityNotFoundException("Device not found");
		}
		Device dev = deviceRepository.findById(id).get();
		dev.setStatus(device.getStatus());
		dev.setType(device.getType());
		
		return deviceRepository.save(dev);
	}
	
}
