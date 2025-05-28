package dev.runner;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import dev.boot.bean.BeanUtilsOfSpring;

@Component
public class ApplicationRunnerStartup implements ApplicationRunner {

    private void testReadConfig() {
        System.out.println(new Date() + " [ApplicationRunnerStartup] Run method");
        BeanUtilsOfSpring.testCopyProperties();
        // BeanUtilsOfApache.testCopyProperties();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // testReadConfig();
    }

}
