package customer;

import Customer.*;
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
public class CTree {

    Inputter inputter = new Inputter();
    FileManage fileManage = new FileManage();

    public CNode root;

    public CTree() {
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
    public CNode search(CNode p, Customer x) {
        if (p == null) {
            return null;
        }
        if (p.info == x) {
            return p;
        }
        if (x.getCcode().compareTo(p.info.getCcode()) < 0) {
            return search(p.left, x);
        } else {
            return search(p.right, x);
        }
    }

    // insert từng phần tử
    public void insert(Customer Customer) {
        if (isEmpty()) {
            root = new CNode(Customer);
            return;
        }
        CNode f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getCcode() == Customer.getCcode()) {
                System.out.print("The code " + Customer.getCcode() + " already exists!");
                return;
            }
            f = p;
            if (Customer.getCcode().compareTo(p.info.getCcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (Customer.getCcode().compareTo(f.info.getCcode()) < 0) {
            f.left = new CNode(Customer);

        } else {
            f.right = new CNode(Customer);
        }
    }

    //thêm nhiều phần tử vào tree
    public void insertMany(Customer... a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    //hiển thị phẩn tử
    public void visit(CNode p) {
        if (p != null) {
            System.out.println(p.info);
        }
    }

    //Duyệt theo chiều rộng của tree
    public void breath(CNode p) {
        if (p == null) {
            return;
        }
        CQueue q = new CQueue();
        q.enqueue(p);
        CNode r;
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

    public void balance(ArrayList<Customer> t, int i, int j) {
        if (i > j) {
            return;
        }
        Customer x;
        int k = (i + j) / 2;
        x = t.get(k);
        insert(x);
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    public void balance(ArrayList<Customer> t, CNode p) {
        if (p == null) {
            return;
        }
        balance(t, p.left);
        t.add(p.info);
        balance(t, p.right);
    }

    // Convert tree thành tree balance(Tree cân bằng)
    public void balance() {
        ArrayList<Customer> t = new ArrayList<>();
        balance(t, root);
        clear();
        int n = t.size();
        balance(t, 0, n - 1);

    }

    //xoay phải => mục đich để cho độ cao bằng nhau
    public CNode rotateRight(CNode p) {
        if (p == null || p.left == null) {
            return p;
        }
        CNode q = p.left;
        p.left = q.right;
        q.right = p;
        return q;

    }

    //xoay trái => mục đich để cho độ cao bằng nhau
    public CNode rotateLeft(CNode p) {
        if (p == null || p.right == null) {
            return p;
        }
        CNode q = p.right;
        p.right = q.left;
        q.left = p;
        return q;

    }

    //Duyệt phần tử theo root - left - right
    public void preOrder(CNode p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    //Duyệt phần tử theo left - root - right
    public void inOrder(CNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    //Duyệt phần tử theo left - right -root
    public void postOrder(CNode p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    //Xóa phần tử x và coppy sang tree mới và gán lại cho nó
    public void deleteByCopy(Customer Customer) {
        CNode f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getCcode().compareTo(Customer.getCcode()) == 0) {
                break;
            }
            f = p;
            if (Customer.getCcode().compareTo(p.info.getCcode()) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        // p is a leaf-CNode;
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
            // find the right most CNode in the left subtree;
            CNode q = p.left;
            CNode frp, rp;
            frp = null;
            rp = q;
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp now is the right most CNode;
            p.info = rp.info;
            if (frp == null) {
                // q is the right most CNode;
                p.left = q.left;

            } else {
                frp.right = rp.left;
            }
        }
    }

    ///Add  custom method
    //tìm kiếm by code
    public CNode search(CNode p, String code) {
        if (p == null) {
            return null;
        }
        if (p.info.getCcode().compareTo(code) == 0) {
            return p;
        }
        if (code.compareTo(p.info.getCcode()) < 0) {
            return search(p.left, code);
        } else {
            return search(p.right, code);
        }
    }

    //tìm kiếm theo code
    public CNode search(String code) {
        return search(root, code);
    }

    //xóa phần tử 
    public void remove(Customer Customer) {
        CNode result = search(root, Customer);
        if (result != null) {
            deleteByCopy(result.info);
        }
    }

    //xóa phần tử theo code
    public void remove(String code) {
        CNode result = search(root, code);
        if (result != null) {
            deleteByCopy(result.info);
            BreadthFirst();
        }
    }

    //thêm phần tử
    public void add(Customer Customer) {
        insert(Customer);
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
    public void inOrder(CNode node, BufferedWriter bw) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        try {
            bw.write("");
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        inOrder(node.right);
    }

    //xóa phần tử theo code
    public void removeByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        CNode result = search(root, code);
        if (result != null) {
            deleteByCopy(result.info);
        } else {
            System.out.println("Not found code");
        }
    }

    //tìm kiếm phần tử theo code
    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        CNode result = search(root, code);
        if (result != null) {
            header();
            visit(result);
        } else {
            System.out.println("Not found code");
        }
    }

    //Duyệt theo chiều rộng của tree
    int size(CNode p) {
        if (p == null) {
            return 0;
        }
        CQueue q = new CQueue();
        q.enqueue(p);
        CNode r;
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

    public Customer inputCustomer() {
        String ccode;
        while (search((ccode = inputter.inputStringNoSpace("Enter code: "))) != null) {
            System.out.println("Code is exit!");
        }
        String name = inputter.inputString("Enter name: ");
        String phone = inputter.inputString("Enter phone: ", "[0-9]{4,}");
        Customer customer = new Customer(ccode, name, phone);
        return customer;
    }
    

    //input data
    public void input() {
        Customer customer =inputCustomer();
        insert(customer);
    }

    public void header() {
        System.out.println(new String().format("%s  |  %s  |  %s", "Code", "Name", "Phone"));
        System.out.println("------------------------");
    }
    
    public void loadData(){
        fileManage.loadData(this);
    }

}
