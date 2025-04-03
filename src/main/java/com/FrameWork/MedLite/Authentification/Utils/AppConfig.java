/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.MedLite.Authentification.Utils;

import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Administrator
 */
//@Configuration
public class AppConfig {

    @Value("${spring.jackson.time-zone}")
    private String appTimeZone;

    @Bean
    public String appTimeZoneId() {  // Changed method name and return type
        return appTimeZone;
    }
}
