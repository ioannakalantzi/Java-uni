import java.util.ArrayList;

public class Store {
    private ArrayList<Device> availableDevices;
    private ArrayList<Order> orders;
    private ArrayList<Sale> sales;

    public Store() {
        availableDevices = new ArrayList<>();
        orders = new ArrayList<>();
        sales = new ArrayList<>();
    }

    
    public void addAvailableDevice(Device device) {
        availableDevices.add(device);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

}
