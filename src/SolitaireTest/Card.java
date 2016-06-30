/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author flashery
 */
public class Card extends ImageView implements Serializable{

    final String BACK = "BG.png";
    final String IMGEXT = ".png";

    String name;
    boolean isBlack;
    boolean isRed;
    boolean isBack;
    Suit suit;
    Rank rank;
    String color;
    Image img;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return this.name;       
    }
    
    public void setIsBack(boolean isBack) {
        this.isBack = isBack;
    }
    
    public boolean getIsBack(){
        return this.isBack;
    }
    
    public void setIsBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }
    
    public Boolean getIsBlack(){
        return this.isBlack;
    }
    
    public void setIsRed(boolean isRed) {
        this.isRed = isRed;
    }
    
    public Boolean getIsRed(){
        return this.isRed;
    }
    
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    public Suit getSuit() {
        return this.suit;
    }
    
    public void setRank(Rank rank) {
        this.rank = rank;
    }
    
    public Rank getRank() {
        return this.rank;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return this.color;
    }
    
}
