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
    public Train info;
    public TNode left, right;

    public TNode() {
    }

    public TNode(Train x) {
        this.info = x;
        this.left = right = null;
    }
}

