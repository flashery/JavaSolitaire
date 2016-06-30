/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

/**
 *
 * @author flashery
 */
public class SolitaireEvent {

    Pane tempPane;
    double locationY;
    EventHandler mouseDrag;
    DataFormat cardDataFormat;
    AlertDialog alert;
    static ArrayList<Card> cardList;

    public SolitaireEvent(Pane tempPane, double locationY, EventHandler mouseDrag, DataFormat cardDataFormat) {
        this.tempPane = tempPane;
        this.locationY = locationY;
        this.mouseDrag = mouseDrag;
        this.cardDataFormat = cardDataFormat;
    }

    public SolitaireEvent(DataFormat cardDataFormat) {
        this.cardDataFormat = cardDataFormat;
    }

    /*
    public void dragDetected(Object imgView) {
        final Card card = (Card) imgView;

        card.setOnDragDetected((MouseEvent event) -> {

            // drag was detected, start drag-and-drop gesture
            System.out.println("onDragDetected");

            //card.setVisible(false);
            Dragboard db = card.startDragAndDrop(TransferMode.ANY);
            ArrayList<Card> cardList = new ArrayList<>();
            // put a string on dragboard
            ClipboardContent content = new ClipboardContent();
            content.put(cardDataFormat, cardList);
            //content.put(cardDataFormat, card);
            //content.putString(card.getName());

            db.setContent(content);

            event.consume();
        });
    }
     */
    public void dragDetected(Object object) {
        final Card card = (Card) object;

        card.setOnDragDetected((MouseEvent event) -> {

            // drag was detected, start drag-and-drop gesture
            System.out.println("onDragDetected");
            cardList = new ArrayList<>();
            for (int i = tempPane.getChildren().indexOf(card); i < tempPane.getChildren().size(); i++) {

                Card cardMove = (Card) tempPane.getChildren().get(i);
                System.out.println("cardList Source Pane: " + tempPane + " Source Card: " + cardMove.getName());
                cardList.add(cardMove);
                
            }
            Dragboard db = card.startDragAndDrop(TransferMode.ANY);
            // put a string on dragboard
            ClipboardContent content = new ClipboardContent();
            content.put(cardDataFormat, cardList);
           

            db.setContent(content);

            event.consume();
        });
    }

    public void dragOver(Object e) {
        //System.out.println("onDragOver");

        Pane targetPane = (Pane) e;

        targetPane.setOnDragOver((DragEvent event) -> {

            // data is dragged over the target
            // accept it only if it is  not dragged from the same node
            // and if it has a string data
            if (event.getGestureSource() != targetPane
                    && event.getDragboard().hasContent(cardDataFormat)) {
                // allow for both copying and moving, whatever user chooses
                event.acceptTransferModes(TransferMode.ANY);

            }
            event.consume();
        });
    }

