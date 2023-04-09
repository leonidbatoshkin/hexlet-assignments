package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@AllArgsConstructor
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
