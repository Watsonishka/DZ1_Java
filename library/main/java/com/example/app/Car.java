public class Car {
    private String model;
    private double power;
    private int manufacturedYear;

    public Car(String model, double power, int manufacturedYear) {
        this.model = model;
        this.power = power;
        this.manufacturedYear = manufacturedYear;
    }

    public String getModel() {
        return model;
    }

    public double getPower() {
        return power;
    }

    public int getManufacturedYear() {
        return manufacturedYear;
    }
}
