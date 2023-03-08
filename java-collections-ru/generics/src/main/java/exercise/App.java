package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

class App {

    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> booksList, Map<T, T> where) {
        List<Map<T, T>> resultList = new ArrayList<>(booksList);
        for (Map<T, T> libraryBook : booksList) {
            if (!isRelevantBook(libraryBook, where)) {
                resultList.remove(libraryBook);
            }
        }
        return resultList;
    }

    private static <T> boolean isRelevantBook(Map<T, T> listBook, Map<T, T> whereBook) {
        for (Entry<T, T> book : whereBook.entrySet()) {
            if (!(listBook.containsKey(book.getKey()) && listBook.containsValue(book.getValue())))
                return false;
        }
        return true;
    }
}
