package dev.profitsoft.hw4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Main {
    public static void main(String[] args) {
        // If you have issues with running in IntelliJ IDEA (because of multimodule structure):
        // Select Run/Debug configuration -> Edit configurations -> This one
        // -> Modify options -> Working directory -> $MODULE_WORKING_DIR$
        SpringApplication.run(Main.class, args);
    }
}