package com.example.truphone.Device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private final Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> addDevice(@RequestBody DeviceDTO deviceDTO) {
        try {
            deviceService.addDevice(deviceDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<Device> getDeviceById(@PathVariable int deviceId) {
        try {
            Device device = deviceService.getDeviceById(deviceId);
            return new ResponseEntity<>(device, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> deviceList = deviceService.getAllDevices();
        return new ResponseEntity<>(deviceList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> addDevice(@PathVariable int id, @RequestBody DeviceDTO deviceDTO) {
        try {
            deviceService.updateDevice(id, deviceDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        deviceService.deleteDevice(id);
    }

    @GetMapping("/search/{brandName}")
    public ResponseEntity<Device> getDeviceByBrand(@PathVariable String brandName) {
        Device device = deviceService.getDeviceByBrand(brandName);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
}
