package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Daytime daytime() {
        var hour = LocalDateTime.now().getHour();
        return hour >= 6 && hour < 22 ? getDayTime() : getNightTime();
    }

    @Bean
    @Scope("prototype")
    public Day getDayTime() {
        return new Day();
    }

    @Bean
    @Scope("prototype")
    public Night getNightTime() {
        return new Night();
    }
}
