package exercise;

class Flat implements Home {
    private final int area;
    private final int balconyArea;
    private final int floor;

    public Flat(int area, int balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return (double) area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        if (this.getArea() == another.getArea()) {
            return 0;
        }
        return this.getArea() > another.getArea() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
