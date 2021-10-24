package assignment2;

import Booking.ListBooking;
import Booking.ManageBooking;
import customer.ListCustomer;
import customer.ManageCustomer;
import train.ManageTrain;
import util.Menu;

import java.util.ArrayList;
import train.TTree;

public class Main {

    private final static Menu menu = new Menu();
    private final static TTree listTrain = new TTree();
    private final static ListCustomer listCustomer = new ListCustomer();
    private static ListBooking listBooking;
    private final static ManageTrain manageTrain = new ManageTrain();
    private final static ManageCustomer manageCustomer = new ManageCustomer();
    private final static ManageBooking manageBooking = new ManageBooking();

    public static void main(String[] args) {
        listTrain.loadData();
        listCustomer.loadData();
        ArrayList<String> options = new ArrayList<String>();
        options.add("Manage train");
        options.add("Manage customer");
        options.add("Manage booking");
        options.add("Exit app");
        while (true) {
            int choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    manageTrain.manage(listTrain);
                    break;
                case 2:
                    manageCustomer.manage(listCustomer);
                    break;
                case 3:
                    listBooking = new ListBooking(listTrain, listCustomer);
                    manageBooking.manage(listBooking, listTrain, listCustomer);
                    break;
                case 4:
                    break;
            }
            if (choice == options.size()) {
                break;
            }
        }
    }
}
