public class Camera extends ImageandSound {
    public Camera(int quantity, String code, String modelName, int year, String manufacturer, double price, String type, double megapixels, double opticalZoom, double digitalZoom, double screenSize ){
        this.quantity = quantity;
        this.code = code;
        this.modelName = modelName;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
        this.type = type;
        this.megapixels = megapixels;
        this.opticalZoom = opticalZoom;
        this.digitalZoom = digitalZoom;
        this.screenSize = screenSize;
    }
    
    
    private double megapixels;
    private double opticalZoom;
    private double digitalZoom;
    private double screenSize;

    public double getMegapixels() {
        return megapixels;
    }

    public void setMegapixels(double megapixels) {
        this.megapixels = megapixels;
    }

    public double getOpticalZoom() {
        return opticalZoom;
    }

    public void setOpticalZoom(double opticalZoom) {
        this.opticalZoom = opticalZoom;
    }

    public double getDigitalZoom() {
        return digitalZoom;
    }

    public void setDigitalZoom(double digitalZoom) {
        this.digitalZoom = digitalZoom;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}