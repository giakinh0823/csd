package train;

import util.Menu;

import java.util.ArrayList;

public class ManageTrain {
    private final FileManage fileManage = new FileManage();
    private final Menu menu = new Menu();
    public void manage(TTree listTrain) {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Load data from file");
        options.add("Input & insert data");
        options.add("In-order traverse");
        options.add("Breadth-first traverse");
        options.add("In-order traverse to file");
        options.add("Search by pcode");
        options.add("Delete by pcode by copying");
        options.add("Simply balancing");
        options.add("Count number of trains");
        options.add("Back to home");
        while (true){
            System.out.println("----------------------------------Manage to Train------------------------------");
            int choice = menu.getChoice(options);
            switch (choice){
                case 1:
                    fileManage.loadData(listTrain);
                    break;
                case 2:
                    listTrain.input();
                    listTrain.BreadthFirst();
                    break;
                case 3:
                    listTrain.header();
                    listTrain.inOrder(listTrain.root);
                    break;
                case 4:
                    listTrain.BreadthFirst();
                    break;
                case 5:
                    fileManage.writeData(listTrain);
                    break;
                case 6:
                    listTrain.searchByCode();
                    break;
                case 7:
                    listTrain.removeByCode();
                    listTrain.BreadthFirst();
                    break;
                case 8:
                    listTrain.balance();
                    listTrain.BreadthFirst();
                    break;
                case 9:
                    System.out.println("Size of list train: " +listTrain.size(listTrain.root));;
                    break;
            }
            if(choice==options.size()){
                break;
            }
        }
    }

}
