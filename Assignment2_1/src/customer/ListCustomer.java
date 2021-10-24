package customer;

import customer.Customer;
import train.Train;
import util.Inputter;

public class ListCustomer{
    private final Inputter inputter = new Inputter();

    CNode head;
    CNode tail;

    public ListCustomer() {
        head = null;
        tail = null;
    }
    
    public void loadData(){
        clear();
        FileManage fileManage = new FileManage();
        fileManage.loadData(this);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public CNode getHead(){
        return this.head;
    }

    public void  setHead(CNode head){
        this.head = head;
    }

    public CNode getTail(){
        return this.tail;
    }

    public void setTail(CNode tail){
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Customer data) {
        CNode q = new CNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addFirst(Customer data) {
        CNode q = new CNode(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(Customer data) {
        CNode q = new CNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addMany(Customer... listData) {
        for (Customer data : listData) {
            addLast(data);
        }
    }

    public void visit(CNode p) {
        if (p != null) {
            System.out.println(p.data);
        }
    }

    public void traverse() {
        CNode p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
    }

    public void insert(int pos, Customer data) {
        CNode head = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            CNode newNode = new CNode(data);
            newNode.next = head;
            this.head = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 1) {
                    CNode newNode = new CNode(data);
                    newNode.next = head.next;
                    head.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                    break;
                }
                head = head.next;
            }
            if (pos != 1) {
                System.out.print("Position out of range");
            }
        }
    }

    public void insertAfter(int pos, Customer data) {
        CNode node = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            CNode newNode = new CNode(data);
            newNode.next = node.next;
            node.next = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 0) {
                    CNode newNode = new CNode(data);
                    newNode.next = node.next;
                    node.next = newNode;
                    if (newNode.next == null) {
                        tail = newNode;
                    }
                    break;
                }
                node = node.next;
            }

            if (pos != 1) {
                System.out.print("Position out of range");
            }
        }
    }

    public void insertAfter(CNode q, Customer data) {
        if (q == null) {
            return;
        }
        CNode newNode = new CNode(data);
        newNode.next = q.next;
        q.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void insertBefore(CNode q, Customer data) {
        if (q == null) {
            return;
        }
        CNode newNode = new CNode(data);
        if (q == this.head) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            CNode current = this.head;
            while(current.next != null){
                if(current.next==q){
                    newNode.next = current.next;
                    current.next = newNode;
                    break;
                }
                current = current.next;
            }
        }
    }

    public void remove(CNode q) {
        CNode node = this.head;
        CNode prevNode = null;
        while (node != null) {
            if (node == q) {
                if (prevNode == null) {
                    this.head = node.next;
                    node = this.head;
                } else {
                    prevNode.next = node.next;
                    node = prevNode;
                }
                if(node.next ==null){
                    tail = node;
                }
                return;
            } else {
                prevNode = node;
                node = prevNode.next;
            }
        }
    }

    public void removePos(int k) {
        CNode node = this.head;
        CNode prevNode = null;
        int d = 1;
        while (node != null) {
            if (d == k) {
                if (prevNode == null) {
                    this.head = node.next;
                    node = this.head;
                } else {
                    prevNode.next = node.next;
                    node = prevNode;
                }
                if(node.next ==null){
                    tail = node;
                }
                return;
            } else {
                prevNode= node;
                node = prevNode.next;
            }
            d++;
        }
    }

    public CNode getNode(int k) {
        CNode node= this.head;
        while (k-- != 0 && node != null) {
            if (k == 0) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        int d = 0;
        CNode q = head;
        while (q != null) {
            d++;
            q = q.next;
        }
        return d;
    }

    public Customer[] toArray() {
        Customer list[] = new Customer[size()];
        CNode q = head;
        int d = 0;
        while (q != null) {
            list[d]= q.data;
            d++;
            q = q.next;
        }
        return list;
    }

    public void reverse() {
        CNode curentNode, prevNode, nextNode;
        curentNode = this.head;
        prevNode = null;
        nextNode = null;
        while (curentNode != null) {
            nextNode = curentNode.next;
            curentNode.next = prevNode;
            prevNode = curentNode;
            curentNode = nextNode;
        }
        this.head = prevNode;
    }

    public void setData(CNode p, Customer x) {
        p.data = x;
    }


    private CNode getMiddle(CNode node) {
        if (node == null) return null;
        CNode slow = node, fast = node;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    CNode sortedMerge(CNode a, CNode b) {
        CNode result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if(a.getData().getCcode().compareTo(b.getData().getCcode())<=0) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b));
        } else {
            result = b;
            result.setNext( sortedMerge(a, b.getNext()));
        }
        return result;
    }

    public CNode mergeSort(CNode h) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        CNode middle = getMiddle(h);
        CNode nextofmiddle = middle.getNext();
        middle.setNext(null);
        CNode left = mergeSort(h);
        CNode right = mergeSort(nextofmiddle);
        CNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }
    
    public void display() {
        System.out.println(new String().format("%s  |  %s  |  %s", "Code", "Name", "Phone"));
        System.out.println("------------------------");
        CNode node = this.getHead();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public void showCustomer(Customer customer) {
        System.out.println(new String().format("%s  |  %s  |  %s", "Code", "Name", "Phone"));
        System.out.println("------------------------");
        System.out.println(customer);
    }


    public Customer findByCode(String code) {
        CNode node = this.getHead();
        while (node != null) {
            if (node.getData().getCcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public void removeByCode(String code){
        CNode node = this.getHead();
        CNode prevNode = null;
        while (node != null) {
            if (node.getData().getCcode().equals(code)) {
                if (prevNode == null) {
                    this.setHead(node.getNext());
                    node = this.getHead();
                } else {
                    prevNode.setNext(node.getNext());
                    node = prevNode;
                }
                if(node.getNext() == null){
                    this.setTail(node);
                }
                break;
            } else {
                prevNode = node;
                node = prevNode.getNext();
            }
        }
    }

    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Customer customer = findByCode(code);
        if (customer == null) {
            System.out.println("Not found code!");
        } else {
            showCustomer(customer);
        }
    }
    
    public Customer searchCode(String code) {
        Customer customer = findByCode(code);
        if (customer == null) {
            return null;
        } else {
            return customer;
        }
    }


    private Customer inputCustomer() {
        String ccode;
        while (findByCode((ccode = inputter.inputStringNoSpace("Enter code: "))) != null) {
            System.out.println("Code is exit!");
        }
        String name = inputter.inputString("Enter name: ");
        String phone = inputter.inputString("Enter phone: ", "[0-9]{4,}");
        Customer customer = new Customer(ccode, name, phone);
        return customer;
    }

    public void addCustomerToEnd() {
        Customer customer = inputCustomer();
        this.addLast(customer);
    }

    public void deleteByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Customer customer = findByCode(code);
        if (customer == null) {
            System.out.println("Node found code!");
        } else {
            removeByCode(code);
        }
    }
  

}
