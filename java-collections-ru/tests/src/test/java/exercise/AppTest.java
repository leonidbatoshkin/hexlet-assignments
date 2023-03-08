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
    void testTake2() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> expected1 = new ArrayList<>();
        List<Integer> result1 = App.take(list1, 3);
        assertThat(result1).isEqualTo(expected1);
    }

    @Test
    void testTake3() {
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> result2 = App.take(list2, 7);
        assertThat(result2).isEqualTo(expected2);
    }

    @Test
    void testTake4() {
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected3 = new ArrayList<>();
        List<Integer> result3 = App.take(list3, 0);
        assertThat(result3).isEqualTo(expected3);
    }
}
