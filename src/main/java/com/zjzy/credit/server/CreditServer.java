package com.zjzy.credit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableScheduling
public class CreditServer {
    public static void main(String[] args) {
        SpringApplication.run(CreditServer.class, args);
    }
}
