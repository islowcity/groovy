package slow.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "slow.city.*")
@SpringBootApplication
//@EnableConfigurationProperties({ApplicationProperties.class})
@EnableAutoConfiguration
public class SlowcityApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SlowcityApplication.class, args);
    }
}