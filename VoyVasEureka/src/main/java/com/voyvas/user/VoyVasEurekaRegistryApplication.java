package com.voyvas.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class VoyVasEurekaRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoyVasEurekaRegistryApplication.class, args);
    }

}
