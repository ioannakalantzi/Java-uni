public class Television extends ImageandSound {
    public Television(int quantity, String code, String modelName, int year, String manufacturer, double price, String type, double screenSize, int resolution, String ports ){
        this.quantity = quantity;
        this.code = code;
        this.modelName = modelName;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
        this.type = type;
        this.screenSize = screenSize;
        this.resolution = resolution;
        this.ports = ports;
    }
    
    private double screenSize;
    private int resolution;
    private String ports;

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }
}