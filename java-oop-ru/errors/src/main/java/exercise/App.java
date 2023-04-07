package exercise;

class App {
    public static void printSquare(Circle circle) {
        try {
            if (circle.getRadius() < 0) {
                throw new NegativeRadiusException("Не удалось посчитать площадь");
            }
            System.out.println((int) Math.round(circle.getSquare()));
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
