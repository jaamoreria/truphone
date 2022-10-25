package com.example.truphone.Device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class DeviceService {
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void addDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setName(deviceDTO.getName());
        device.setBrand(deviceDTO.getBrand());
        device.setCreationDate(new Date());

        deviceRepository.save(device);
    }

    public Device getDeviceById(int id) {
        Device device = null;
        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if (optionalDevice.isPresent()) {
            device = optionalDevice.get();
        }

        return device;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public void updateDevice(int id, DeviceDTO deviceDTO) {
        Device device = getDeviceById(id);
        if (nonNull(device)) {
            device.setName(deviceDTO.getName());
            device.setBrand(deviceDTO.getBrand());
            device.setCreationDate(deviceDTO.getCreationDate());
            deviceRepository.save(device);
        }
    }

    public void deleteDevice(int id) {
        deviceRepository.deleteById(id);
    }

    public Device getDeviceByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }
}
