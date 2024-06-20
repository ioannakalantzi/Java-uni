public class BluRayPlayer extends ImageandSound {
    public BluRayPlayer(int quantity, String code, String modelName, int year, String manufacturer, double price, String type, int resolution, String format ){
        this.quantity = quantity;
        this.code = code;
        this.modelName = modelName;
        this.year = year;
        this.manufacturer = manufacturer;
        this.price = price;
        this.type = type;
        this.resolution = resolution;
        this.playbackFormat = format;
    }
    
    private String playbackFormat;
    private int resolution;

    public String getPlaybackFormat() {
        return playbackFormat;
    }

    public void setPlaybackFormat(String playbackFormat) {
        this.playbackFormat = playbackFormat;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }
}