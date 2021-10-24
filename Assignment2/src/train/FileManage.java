package train;

import java.io.*;

public class FileManage {

    private static final File file = new File("src/train/data.dat");

    public void loadData(TTree tTrain) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String line = null;
            while ((line = bf.readLine()) != null) {
                String listString[] = line.split("\\|");
                if (listString.length >= 6) {
                    Train train = new Train(listString[0].trim(), listString[1].trim(), Integer.parseInt(listString[2].trim()), Integer.parseInt(listString[3].trim()), Double.parseDouble(listString[4].trim()), listString[5].trim());
                    if (tTrain.search(train.getTcode()) != null) {
                        tTrain.remove(train.getTcode());
                    }
                    tTrain.add(train);
                }
            }
            fr.close();
            bf.close();
        } catch (NumberFormatException ex) {
            System.out.println("Error convert string to number! Please check input data file");
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exits!.");
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    
    public void writeData(TTree listTrain) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            listTrain.inOrder(listTrain.root, bw);
            bw.close();
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exits!.");
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
