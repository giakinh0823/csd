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
    public Customer info;
    public CNode left, right;

    public CNode() {
    }

    public CNode(Customer x) {
        this.info = x;
        this.left = right = null;
    }
}
