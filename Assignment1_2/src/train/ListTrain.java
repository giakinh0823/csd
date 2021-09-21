package train;

import train.Train;
import util.Inputter;

public class ListTrain {
    private final Inputter inputter = new Inputter();
    
    TNode head;
    TNode tail;

    public ListTrain() {
        head = null;
        tail = null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public TNode getHead(){
        return this.head;
    }

    public void  setHead(TNode head){
        this.head = head;
    }

    public TNode getTail(){
        return this.tail;
    }

    public void setTail(TNode tail){
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(Train data) {
        TNode q = new TNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addFirst(Train data) {
        TNode q = new TNode(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(Train data) {
        TNode q = new TNode(data);
        if (this.isEmpty()) {
            head = tail = q;
            return;
        }
        tail.next = q;
        tail = q;
    }

    public void addMany(Train... listData) {
        for (Train data : listData) {
            addLast(data);
        }
    }

    public void visit(TNode p) {
        if (p != null) {
            System.out.println(p.data);
        }
    }

    public void traverse() {
        TNode p = head;
        while (p != null) {
            visit(p);
            p = p.next;
        }
    }

    public void insert(int pos, Train data) {
        TNode head = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            TNode newNode = new TNode(data);
            newNode.next = head;
            this.head = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 1) {
                    TNode newNode = new TNode(data);
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

    public void insertAfter(int pos, Train data) {
        TNode node = this.head;
        if (pos < 1) {
            System.out.print("Invalid position");
        }
        if (pos == 1) {
            TNode newNode = new TNode(data);
            newNode.next = node.next;
            node.next = newNode;
        } else {
            while (pos-- != 0) {
                if (pos == 0) {
                    TNode newNode = new TNode(data);
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

    public void insertAfter(TNode q, Train data) {
        if (q == null) {
            return;
        }
        TNode newNode = new TNode(data);
        newNode.next = q.next;
        q.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void insertBefore(TNode q, Train data) {
        if (q == null) {
            return;
        }
        TNode newNode = new TNode(data);
        if (q == this.head) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            TNode current = this.head;
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

    public void remove(TNode q) {
        TNode node = this.head;
        TNode prevNode = null;
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
        TNode node = this.head;
        TNode prevNode = null;
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

    public TNode getNode(int k) {
        TNode node= this.head;
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
        TNode q = head;
        while (q != null) {
            d++;
            q = q.next;
        }
        return d;
    }

    public Train[] toArray() {
        Train list[] = new Train[size()];
        TNode q = head;
        int d = 0;
        while (q != null) {
            list[d]= q.data;
            d++;
            q = q.next;
        }
        return list;
    }

    public void reverse() {
        TNode curentNode, prevNode, nextNode;
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

    public void setData(TNode p, Train x) {
        p.data = x;
    }


    private TNode getMiddle(TNode node) {
        if (node == null) return null;
        TNode slow = node, fast = node;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    TNode sortedMerge(TNode a, TNode b) {
        TNode result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if(a.getData().getTcode().compareTo(b.getData().getTcode())<=0) {
            result = a;
            result.setNext(sortedMerge(a.getNext(), b));
        } else {
            result = b;
            result.setNext( sortedMerge(a, b.getNext()));
        }
        return result;
    }

    public TNode mergeSort(TNode h) {
        if (h == null || h.getNext() == null) {
            return h;
        }
        TNode middle = getMiddle(h);
        TNode nextofmiddle = middle.getNext();
        middle.setNext(null);
        TNode left = mergeSort(h);
        TNode right = mergeSort(nextofmiddle);
        TNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    public void display() {
        System.out.println(new String().format("%s | %s | %s | %s | %s | %s | %s", "Code", "Train Name", "Seat", "Booked", "Depart Time", "Depart Place", "Available Seat"));
        System.out.println("-------------------------------------------------------------------------------");
        TNode node = this.getHead();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public void showTrain(Train train) {
        System.out.println(new String().format("%s | %s | %s | %s | %s | %s | %s", "Code", "Train Name", "Seat", "Booked", "Depart Time", "Depart Place", "Available Seat"));
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(train);
    }

    public Train findByCode(String code) {
        TNode node = this.getHead();
        while (node != null) {
            if (node.getData().getTcode().equals(code)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    private Train inputTrain() {
        String tcode;
        while (findByCode((tcode = inputter.inputStringNoSpace("Enter code: "))) != null) {
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

    public void addTrainToEnd() {
        Train train = inputTrain();
        this.addLast(train);
    }


    public void searchByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Not found code!");
        } else {
            showTrain(train);
        }
    }

    public void removeByCode(String code){
        TNode node = this.getHead();
        TNode prevNode = null;
        while (node != null) {
            if (node.getData().getTcode().equals(code)) {
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

    public void deleteByCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Node found code!");
        } else {
            removeByCode(code);
        }
    }

    public void sortByCode() {
        this.setHead(this.mergeSort(this.getHead()));
    }

    public void addAfterPos() {
        int pos = inputter.inputInt("Enter pos need add: ", 1, this.size());
        Train train = inputTrain();
        this.insertAfter(pos, train);
    }

    public void deleteBeforeCode() {
        String code = inputter.inputStringNoSpace("Enter code: ");
        Train train = findByCode(code);
        if (train == null) {
            System.out.println("Not found code!");
        } else {
            TNode node = this.getHead();
            TNode prevNode = null;
            while (node != null && node.getNext() != null) {
                if (node.getNext().getData().getTcode().equals(code)) {
                    if(prevNode == null){
                        this.setHead(node.getNext());
                    }else{
                        prevNode.setNext(node.getNext());
                        node = prevNode.getNext();
                    }
                    if(node.getNext() == null){
                        this.setTail(node);
                    }
                    break;
                }
                prevNode = node;
                node = prevNode.getNext();
            }
        }
    }

}
