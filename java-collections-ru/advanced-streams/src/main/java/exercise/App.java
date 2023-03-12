package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

class App {

    public static String getForwardedVariables(String file) {
        return Arrays.stream(file.split("\n"))
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.substring("environment=".length()))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.split(","))
                .flatMap(Stream::of)
                .filter(line -> line.startsWith("X_FORWARDED_"))
                .map(line -> line.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
