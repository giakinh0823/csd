/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

import java.util.LinkedList;

/**
 *
 * @author giaki
 */
public class BQueue {
    LinkedList<BNode> t;

    public BQueue() {
        t = new LinkedList<>();
    }

    void clear(){
        t.clear();
    }
    boolean isEmpty(){
        return t.isEmpty();
    }
    
    void enqueue(BNode p){
        t.addLast(p);
    }
    BNode dequeue(){
        if(isEmpty()) return null;
        return t.removeFirst();
    }
    BNode font(){
        if(isEmpty()) return null;
        return t.getFirst();
    }
}
