package org.example.commonlibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CommonLibraryApplication {
    public static void main(String[] args) {
      SpringApplication.run(CommonLibraryApplication.class, args);
        }
    }
