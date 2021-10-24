package train;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import util.Inputter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lanh0
 */
public class TTree {

    Inputter inputter = new Inputter();
    FileManage fileManage = new FileManage();

    public TNode root;

    public TTree() {
        root = null;
    }

    //xóa các phần tử trong tree
    public void clear() {
        root = null;
    }

    //check xem tree có rỗng không
    boolean isEmpty() {
        return root == null;
    }

    //tìm kiếm phần tử
    public TNode search(TNode p, Train x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        }
        if (x.getTcode().compareTo(p.info.getTcode()) < 0) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    // insert từng phần tử
    public void insert(Train train) {
        if (isEmpty()) {
            root = new TNode(train);
            return;
        }
        TNode f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getTcode() == train.getTcode()) {
                System.out.print("The code " + train.getTcode() + " already exists!");
                return;
            }
            f = p;
            if (train.getTcode().compareTo(p.info.getTcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (train.getTcode().compareTo(f.info.getTcode()) < 0) {
            f.left = new TNode(train);

        } else {
            f.right = new TNode(train);
        }
    }

    //thêm nhiều phần tử vào tree
    public void insertMany(Train... a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    //hiển thị phẩn tử
    public void visit(TNode p) {
        if (p != null) {
            System.out.println(p.info);
        }
    }

    //Duyệt theo chiều rộng của tree
    public void breath(TNode p) {
        if (p == null) {
            return;
        }
        TQueue q = new TQueue();
        q.enqueue(p);
        TNode r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }

    public void balance(ArrayList<Train> t, int i, int j) {
        if (i > j) {
            return;
        }
        Train x;
        int k = (i + j) / 2;
        x = t.get(k);
        insert(x);
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    public void balance(ArrayList<Train> t, TNode p) {
        if (p == null) {
            return;
        }
        balance(t, p.left);
        t.add(p.info);
        balance(t, p.right);
    }

    // Convert tree thành tree balance(Tree cân bằng)
    public void balance() {
        ArrayList<Train> t = new ArrayList<>();
        balance(t, root);
        clear();
        int n = t.size();
        balance(t, 0, n - 1);

    }

    //xoay phải => mục đich để cho độ cao bằng nhau
    public TNode rotateRight(TNode p) {
        if (p == null || p.left == null) {
            return p;
        }
        TNode q = p.left;
        p.left = q.right;
        q.right = p;
        return q;

    }

    //xoay trái => mục đich để cho độ cao bằng nhau
    public TNode rotateLeft(TNode p) {
        if (p == null || p.right == null) {
            return p;
        }
        TNode q = p.right;
        p.right = q.left;
        q.left = p;
        return q;

    }

    //Duyệt phần tử theo root - left - right
    public void preOrder(TNode p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    //Duyệt phần tử theo left - root - right
    public void inOrder(TNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    //Duyệt phần tử theo left - right -root
    public void postOrder(TNode p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    //Xóa phần tử x và coppy sang tree mới và gán lại cho nó
    public void deleteByCopy(Train train) {
        TNode f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getTcode().compareTo(train.getTcode()) == 0) {
                break;
            }
            f = p;
            if (train.getTcode().compareTo(p.info.getTcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        // p is a leaf-TNode;
        if (p.left == null && p.right == null) {
            if (f == null) { // p = root;
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }
        // p has left son only
        if (p.left != null && p.right == null) {
            if (f == null) { // p = root;
                root = p.left;
            } else {
                if (f.left == p) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }
        // p has right son only
        if (p.left == null && p.right != null) {
            if (f == null) { // p = root;
                root = p.right;
            } else {
                if (f.left == p) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }
        // p has two son
        if (p.left != null && p.right != null) {
            // find the right most TNode in the left subtree;
            TNode q = p.left;
            TNode frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp now is the right most TNode;
            p.info = rp.info;
            if (frp == null) {
                // q is the right most TNode;
                p.left = q.left;

            } else {
                frp.right = rp.left;
            }
        }
    }

    ///Add  custom method
    //tìm kiếm by code
    public TNode search(TNode p, String code) {
        if (p == null) {
            return null;
        }
        if (p.info.getTcode().compareTo(code) == 0) {
            return p;
        }
        if (code.compareTo(p.info.getTcode()) < 0) {
            return search(p.left, code);
        } else {
            return search(p.right, code);
        }
    }

    //tìm kiếm theo code
    public TNode search(String code) {
        return search(root, code);
    }

    //xóa phần tử 
    public void remove(Train train) {
        TNode result = search(root, train);
        if (result != null) {
            deleteByCopy(result.info);
        }
    }

    //xóa phần tử theo code
    public void remove(String code) {
        TNode result = search(root, code);
        if (result != null) {
            deleteByCopy(result.info);
            BreadthFirst();
        }
    }

    //thêm phần tử
    public void add(Train train) {
        insert(train);
    }

    //Duyệt theo chiều rộng
    public void BreadthFirst() {
        if (root == null) {
            System.out.println("Is empty!");
            return;
        }
        header();
        breath(root);
    }

    //write to file phần tử theo left - root - right
    public void inOrder(TNode node, BufferedWriter bw) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        try {
            bw.write(new String().format("%s|%s|%d|%d|%.1f|%s", node.info.getTcode(), node.info.getName(), node.info.getSeat(), node.info.getBooked(), node.info.getDepartTime(), node.info.getDepartPlace()));
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        inOrder(node.right);
    }

    //xóa phần tử theo code
    public void removeByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        TNode result = search(root, code);
        if (result != null) {
            deleteByCopy(result.info);
        } else {
            System.out.println("Not found code");
        }
    }

    //tìm kiếm phần tử theo code
    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        TNode result = search(root, code);
        if (result != null) {
            header();
            visit(result);
        } else {
            System.out.println("Not found code");
        }
    }

    //Duyệt theo chiều rộng của tree
    public int size(TNode p) {
        if (p == null) {
            return 0;
        }
        TQueue q = new TQueue();
        q.enqueue(p);
        TNode r;
        int size = 0;
        while (!q.isEmpty()) {
            r = q.dequeue();
            size++;
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
        return size;
    }

    public Train inputTrain() {
        String tcode;
        while (search((tcode = inputter.inputStringNoSpace("Enter code: "))) != null) {
            System.out.println("Code is exit!");
        }
        String name = inputter.inputString("Enter name: ");
        int seat = inputter.inputInt("Enter seat: ", 1);
        int booked = inputter.inputInt("Enter booked: ", 0, seat);
        double departTime = inputter.inputDouble("Enter depart time: ", 0);
        String departPlace = inputter.inputString("Enter depart place: ", "[a-zA-Z0-9]+");
        Train train = new Train(tcode, name, seat, booked, departTime, departPlace);
        return train;
    }

    //input data
    public void input() {
        Train train = inputTrain();
        insert(train);
    }

    public void header() {
        System.out.println(new String().format("%s | %s | %s | %s | %s | %s | %s", "Code", "Train Name", "Seat", "Booked", "Depart Time", "Depart Place", "Available Seat"));
        System.out.println("-------------------------------------------------------------------------------");
    }  

    
    public void loadData(){
        clear();
        fileManage.loadData(this);
    }
}
