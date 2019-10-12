package io.projetocoletarsu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swagger2SpringBoot {

    private static final Logger log = LoggerFactory.getLogger(Swagger2SpringBoot.class);


    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);

    }

}
