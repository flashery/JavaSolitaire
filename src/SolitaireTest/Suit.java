/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

/**
 *
 * @author flashery
 */
public enum Suit {
    D("D"), H("H"), S("S"), C("C");
    
    private final String suitString;
    
    private Suit(String suitString) {
        this.suitString = suitString;
    }
    
    public String getString() {
        return this.suitString;
    }
}