    public void dragEntered(Object e) {
        Pane targetPane = (Pane) e;
        targetPane.setOnDragEntered((DragEvent event) -> {
            // the drag-and-drop gesture entered the target
            System.out.println("onDragEntered");
            // show to the user that it is an actual gesture target
            if (event.getGestureSource() != targetPane && event.getDragboard().hasContent(cardDataFormat)) {
                //targetPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            event.consume();
        });
    }

    public void dragExited(Object e) {
        //System.out.println("onDragExited");
        Pane target = (Pane) e;

        target.setOnDragExited((DragEvent event) -> {
            // mouse moved away, remove the graphical cues
            // target.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            event.consume();
        });
    }

    /*
    public void dragDropped(Object e) {

        Pane targetPane = (Pane) e;

        targetPane.setOnDragDropped((DragEvent event) -> {
            // data dropped
            System.out.println("onDragDropped");

            // Get the Dragboard data
            Dragboard db = event.getDragboard();

            boolean success = false;

            double layoutY = 0.0;

            Card tempPaneCard = null;

            // Get the y location and the last card of the last index
            if (!tempPane.getChildren().isEmpty()) {
                for (Node children : targetPane.getChildren()) {
                    layoutY = children.getLayoutY();
                    tempPaneCard = (Card) children;
                }
            }

            // Check if the target pane is empty, if true
            // set the location of y to 0 else add 30
            if (targetPane.getChildren().isEmpty()) {
                locationY = 0;
            } else if (targetPane == tempPane) {
                locationY = layoutY;
            } else {
                locationY = layoutY + 30;
            }

            SolitaireRule solRule = new SolitaireRule();

            // if there is an image data on dragboard, read it and use it
            if (db.hasContent(cardDataFormat)) {
                
                Card sourceCard = (Card) db.getContent(cardDataFormat);
                
                if (!solRule.sameColor(sourceCard.getName(), tempPaneCard.getName())) {
                    if (solRule.compareRank(sourceCard.getName(), tempPaneCard.getName())) {

                        Card card = createNewCard(sourceCard.getName());
                        targetPane.getChildren().add(card);
                        success = true;
                    }
                } else {
                    alert = new AlertDialog(Alert.AlertType.WARNING, "Warning", "Same Color", "The cards are the same colors.");
                }
                
            }

            // let the source know whether the string was successfully
            // transferred and used
            event.setDropCompleted(success);

            event.consume();
        });
    }
     */
    public void dragDropped(Object e) {

        Pane targetPane = (Pane) e;

        targetPane.setOnDragDropped((DragEvent event) -> {
            // data dropped
            System.out.println("onDragDropped");

            // Get the Dragboard data
            Dragboard db = event.getDragboard();

            boolean success = false;

            // if there is an image data on dragboard, read it and use it
            if (db.hasContent(cardDataFormat)) {

                //locationY = getLocationY(targetPane);
                Card targetTopCard = getTargetTopCard(targetPane);
                SolitaireRule solRule = new SolitaireRule();

                ArrayList<Card> cardSourceList = (ArrayList<Card>) db.getContent(cardDataFormat);
                //cardList = (ArrayList<Card>) db.getContent(cardDataFormat);

                //System.out.println("Card List: " + cardList);
                for (Card sourceCard : cardSourceList) {
                    if (!targetPane.getChildren().isEmpty()) {
                        if (!solRule.sameColor(sourceCard.getName(), targetTopCard.getName())) {
                            if (solRule.compareRank(sourceCard.getName(), targetTopCard.getName())) {
                                locationY = getLocationY(targetPane);
                                Card card = createNewCard(sourceCard.getName());
                                targetPane.getChildren().add(card);
                                success = true;
                            } else {
                                //alert = new AlertDialog(Alert.AlertType.WARNING, "Warning", "Same Color", "The cards are the same colors.");
                                break;
                            }
                        } else {
                            //alert = new AlertDialog(Alert.AlertType.WARNING, "Warning", "Same Color", "The cards are the same colors.");
                            break;
                        }
                    } else {
                        Card card = acceptKing(sourceCard);
                        targetPane.getChildren().add(card);
                        success = true;
                    }
                    targetTopCard = sourceCard;
                }

                if (success) {
                    cardList.stream().forEach((sourceCard) -> {
                        dragDone(sourceCard);
                    });

                    targetTopCard = null;
                    cardList = null;
                }

            }

            // transferred and used
            event.setDropCompleted(success);

            event.consume();
        });
    }

    public void dragDone(Card sourceCard) {

        System.out.println("cardList Source Pane: " + tempPane + " cardList Source Card: " + sourceCard);
        tempPane.getChildren().remove(sourceCard);

        if (!tempPane.getChildren().isEmpty()) {
            // Flip the last
            new SolitaireAnimation().flipCard(tempPane, mouseDrag);
        }

    }

    private Card createNewCard(String cardName) {
        Card card = new Card();
        card.setImage(new Image(cardName + card.IMGEXT));
        card.setName(cardName);
        card.addEventFilter(MouseDragEvent.MOUSE_PRESSED, mouseDrag);
        card.setLayoutY(locationY);

        if (cardName.endsWith("H") || cardName.endsWith("D")) {
            card.setIsRed(true);
        } else if (cardName.endsWith("S") || cardName.endsWith("C")) {
            card.setIsBlack(true);
        }
        return card;
    }

    private Card acceptKing(Card sourceCard) {
        Card card = new Card();
        if(sourceCard.getName().substring(0, 1).equals("K")) {
            System.out.println(sourceCard.getName().substring(0, 1));
            card.setImage(new Image(sourceCard.getName() + sourceCard.IMGEXT));
            card.setName(sourceCard.getName());
            card.addEventFilter(MouseDragEvent.MOUSE_PRESSED, mouseDrag);
            card.setLayoutY(0.0);

            if (sourceCard.getName().endsWith("H") || sourceCard.getName().endsWith("D")) {
                sourceCard.setIsRed(true);
            } else if (sourceCard.getName().endsWith("S") || sourceCard.getName().endsWith("C")) {
                card.setIsBlack(true);
            }
        }

        return card;
    }

    private double getLocationY(Pane targetPane) {

        double returnLocation = 0.0;
        double layoutY = 0.0;

        // Get the y location and the last card of the last index
        if (!tempPane.getChildren().isEmpty()) {
            for (Node children : targetPane.getChildren()) {
                layoutY = children.getLayoutY();
            }
        }

        // Check if the target pane is empty, if true
        // set the location of y to 0 else add 30
        if (targetPane.getChildren().isEmpty()) {
            returnLocation = 0;
        } else if (targetPane == tempPane) {
            returnLocation = layoutY;
        } else {
            returnLocation = layoutY + 30;
        }

        return returnLocation;
    }

    private Card getTargetTopCard(Pane targetPane) {
        Card topCard = null;

        // Get the y location and the last card of the last index
        if (!tempPane.getChildren().isEmpty()) {
            for (Node children : targetPane.getChildren()) {
                topCard = (Card) children;
            }
        }

        return topCard;
    }
}
