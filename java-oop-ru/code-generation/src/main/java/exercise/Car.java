package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Value
@Getter
@AllArgsConstructor
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static Car unserialize(String json) throws IOException {
        return new ObjectMapper().readValue(json, Car.class);
    }
}
