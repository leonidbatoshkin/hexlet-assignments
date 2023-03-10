package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeEmails = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .map(email -> email.split("@")[1])
                .filter(freeEmails::contains)
                .count();
    }
}
