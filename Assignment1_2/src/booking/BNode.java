/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

/**
 *
 * @author giaki
 */
public class BNode {
    Booking data;
    BNode next;

    public BNode(Booking data, BNode next) {
        this.data = data;
        this.next = next;
    }

    public BNode(Booking data) {
        this.data = data;
        this.next = null;
    }

    public Booking getData() {
        return data;
    }

    public BNode getNext(){
        return next;
    }

    public void setData(Booking data) {
        this.data = data;
    }

    public void setNext(BNode next) {
        this.next = next;
    }
}
