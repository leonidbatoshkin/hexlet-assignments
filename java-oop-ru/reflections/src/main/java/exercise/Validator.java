package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class Validator {
        public static List<String> validate(Address address) {
            List<String> list = new ArrayList<>();
        try {
            for (Field field : address.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(NotNull.class)) {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        list.add(field.getName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
