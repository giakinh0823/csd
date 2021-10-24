package customer;
import util.Menu;

import java.util.ArrayList;

public class ManageCustomer {

    private final FileManage fileManage = new FileManage();
    private final Menu menu = new Menu();

    public void manage(CTree listCustomer) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Load data from file");
        options.add("Input & add to the end");
        options.add("Display data");
        options.add("Save customer list to file");
        options.add("Search by tcode");
        options.add("Delete by tcode");
        options.add("Back to home");
        while (true){
            System.out.println("----------------------------Manage to Customer--------------------------");
            int choice = menu.getChoice(options);
            switch (choice){
                case 1:
                    fileManage.loadData(listCustomer);
                    break;
                case 2:
                    listCustomer.input();
                    break;
                case 3:
                    listCustomer.BreadthFirst();
                    break;
                case 4:
                    fileManage.writeData(listCustomer);
                    break;
                case 5:
                    listCustomer.searchByCode();
                    break;
                case 6:
                    listCustomer.removeByCode();
                    break;
                case 7:
                    break;
            }
            if(choice==options.size()){
                break;
            }
        }
    }
}
