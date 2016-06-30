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
public class SolitaireRule {

    public boolean sameColor(String sourceCard, String topCard) {
 System.out.println("Top Card: " + topCard );
 System.out.println("Source Card: " + sourceCard );
 
        String topCardColor = "";
        String sourceCardColor = "";

        if (sourceCard.endsWith("H") || sourceCard.endsWith("D")) {
            sourceCardColor = "red";
        } else if (sourceCard.endsWith("S") || sourceCard.endsWith("C")) {
            sourceCardColor = "black";
        }

        if (topCard.endsWith("H") || topCard.endsWith("D")) {
            topCardColor = "red";
        } else if (topCard.endsWith("S") || topCard.endsWith("C")) {
            topCardColor = "black";
        }

        return topCardColor.equals(sourceCardColor);
    }

    public boolean compareRank(String sourceCard, String topCard) {

        String sourceRank = "";
        String topRank = "";

        if (topCard.length() == 3) {
            topRank = topCard.substring(0, 2);
            sourceRank = sourceCard.substring(0, 1);
        } else if (sourceCard.length() == 3) {
            topRank = topCard.substring(0, 1);
            sourceRank = sourceCard.substring(0, 2);
        } else {
            sourceRank = sourceCard.substring(0, 1);
            topRank = topCard.substring(0, 1);
        }

        int x = Rank.fromString(topRank).ordinal() - Rank.fromString(sourceRank).ordinal();

        return (x == 1);
    }

    /*
    public boolean sameColor(String sourceCard, String topCard) {

        String topCardColor = "";
        String sourceCardColor = "";

        if (sourceCard.endsWith("H") || sourceCard.endsWith("D")) {
            sourceCardColor = "red";
        } else if (sourceCard.endsWith("S") || sourceCard.endsWith("C")) {
            sourceCardColor = "black";
        }

        if (topCard.endsWith("H") || topCard.endsWith("D")) {
            topCardColor = "red";
        } else if (topCard.endsWith("S") || topCard.endsWith("C")) {
            topCardColor = "black";
        }

        return topCardColor.equals(sourceCardColor);
    }

    public boolean compareRank(String sourceCard, String topCard) {

        String sourceRank = "";
        String topRank = "";
        
        System.out.println("Source card lenght = " + sourceCard.length() );
        System.out.println("Top card lenght = " + topCard.length() );
        
        if (topCard.length() == 3) {
            topRank = topCard.substring(0, 2);
            sourceRank = sourceCard.substring(0, 1);
        } else if (sourceCard.length() == 3) {
            topRank = topCard.substring(0, 1);
            sourceRank = sourceCard.substring(0, 2);
        } else {
            sourceRank = sourceCard.substring(0, 1);
            topRank = topCard.substring(0, 1);
        }
        System.out.println(topRank);

        int x = Rank.fromString(topRank).ordinal() - Rank.fromString(sourceRank).ordinal();

        return (x == 1);
    }
     */
}
