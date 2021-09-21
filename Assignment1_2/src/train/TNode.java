/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

/**
 *
 * @author giaki
 */
public class TNode {
    Train data;
    TNode next;

    public TNode(Train data, TNode next) {
        this.data = data;
        this.next = next;
    }

    public TNode(Train data) {
        this.data = data;
        this.next = null;
    }

    public Train getData() {
        return data;
    }

    public TNode getNext(){
        return next;
    }

    public void setData(Train data) {
        this.data = data;
    }

    public void setNext(TNode next) {
        this.next = next;
    }
}
