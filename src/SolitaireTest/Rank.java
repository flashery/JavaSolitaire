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
public enum Rank { 
    ACE("A"), 
    TWO("2"), 
    THREE("3"), 
    FOUR("4"), 
    FIVE("5"), 
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"), 
    NINE("9"), 
    TEN("10"), 
    JACK("J"), 
    QUEEN("Q"), 
    KING("K");
                
    private final String rankString;
    
    private Rank(String rankString) {
        this.rankString = rankString;
    }
    
    public String getString() {
        return this.rankString;
    }
    
    public static Rank fromString(String text) {
    if (text != null) {
      for (Rank b : Rank.values()) {
        if (text.equalsIgnoreCase(b.rankString)) {
            //System.out.println("Rank: " +b);
            return b;
        }
      }
    }
    return null;
  }
}