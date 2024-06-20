public class WashingMachine extends HomeAppliance {
    public WashingMachine(int quantity, String code, String modelName, int year, String manufacturer, double price, String energyClass, int capacity, int rpm){
        this.quantity = quantity;
        this.code = code;
        this.modelName = modelName;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
        this.energyClass = energyClass;
        this.capacity = capacity;
        this.rpm = rpm;
    }
    
    private int capacity;
    private int rpm;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }
}