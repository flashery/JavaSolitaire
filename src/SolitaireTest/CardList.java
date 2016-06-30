/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import java.util.ArrayList;

/**
 *
 * @author flashery
 */
public class  CardList{
    ArrayList<Object> cards;
    int size;
    
    public void addCard(Object object) {
        cards.add(object);
    }
    
    public Object getCard(int index) {  
        return cards.get(index);
    }
    
    public int size() {
        return cards.size();
    }
}
