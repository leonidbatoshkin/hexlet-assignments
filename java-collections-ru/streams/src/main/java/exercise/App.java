package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeEmails = List.of("@gmail.com", "@yandex.ru", "@hotmail.com");
        return emails.stream()
                .filter(email -> email.contains(freeEmails.get(0))
                        || email.contains(freeEmails.get(1))
                        || email.contains(freeEmails.get(2)))
                .count();
    }
}
