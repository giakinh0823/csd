/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

/**
 *
 * @author giaki
 */
public class CNode {
    Customer data;
    CNode next;

    public CNode(Customer data, CNode next) {
        this.data = data;
        this.next = next;
    }

    public CNode(Customer data) {
        this.data = data;
        this.next = null;
    }

    public Customer getData() {
        return data;
    }

    public CNode getNext(){
        return next;
    }

    public void setData(Customer data) {
        this.data = data;
    }

    public void setNext(CNode next) {
        this.next = next;
    }
}
