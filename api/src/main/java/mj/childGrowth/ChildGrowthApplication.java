package mj.childGrowth;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class ChildGrowthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildGrowthApplication.class, args);
    }
}