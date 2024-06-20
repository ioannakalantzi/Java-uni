public class Refrigerator extends HomeAppliance {
    public Refrigerator(int quantity, String code, String modelName, int year, String manufacturer, double price, String type, String energyClass, int capacity, int freezerCapacity){
        this.quantity = quantity;
        this.code = code;
        this.modelName = modelName;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
        this.type = type;
        this.energyClass = energyClass;
        this.capacity = capacity;
        this.freezerCapacity = freezerCapacity;
    }
    
    private String type;
    private int capacity;
    private int freezerCapacity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFreezerCapacity() {
        return freezerCapacity;
    }

    public void setFreezerCapacity(int freezerCapacity) {
        this.freezerCapacity = freezerCapacity;
    }
}