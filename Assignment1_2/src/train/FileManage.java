package train;

import train.Train;

import java.io.*;


public class FileManage {
    private static final File file = new File( "src/train/data.dat");
    public void loadData(ListTrain listTrain){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String line = null;
            while ((line = bf.readLine()) != null) {
                String listString[] = line.split("\\|");
                Train train = new Train(listString[0].trim(), listString[1].trim(), Integer.parseInt(listString[2].trim()), Integer.parseInt(listString[3].trim()), Double.parseDouble(listString[4].trim()), listString[5].trim());
                if(listTrain.findByCode(train.getTcode())!=null){
                    listTrain.removeByCode(train.getTcode());
                }
                listTrain.add(train);
            }
            fr.close();
            bf.close();
        } catch (NumberFormatException ex){
            System.out.println("Error convert string to number! Please check input data file");
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exits!.");
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void writeData(ListTrain listTrain){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            TNode node = listTrain.getHead();
            while(node != null){
                bw.write(new String().format("%s|%s|%d|%d|%.1f|%s", node.getData().getTcode(), node.getData().getName(), node.getData().getSeat(), node.getData().getBooked(), node.getData().getDepartTime(), node.getData().getDepartPlace()));
                bw.newLine();
                node = node.getNext();
            }
            bw.close();
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exits!.");
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
