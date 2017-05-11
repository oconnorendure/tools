package net.itxiu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by huangshaokang on 17/3/28.
 */
@ComponentScan(basePackages = {"net.itxiu"})
@SpringBootApplication
@EnableEurekaClient
public class ItxiuServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ItxiuServerApplication.class).web(true).run(args);
    }
}
