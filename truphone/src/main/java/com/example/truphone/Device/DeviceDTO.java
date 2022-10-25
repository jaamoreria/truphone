package com.example.truphone.Device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    @Getter
    @Setter
    private String name;
    @Getter @Setter
    private String brand;
    @Getter @Setter
    private Date creationDate;
}
