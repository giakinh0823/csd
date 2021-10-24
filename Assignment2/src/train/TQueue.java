package train;


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
public class TQueue {
    LinkedList<TNode> t;

    public TQueue() {
        t = new LinkedList<>();
    }

    public void clear(){
        t.clear();
    }
    boolean isEmpty(){
        return t.isEmpty();
    }
    
    public void enqueue(TNode p){
        t.addLast(p);
    }
    public TNode dequeue(){
        if(isEmpty()) return null;
        return t.removeFirst();
    }
    public TNode font(){
        if(isEmpty()) return null;
        return t.getFirst();
    }
}
