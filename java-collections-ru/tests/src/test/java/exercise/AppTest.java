package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> actual = App.take(list, 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testTakeTail() {
        List<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 3, 4));
        List<Integer> actual = App.take(list, 1);
        List<Integer> expected = new ArrayList<>(List.of(4));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testTakeFullList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(4, 1, 1, 0));
        List<Integer> actual = App.take(list, 4);
        List<Integer> expected = new ArrayList<>(Arrays.asList(4, 1, 1, 0));
        assertThat(actual).isEqualTo(expected);
    }
}
