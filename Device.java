public abstract class Device {
    public int quantity;
    public String code;
    public String modelName;
    public int year;
    public String manufacturer;
    public double price;

    public int getQuantity(){
        return quantity;
    } 

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int newQuantity(int n){
        return (quantity - n);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Device Code: " + code + "\nQuantity: " + quantity + "\nModel name: " + modelName +
        "\nYear: " + year + "\nManufacturer: " + manufacturer +  "\nPrice: " + price + "\n---------------";
    }
}
