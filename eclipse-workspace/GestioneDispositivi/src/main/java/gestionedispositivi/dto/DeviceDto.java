package gestionedispositivi.dto;

import gestionedispositivi.device.DeviceStatus;
import gestionedispositivi.device.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {


	private Long id;
	private DeviceType type;
	private DeviceStatus status;

}
