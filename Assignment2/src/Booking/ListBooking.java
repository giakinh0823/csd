package Booking;

import customer.CNode;
import customer.CTree;
import customer.Customer;
import train.TTree;
import train.TNode;
import train.Train;
import util.Inputter;

public class ListBooking {
    private final Inputter inputter = new Inputter();
    private TTree listTrain;
    private CTree listCustomer;
    
    BNode head;
    BNode tail;

    public ListBooking() {
        head = null;
        tail = null;
    }
    
    public ListBooking(TTree listTrain, CTree listCustomer) {
        head = null;
        tail = null;
        this.listTrain = listTrain;
        this.listCustomer = listCustomer;
    }
    
    public void display() {
        System.out.println(new String().format("%s | %s | %s | %s | %s | %s | %s | %s", "Customer Code", "Customer Name", "Phone", "Train Code", "Train Name", "Seat", "Depart Time", "Depart Place"));
        System.out.println("---------------------------------------------------------------------------------------------------");
        BNode node = this.getHead();
        while (node != null) {
            Train train = listTrain.search(node.getData().getTcode()).info;
            Customer customer = listCustomer.search(node.getData().getCcode()).info;
            System.out.println(new String().format("%-16s%-16s%-12s%-12s%-12s%-10d%-11.1f%-14s", customer.getCcode(), customer.getName(), customer.getPhone(), train.getTcode(), train.getName(),node.getData().getSeat(), train.getDepartTime(), train.getDepartPlace()));
            node = node.getNext();
        }
    }

    public Booking findBooking(String tcode, String ccode){
        BNode node = this.getHead();
        while (node != null) {
            if (node.getData().getTcode().equals(tcode) && node.getData().getCcode().equals(ccode)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public Train findByCodeTrain(String code) {
        TNode node = listTrain.search(code);
        if(node!=null){
            return node.info;
        }
        return null;
    }

    public Customer findByCodeCustomer(String code) {
        CNode node = listCustomer.search(code);
        if(node!=null){
            return node.info;
        }
        return null;
    }


    public void inputBooking() {
        String tcode;
        Train train = null;
        while ((train = findByCodeTrain((tcode = inputter.inputStringNoSpace("Enter code train(0-> exit): ")))) == null) {
            if(tcode.equals("0")) return;
            System.out.println("Code doesn't exit!");
        }
        if(train.getSeat() - train.getBooked() == 0){
            System.out.println("Train is exhausted!");
            return;
        }
        String ccode;
        Customer customer = null;
        while ((customer = findByCodeCustomer((ccode = inputter.inputStringNoSpace("Enter code customer(0-> exit): ")))) == null) {
            if(ccode.equals("0")) return;
            System.out.println("Code doesn't exit!");
        }
        if(findBooking(tcode, ccode)!=null){
            System.out.println("Train and customer are found in the booking list  then  data is not accepted");
            return;
        }
        int seat = inputter.inputInt("Input seat: ",0,train.getSeat()-train.getBooked());
        Booking booking = new Booking(tcode, ccode, seat);
        train.setBooked(train.getBooked()+seat);
        this.addLast(booking);
    }
    
    
    public void booking(String tcode, String ccode, int seat) {
        Train train = null;
        if((train=findByCodeTrain(tcode))==null){
            System.out.println("Code doesn't exit!");
            return;
        }
        if(train.getSeat() - train.getBooked() == 0){
            System.out.println("Train is exhausted!");
            return;
        }
        Customer customer = null;
        if ((customer = findByCodeCustomer(ccode)) == null) {
            System.out.println("Code doesn't exit!");
            return;
        }
        if(findBooking(tcode, ccode)!=null){
            System.out.println("Train and customer are found in the booking list  then  data is not accepted");
            return;
        }
        Booking booking = new Booking(tcode, ccode, seat);
        train.setBooked(train.getBooked()+seat);
        this.addLast(booking);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public BNode getHead(){
        return this.head;
    }

    public void  setHead(BNode head){
        this.head = head;
    }

    public BNode getTail(){
        return this.tail;
    }

    public void setTail(BNode tail){
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Booking data) {
        BNode q = new BNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addFirst(Booking data) {
        BNode q = new BNode(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(Booking data) {
        BNode q = new BNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addMany(Booking... listData) {
        for (Booking data : listData) {
            addLast(data);
        }
    }

    public void visit(BNode p) {
        if (p != null) {
            System.out.println(p.data);
        }
    }

    public void traverse() {
        BNode p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
    }

    public void insert(int pos, Booking data) {
        BNode head = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            BNode newNode = new BNode(data);
            newNode.next = head;
            this.head = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 1) {
                    BNode newNode = new BNode(data);
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

    public void insertAfter(int pos, Booking data) {
        BNode node = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            BNode newNode = new BNode(data);
            newNode.next = node.next;
            node.next = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 0) {
                    BNode newNode = new BNode(data);
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

    public void insertAfter(BNode q, Booking data) {
        if (q == null) {
            return;
        }
        BNode newNode = new BNode(data);
        newNode.next = q.next;
        q.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void insertBefore(BNode q, Booking data) {
        if (q == null) {
            return;
        }
        BNode newNode = new BNode(data);
        if (q == this.head) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            BNode current = this.head;
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

    public void remove(BNode q) {
        BNode node = this.head;
        BNode prevNode = null;
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
        BNode node = this.head;
        BNode prevNode = null;
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

    public BNode getNode(int k) {
        BNode node= this.head;
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
        BNode q = head;
        while (q != null) {
            d++;
            q = q.next;
        }
        return d;
    }

    public Booking[] toArray() {
        Booking list[] = new Booking[size()];
        BNode q = head;
        int d = 0;
        while (q != null) {
            list[d]= q.data;
            d++;
            q = q.next;
        }
        return list;
    }

    public void reverse() {
        BNode curentNode, prevNode, nextNode;
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

    public void setData(BNode p, Booking x) {
        p.data = x;
    }
    
    BNode getMiddle(BNode node) {
        if (node == null) return null;
        BNode slow = node, fast = node;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }
    
    int compare(BNode a, BNode b){
        int result = a.getData().getTcode().compareTo(b.getData().getTcode());
        if(result==0){
            return a.getData().getCcode().compareTo(b.getData().getCcode());
        }
        return result;
    }

    BNode sortedMerge(BNode a, BNode b) {
        BNode result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if(compare(a,b)<=0) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b));
        }else {
            result = b;
            result.setNext( sortedMerge(a, b.getNext()));
        }
        return result;
    }

    public BNode mergeSort(BNode h) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        BNode middle = getMiddle(h);
        BNode nextofmiddle = middle.getNext();
        middle.setNext(null);
        BNode left = mergeSort(h);
        BNode right = mergeSort(nextofmiddle);
        BNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    public void sortByTcodeAndCcode(){
        this.setHead(this.mergeSort(this.getHead()));
    }

}
