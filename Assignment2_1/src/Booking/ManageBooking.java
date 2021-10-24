package Booking;

import train.TTree;
import customer.ListCustomer;
import util.Menu;

import java.util.ArrayList;

public class ManageBooking {

    private final Menu menu = new Menu();

    public void manage(ListBooking listBooking, TTree listTrain, ListCustomer listCustomer) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Input data");
        options.add("Display data");
        options.add("Sort by tcode + ccode");
        options.add("Back to home");
        while (true) {
            System.out.println("-------------------------------------------Manage to Booking---------------------------------------");
            int choice = menu.getChoice(options);
            switch (choice) {
                case 1:
                    listBooking.inputBooking();
                    listTrain.BreadthFirst();
                    break;
                case 2:
                    listBooking.display();
                    break;
                case 3:
                    listBooking.sortByTcodeAndCcode();
                    listBooking.display();
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
