package train;

import train.FileManage;
import train.ListTrain;
import util.Menu;

import java.util.ArrayList;

public class ManageTrain {
    private final FileManage fileManage = new FileManage();
    private final Menu menu = new Menu();

    public void manage(ListTrain listTrain) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Load data from file");
        options.add("Input & add to the end");
        options.add("Display data");
        options.add("Save train list to file");
        options.add("Search by tcode");
        options.add("Delete by tcode");
        options.add("Sort by tcode");
        options.add("Add after position k");
        options.add("Delete before code");
        options.add("Back to home");

        while (true){
            System.out.println("----------------------------------Manage to Train------------------------------");
            int choice = menu.getChoice(options);
            switch (choice){
                case 1:
                    fileManage.loadData(listTrain);
                    break;
                case 2:
                    listTrain.addTrainToEnd();
                    break;
                case 3:
                    listTrain.display();
                    break;
                case 4:
                    fileManage.writeData(listTrain);
                    break;
                case 5:
                    listTrain.searchByCode();
                    break;
                case 6:
                    listTrain.deleteByCode();
                    break;
                case 7:
                    listTrain.sortByCode();
                    break;
                case 8:
                    listTrain.addAfterPos();
                    break;
                case 9:
                    listTrain.deleteBeforeCode();
                    break;
                case 10:
                    break;
            }
            if(choice==options.size()){
                break;
            }
        }
    }

}
