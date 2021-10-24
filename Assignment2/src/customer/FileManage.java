package customer;

import customer.Customer;

import java.io.*;

public class FileManage {

    private static final File file = new File("src/customer/data.dat");

    public void loadData(CTree listCustomer) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            String line = null;
            while ((line = bf.readLine()) != null) {
                String listString[] = line.split("\\|");
                if (listString.length>=3) {
                    Customer customer = new Customer(listString[0].trim(), listString[1].trim(), listString[2].trim());
                    if (listCustomer.search(customer.getCcode()) != null) {
                        listCustomer.remove(customer.getCcode());
                    }
                    listCustomer.add(customer);
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

    public void writeData(CTree listCustomer) {
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
            listCustomer.inOrder(listCustomer.root, bw);
            bw.close();
            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File doesn't exits!.");
        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
