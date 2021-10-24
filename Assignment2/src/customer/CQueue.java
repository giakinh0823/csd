package customer;


import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lanh0
 */
public class CQueue {
    public LinkedList<CNode> t;

    public CQueue() {
        t = new LinkedList<>();
    }

    public void clear(){
        t.clear();
    }
    boolean isEmpty(){
        return t.isEmpty();
    }
    
    public void enqueue(CNode p){
        t.addLast(p);
    }
    public CNode dequeue(){
        if(isEmpty()) return null;
        return t.removeFirst();
    }
    public CNode font(){
        if(isEmpty()) return null;
        return t.getFirst();
    }
}
