/*
Ioanna Kalantzi p3210057
Georgia Kalogianni p3210058
Team: 014
*/

import java.time.LocalDate;  
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class mainApp{
    private static List<Device> availableDevices;
    private List<Order> orders;
    private List<Sale> sales;
    private int nextOrderCode;
    private int nextSaleCode;
    private int typeChoice;
    public int discount;
    private double discountedprice;

    Device device;
    Order order;
    Order chosenOrder;

    public mainApp() {
        availableDevices = new ArrayList<>();
        orders = new ArrayList<>();
        sales = new ArrayList<>();
        nextOrderCode = 0;
        nextSaleCode = 0;
    }

    private void initializeData() {
        availableDevices.add(new Television(5, "0", "Smart", 2019, "LG", 296.58,"Plasma", 43, 1080, "HDMI"));
        availableDevices.add(new Television(2, "1", "Smart", 2020, "Samsung", 406.63,"LED", 55, 1080, "HDMI"));
        availableDevices.add(new BluRayPlayer(0, "2", "DMR-BS17", 2013, "Panasonic", 469.45,"Blu-Ray", 1080, "DVB-S" ));
        availableDevices.add(new BluRayPlayer(1, "3", "DMR-UBS7", 2013, "Panasonic", 479.99,"Blu-Ray", 1080, "BD-R" ));
        availableDevices.add(new Camera(4, "4", "Coolpix P1000", 2015, "Nikon", 1059, "Compact", 16, 125, 125, 3.2));
        availableDevices.add(new Camera(5, "5", "PowerShot G7", 2014, "Canon", 599,"Compact", 20.9, 4.2, 4, 3));
        availableDevices.add(new Consoles(0, "6", "PS5", 2020, "Sony", 508.50,"PS5", "x86-64-AMD Ryzen Zen 8 Cores", "4K", "3D AudioTech", 825));
        availableDevices.add(new Consoles(6, "7", "PS4", 2013, "Sony", 394.57,"PS4", "x86-64 AMD “Jaguar”", "1080", "x86-64 AMD “Jaguar”", 500));
        availableDevices.add(new Refrigerator(9, "8", "Total NoFrost", 2020, "Samsung", 1299,"closet", "F", 409, 225));
        availableDevices.add(new Refrigerator(7, "9", "NoFrost", 2020, "Bosh", 1083,"one door", "E", 406, 120));
        availableDevices.add(new WashingMachine(4, "10", "WGG254A1GR", 2019, "Bosh", 749,"C", 10, 1400));
        availableDevices.add(new WashingMachine(0, "11", "Inverter Direct Drive", 2017, "LG", 619,"B", 10, 1400));
    }

    public void displayAvailableDevices() throws ParseException {
        System.out.println("Available Devices:");

        for (Device device : availableDevices) {
            System.out.println(device);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose device category:");
        System.out.println("0=Image and Sound");
        System.out.println("1=Gaming");
        System.out.println("2=Home Appliances");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        if (categoryChoice == 0){
            System.out.println("Choose Device:");
            System.out.println("0=Television(Codes: 0, 1)");
            System.out.println("1=Blu-Ray/DVD players(Codes: 2, 3)");
            System.out.println("2=Cameras(Codes: 4, 5)");
            typeChoice = scanner.nextInt();
            discount = 25;
            scanner.nextLine();
        }
        if (categoryChoice == 1){
            System.out.println("Choose Device:");
            System.out.println("0=Consoles(Codes: 6, 7)");
            typeChoice = scanner.nextInt();
            discount = 10;
            scanner.nextLine();
        }
        if (categoryChoice == 2){
            System.out.println("Choose Device:");
            System.out.println("0=Refrigerators(Codes: 8, 9)");
            System.out.println("1=Washing Machines(Codes: 10, 11)");
            typeChoice = scanner.nextInt();
            discount = 20;
            scanner.nextLine();
        }
                

        System.out.println("Choose specific device:");
        Integer modelChoice = scanner.nextInt();

        for (Device device : availableDevices) {
                System.out.println("Characteristics of device:");
                device = availableDevices.get(modelChoice);
                System.out.println(device);

                if (device.getQuantity() > 0) {
                    System.out.println("Do you want to buy this product?");
                    System.out.println("1=Yes");
                    System.out.println("2=No");
                    int Buy = scanner.nextInt();
                    scanner.nextLine();

                    switch (Buy) {
                        case 1:
                            sellDevice(device);
                            break;
                        case 2:
                            break;
                    }
                }
                else {
                    System.out.println("Sorry, there any products left. Do you want to place an order?");
                    System.out.println("1=Yes");
                    System.out.println("2=No");
                    int Order = scanner.nextInt();
                    scanner.nextLine();

                    switch (Order) {
                        case 1:
                            orderDevice(device);
                            break;
                        case 2:
                            break;
                    }
                }

                break;
            
        }

    }

    private void sellDevice(Device device) throws ParseException {
        Date datenow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String mydate = format.format(datenow);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter info:");

        System.out.println("Full name:");
        String customerName = scanner.nextLine();

        System.out.println("Phone:");
        String customerPhone = scanner.nextLine();

        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        
        if (device.getQuantity() >= quantity) {
            double totalCost = device.getPrice() * quantity;
            discountedprice = totalCost*discount/100;
            totalCost = totalCost - discountedprice;

            device.setQuantity(device.newQuantity(quantity)) ;
            nextSaleCode++;
            Sale sale = new Sale(nextSaleCode, device, customerName, customerPhone, mydate, quantity, discount, discountedprice, totalCost);
            sales.add(sale);

            System.out.println("\n--- Successful sale ---");
            System.out.println("Sale info:");
            System.out.println(sale);

             
        } else {
            System.out.println("There isn't availability.");
        }

        System.out.println("Return to homepage.");
    }

    private void orderDevice(Device device) throws ParseException{
        Date datenow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String mydate = format.format(datenow);
        Date mydate1 = format.parse(mydate);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Order Device");
        System.out.println("Enter info:");

        System.out.println("Full name:");
        String customerName = scanner.nextLine();

        System.out.println("Phone:");
        String customerPhone = scanner.nextLine();

        System.out.println("Expected arrival date (dd-mm-yyyy):");
        String orderDate = scanner.nextLine();

        Date expDate = format.parse(orderDate);

        while(mydate1.after(expDate)){
            System.out.println("The given date is prior to the current date, please enter a different date.");
            System.out.println("Expected arrival date (dd-mm-yyyy):");
            orderDate = scanner.nextLine();
            expDate = format.parse(orderDate);
        }

        System.out.println("Quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        double totalCost = device.getPrice() * quantity;
        discountedprice = totalCost*discount/100;
        totalCost = totalCost - discountedprice;
        nextOrderCode++;
        Order order = new Order(nextOrderCode, device, customerName, customerPhone, orderDate, quantity, mydate, discount, discountedprice, totalCost);
        orders.add(order);

        System.out.println("\n--- Successful Order ---");
        System.out.println("Order info:");
        System.out.println(order);

         
        System.out.println("Return to homepage...");
    }

    public void displaySales() {
        System.out.println("Sales list:");

        if (sales.isEmpty()) {
            System.out.println("There are no sales.");
        } else {
            for (Sale sale : sales) {
                System.out.println(sale);
            }
        }
    }

    public void displayOrders() throws ParseException{
        System.out.println("Orders list:");

        if (orders.isEmpty()) {
            System.out.println("There are no orders.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
            System.out.println("Do you want to choose a specific order?");
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Yes");
            System.out.println("2. No");
            Integer Orderchoice = scanner.nextInt();

            switch (Orderchoice){
                case 1:
                System.out.println("Choose Order:");
                Integer numorder = scanner.nextInt();
                chosenOrder = orders.get(numorder - 1);
                System.out.println(chosenOrder);

                System.out.println("Order arrival and sale?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                Integer arrivalchoice = scanner.nextInt();
                switch (arrivalchoice) {
                    case 1:    
                        Date datenow = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        String mydate = format.format(datenow);
                        
                        nextSaleCode ++;
                        Sale sale = new Sale(nextSaleCode, device, chosenOrder.getCustomerName(), chosenOrder.getCustomerPhone(), mydate, chosenOrder.getQuantity(), chosenOrder.getdiscount(), chosenOrder.getdiscountedprice(), chosenOrder.getTotalCost());
                        sales.add(sale);
                        orders.remove(chosenOrder);
                        System.out.println("Successful");
                        break;
                    case 2:
                        break;
                }
                break;
            case 2:
                break;
            }
        }
    }

    public static void Devicestxt(List<Device> availableDevices){
        String filePath = "available_devices_txt.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Television tvtxt;
            BluRayPlayer playertxt;
            Camera camtxt;
            Consoles consolestxt;
            Refrigerator reftxt;
            WashingMachine washtxt;


            bufferedWriter.write("ITEM LIST");
            bufferedWriter.newLine();
            bufferedWriter.write("{");
            bufferedWriter.newLine();

            for (Device devicetxt : availableDevices) {

                bufferedWriter.write("ITEM");
                bufferedWriter.newLine();
                bufferedWriter.write(" {");
                bufferedWriter.newLine();
                bufferedWriter.write(" CODE " + devicetxt.getCode());
                bufferedWriter.newLine();
                bufferedWriter.write(" MODEL " + devicetxt.getModelName());
                bufferedWriter.newLine();
                bufferedWriter.write(" MODEL_YEAR " + devicetxt.getYear());
                bufferedWriter.newLine();
                bufferedWriter.write(" MANUFACTURER " + devicetxt.getManufacturer());
                bufferedWriter.newLine();
                bufferedWriter.write(" PRICE " + devicetxt.getPrice());
                bufferedWriter.newLine();
                bufferedWriter.write(" PIECES " + devicetxt.getQuantity());
                bufferedWriter.newLine();
                if (devicetxt.getCode() == "0" || devicetxt.getCode() == "1"){
                    bufferedWriter.write(" ITEM TYPE " + "Television" );
                    bufferedWriter.newLine();
                    tvtxt = (Television) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" TYPE " + tvtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + tvtxt.getScreenSize() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + tvtxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PORTS " + tvtxt.getPorts() );
                    bufferedWriter.newLine();
                }
                if (devicetxt.getCode() == "2" || devicetxt.getCode() == "3"){
                    bufferedWriter.write(" ITEM TYPE " + "BluRayPlayer" );
                    bufferedWriter.newLine();
                    playertxt = (BluRayPlayer) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" TYPE " + playertxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + playertxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PLAYBACKFORMAT " + playertxt.getPlaybackFormat() );
                    bufferedWriter.newLine();
                }
                if (devicetxt.getCode() == "4" || devicetxt.getCode() == "5"){
                    bufferedWriter.write(" ITEM TYPE " + "Camera" );
                    bufferedWriter.newLine();
                    camtxt = (Camera) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" TYPE " + camtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" MEGAPIXELS " + camtxt.getMegapixels() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" OPTICALZOOM " + camtxt.getOpticalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" DIGITALZOOM " + camtxt.getDigitalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + camtxt.getScreenSize() );
                    bufferedWriter.newLine();
                }
                if (devicetxt.getCode() == "6" || devicetxt.getCode() == "7"){
                    bufferedWriter.write(" ITEM TYPE " + "Consoles" );
                    bufferedWriter.newLine();
                    consolestxt = (Consoles) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" TYPE " + consolestxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PROCESSOR " + consolestxt.getProcessor() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" GRAPHICS " + consolestxt.getGraphics() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SOUND " + consolestxt.getSound() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" STORAGECAPACITY " + consolestxt.getStorageCapacity() );
                    bufferedWriter.newLine();
                }
                if (devicetxt.getCode() == "8" || devicetxt.getCode() == "9"){
                    bufferedWriter.write(" ITEM TYPE " + "Refrigerator" );
                    bufferedWriter.newLine();
                    reftxt = (Refrigerator) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" TYPE " + reftxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" ENERGYCLASS " + reftxt.getEnergyClass() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + reftxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" FREEZERCAPACITY " + reftxt.getFreezerCapacity() );
                    bufferedWriter.newLine();
                }
                if (devicetxt.getCode() == "10" || devicetxt.getCode() == "11"){
                    bufferedWriter.write(" ITEM TYPE " + "WashingMachine" );
                    bufferedWriter.newLine();
                    washtxt = (WashingMachine) availableDevices.get(Integer.parseInt(devicetxt.getCode()));
                    bufferedWriter.write(" ENERGYCLASS " + washtxt.getEnergyClass());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + washtxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RPM " + washtxt.getRpm() );
                    bufferedWriter.newLine();
                }


                bufferedWriter.write(" }");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("}");
            bufferedWriter.newLine();

            System.out.println("Devices insert successful");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Orderstxt(List<Order> orders){
        String filePath1 = "orders_txt.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Device deviceorder;
            Television tvtxt;
            BluRayPlayer playertxt;
            Camera camtxt;
            Consoles consolestxt;
            Refrigerator reftxt;
            WashingMachine washtxt;


            bufferedWriter.write("ORDER LIST");
            bufferedWriter.newLine();
            bufferedWriter.write("{");
            bufferedWriter.newLine();

            for (Order orderstxt : orders) {

                deviceorder = orderstxt.getDevice();
                bufferedWriter.write("ORDER");
                bufferedWriter.newLine();
                bufferedWriter.write(" {");
                bufferedWriter.newLine();
                bufferedWriter.write(" ORDER NUMBER " + orderstxt.getOrderCode());
                bufferedWriter.newLine();
                bufferedWriter.write(" MODEL " + deviceorder.getModelName());
                bufferedWriter.newLine();
                bufferedWriter.write(" MANUFACTURER " + deviceorder.getManufacturer());
                bufferedWriter.newLine();
                bufferedWriter.write(" PRICE " + orderstxt.getTotalCost());
                bufferedWriter.newLine();
                bufferedWriter.write(" PIECES " + orderstxt.getQuantity());
                bufferedWriter.newLine();
                bufferedWriter.write(" NAME " + orderstxt.getCustomerName());
                bufferedWriter.newLine();
                bufferedWriter.write(" PHONE " + orderstxt.getCustomerPhone());
                bufferedWriter.newLine();
                bufferedWriter.write(" ORDER DATE " + orderstxt.getOrderDate());
                bufferedWriter.newLine();
                bufferedWriter.write(" DELIVERY DATE " + orderstxt.getExpdate());
                bufferedWriter.newLine();
                if (deviceorder.getCode() == "0" || deviceorder.getCode() == "1"){
                    bufferedWriter.write(" ITEM TYPE " + "Television" );
                    bufferedWriter.newLine();
                    
                    tvtxt = (Television) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" TYPE " + tvtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + tvtxt.getScreenSize() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + tvtxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PORTS " + tvtxt.getPorts() );
                    bufferedWriter.newLine();
                }
                if (deviceorder.getCode() == "2" || deviceorder.getCode() == "3"){
                    bufferedWriter.write(" ITEM TYPE " + "BluRayPlayer" );
                    bufferedWriter.newLine();
                    
                    playertxt = (BluRayPlayer) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" TYPE " + playertxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + playertxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PLAYBACKFORMAT " + playertxt.getPlaybackFormat() );
                    bufferedWriter.newLine();
                }
                if (deviceorder.getCode() == "4" || deviceorder.getCode() == "5"){
                    bufferedWriter.write(" ITEM TYPE " + "Camera" );
                    bufferedWriter.newLine();
                   
                    camtxt = (Camera) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" TYPE " + camtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" MEGAPIXELS " + camtxt.getMegapixels() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" OPTICALZOOM " + camtxt.getOpticalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" DIGITALZOOM " + camtxt.getDigitalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + camtxt.getScreenSize() );
                    bufferedWriter.newLine();
                }
                if (deviceorder.getCode() == "6" || deviceorder.getCode() == "7"){
                    bufferedWriter.write(" ITEM TYPE " + "Consoles" );
                    bufferedWriter.newLine();
                    
                    consolestxt = (Consoles) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" TYPE " + consolestxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PROCESSOR " + consolestxt.getProcessor() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" GRAPHICS " + consolestxt.getGraphics() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SOUND " + consolestxt.getSound() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" STORAGECAPACITY " + consolestxt.getStorageCapacity() );
                    bufferedWriter.newLine();
                }
                if (deviceorder.getCode() == "8" || deviceorder.getCode() == "9"){
                    bufferedWriter.write(" ITEM TYPE " + "Refrigerator" );
                    bufferedWriter.newLine();
                    
                    reftxt = (Refrigerator) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" TYPE " + reftxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" ENERGYCLASS " + reftxt.getEnergyClass() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + reftxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" FREEZERCAPACITY " + reftxt.getFreezerCapacity() );
                    bufferedWriter.newLine();
                }
                if (deviceorder.getCode() == "10" || deviceorder.getCode() == "11"){
                    bufferedWriter.write(" ITEM TYPE " + "WashingMachine" );
                    bufferedWriter.newLine();
                    
                    washtxt = (WashingMachine) availableDevices.get(Integer.parseInt(deviceorder.getCode()));
                    bufferedWriter.write(" ENERGYCLASS " + washtxt.getEnergyClass());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + washtxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RPM " + washtxt.getRpm() );
                    bufferedWriter.newLine();
                }


                bufferedWriter.write(" }");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("}");
            bufferedWriter.newLine();

            System.out.println("Orders insert successful");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    public static void Salestxt(List<Sale> sales){
        String filePath2 = "sales_txt.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath2);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Device devicesale;
            Television tvtxt;
            BluRayPlayer playertxt;
            Camera camtxt;
            Consoles consolestxt;
            Refrigerator reftxt;
            WashingMachine washtxt;


            bufferedWriter.write("SALES LIST");
            bufferedWriter.newLine();
            bufferedWriter.write("{");
            bufferedWriter.newLine();

            for (Sale salestxt : sales) {

                devicesale = salestxt.getDevice();
                bufferedWriter.write("SALE");
                bufferedWriter.newLine();
                bufferedWriter.write(" {");
                bufferedWriter.newLine();
                bufferedWriter.write(" CODE " + salestxt.getSaleCode());
                bufferedWriter.newLine();
                bufferedWriter.write(" MODEL " + devicesale.getModelName());
                bufferedWriter.newLine();
                bufferedWriter.write(" MANUFACTURER " + devicesale.getManufacturer());
                bufferedWriter.newLine();
                bufferedWriter.write(" PIECES " + salestxt.getquantitysale());
                bufferedWriter.newLine();
                bufferedWriter.write(" NAME " + salestxt.getCustomerName());
                bufferedWriter.newLine();
                bufferedWriter.write(" DATE " + salestxt.getSaleDate());
                bufferedWriter.newLine();
                bufferedWriter.write(" PHONE " + salestxt.getCustomerPhone());
                bufferedWriter.newLine();
                if (devicesale.getCode() == "0" || devicesale.getCode() == "1"){
                    bufferedWriter.write(" ITEM TYPE " + "Television" );
                    bufferedWriter.newLine();
                    
                    tvtxt = (Television) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" TYPE " + tvtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + tvtxt.getScreenSize() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + tvtxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PORTS " + tvtxt.getPorts() );
                    bufferedWriter.newLine();
                }
                if (devicesale.getCode() == "2" || devicesale.getCode() == "3"){
                    bufferedWriter.write(" ITEM TYPE " + "BluRayPlayer" );
                    bufferedWriter.newLine();
                   
                    playertxt = (BluRayPlayer) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" TYPE " + playertxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RESOLUTION " + playertxt.getResolution() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PLAYBACKFORMAT " + playertxt.getPlaybackFormat() );
                    bufferedWriter.newLine();
                }
                if (devicesale.getCode() == "4" || devicesale.getCode() == "5"){
                    bufferedWriter.write(" ITEM TYPE " + "Camera" );
                    bufferedWriter.newLine();
               
                    camtxt = (Camera) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" TYPE " + camtxt.gettype());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" MEGAPIXELS " + camtxt.getMegapixels() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" OPTICALZOOM " + camtxt.getOpticalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" DIGITALZOOM " + camtxt.getDigitalZoom() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SCREENSIZE " + camtxt.getScreenSize() );
                    bufferedWriter.newLine();
                }
                if (devicesale.getCode() == "6" || devicesale.getCode() == "7"){
                    bufferedWriter.write(" ITEM TYPE " + "Consoles" );
                    bufferedWriter.newLine();
                   
                    consolestxt = (Consoles) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" TYPE " + consolestxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" PROCESSOR " + consolestxt.getProcessor() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" GRAPHICS " + consolestxt.getGraphics() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" SOUND " + consolestxt.getSound() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" STORAGECAPACITY " + consolestxt.getStorageCapacity() );
                    bufferedWriter.newLine();
                }
                if (devicesale.getCode() == "8" || devicesale.getCode() == "9"){
                    bufferedWriter.write(" ITEM TYPE " + "Refrigerator" );
                    bufferedWriter.newLine();
                   
                    reftxt = (Refrigerator) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" TYPE " + reftxt.getType());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" ENERGYCLASS " + reftxt.getEnergyClass() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + reftxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" FREEZERCAPACITY " + reftxt.getFreezerCapacity() );
                    bufferedWriter.newLine();
                }
                if (devicesale.getCode() == "10" || devicesale.getCode() == "11"){
                    bufferedWriter.write(" ITEM TYPE " + "WashingMachine" );
                    bufferedWriter.newLine();
                    
                    washtxt = (WashingMachine) availableDevices.get(Integer.parseInt(devicesale.getCode()));
                    bufferedWriter.write(" ENERGYCLASS " + washtxt.getEnergyClass());
                    bufferedWriter.newLine();
                    bufferedWriter.write(" CAPACITY " + washtxt.getCapacity() );
                    bufferedWriter.newLine();
                    bufferedWriter.write(" RPM " + washtxt.getRpm() );
                    bufferedWriter.newLine();
                }


                bufferedWriter.write(" }");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("}");
            bufferedWriter.newLine();

            System.out.println("Sales insert successful");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void run() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        initializeData();
        Devicestxt(availableDevices);
        Orderstxt(orders);
        Salestxt(sales);

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1=Devices Display");
            System.out.println("2=Orders display");
            System.out.println("3=Sales Display");
            System.out.println("4=Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAvailableDevices();
                    break;
                case 2:
                    displayOrders();
                    break;
                case 3:
                    displaySales();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program.");
                    Devicestxt(availableDevices);
                    Orderstxt(orders);
                    Salestxt(sales);
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void main(String[] args) throws ParseException {
        mainApp store = new mainApp();
        store.run();
    }
}
