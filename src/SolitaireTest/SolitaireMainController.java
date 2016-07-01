/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.scene.Node;

/**
 * @author flashery
 */
public class SolitaireMainController implements Initializable {

    EventHandler<Event> mouseDrag;
    EventHandler<Event> mouseOver;
    EventHandler<Event> panelClick;
    EventHandler<Event> hiddenDeckClick;

    List<String> cardsDispLst = new LinkedList<>();
    List<Pane> paneList = new ArrayList<>();

    List<String> cardDeck = new ArrayList<>();
    double locationY = 0;

    ObservableList<ImageView> cardObs;

    List<Pane> paneColumns = new LinkedList<>();

    ObservableList<Pane> paneObs;

    Pane tempPane;

    final DataFormat cardDataFormat = new DataFormat("java.util.ArrayList");

    @FXML
    private Pane panel;
    @FXML
    private StackPane hiddenDeckPane;
    @FXML
    private StackPane showDeckPane;
    @FXML
    private StackPane acePane0;
    @FXML
    private StackPane acePane1;
    @FXML
    private StackPane acePane2;
    @FXML
    private StackPane acePane3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setMouseDrag();
        setMouseOver();
        setPanelClick();
        showDeckPane.addEventFilter(MouseEvent.MOUSE_PRESSED, panelClick);
        acePane0.addEventFilter(DragEvent.DRAG_OVER, mouseOver);
        acePane1.addEventFilter(DragEvent.DRAG_OVER, mouseOver);
        acePane2.addEventFilter(DragEvent.DRAG_OVER, mouseOver);
        acePane3.addEventFilter(DragEvent.DRAG_OVER, mouseOver);

        cardDisplay();
    }

    private void cardDisplay() {

        double xLocation = 10;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.add(rank.getString() + suit.getString());
            }
        }

        Collections.shuffle(cardDeck);

        for (int i = 0; i < 7; i++) {
            Pane pane = new Pane();
            pane.setPrefSize(90, 130);
            pane.setLayoutX(xLocation);
            pane.setLayoutY(20);
            pane.addEventFilter(MouseEvent.MOUSE_PRESSED, panelClick);
            pane.addEventFilter(DragEvent.DRAG_OVER, mouseOver);
            //pane.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseDrag); // Add dragging event filter
            paneList.add(pane);
            panel.getChildren().add(pane);
            xLocation += 97;
        }

        int col = 0;
        int cardsIndex = 0;
        int imgLocationY = 0;

        for (int outerCounter = 0; outerCounter < 7; outerCounter++) {
            for (int innerCounter = col; innerCounter < 7; innerCounter++) {

                Card card = createCard(cardsIndex, innerCounter, col, imgLocationY);
                paneList.get(innerCounter).getChildren().add(card);
                cardsDispLst.add(cardDeck.get(cardsIndex));

                cardsIndex++;
            }
            imgLocationY += 15;
            col++;
        }

        //Stack<Card> lifoCard = new Stack<>();
        for (int counter = cardsIndex; counter < cardDeck.size(); counter++) {
            Card card = createCard(counter, 0, col, imgLocationY);
            hiddenDeckPane.getChildren().add(card);

        }
        //hiddenDeckPane.getChildren().addAll(lifoCard);
    }

    private Card createCard(int cardsIndex, int innerCounter, int col, double imgLocationY) {
        Card card = new Card();

        card.setName(cardDeck.get(cardsIndex));
        card.setLayoutY(imgLocationY);
        card.getStyleClass().add("card");

        // Set the color of the card
        if (cardDeck.get(cardsIndex).endsWith("H") || cardDeck.get(cardsIndex).endsWith("D")) {
            card.setIsRed(true);

        } else if (cardDeck.get(cardsIndex).endsWith("S") || cardDeck.get(cardsIndex).endsWith("C")) {
            card.setIsBlack(true);
        }

        // Set card image
        if (innerCounter != col) {
            card.setIsBack(true);
            card.setImage(new Image(card.BACK));
        } else {
            card.setIsBack(false);
            card.setImage(new Image(cardDeck.get(cardsIndex) + card.IMGEXT));
            card.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseDrag); // Add dragging event filter
        }

        return card;
    }

    /*
     * Event Methods
     */
    private void setMouseDrag() {
        mouseDrag = (EventHandler) new EventHandler() {
            @Override
            public void handle(Event event) {
                SolitaireEvent solEvent = new SolitaireEvent(tempPane, locationY, mouseDrag, cardDataFormat);
                //solEvent.dragDone(event.getSource());
                solEvent.dragDetected(event.getSource());

            }
        };
    }

    private void setMouseOver() {
        mouseOver = (EventHandler) new EventHandler() {
            @Override
            public void handle(Event event) {
                SolitaireEvent solEvent = new SolitaireEvent(tempPane, locationY, mouseDrag, cardDataFormat);
                solEvent.dragOver(event.getSource());
                //solEvent.dragExited(event.getSource());
                //solEvent.dragEntered(event.getSource());
                solEvent.dragDropped(event.getSource());
            }
        };
    }

    private void setPanelClick() {
        panelClick = (EventHandler) new EventHandler() {
            @Override
            public void handle(Event event) {
                tempPane = (Pane) event.getSource();
                System.out.println(event.getSource() + " ");

            }
        };
    }

    @FXML
    private void hiddenDeckClick() {
        /*
         * to traverse the list in the reverse order, 
         * get the ListIterator from the last index and 
         * traverse backwards using the hasPrevious() 
        and previous() methods.
         */

        System.out.println();
        /*
        while (iterator.hasPrevious()) {

            Card card = (Card) iterator.previous();
            System.out.print(card.getName() + " | ");

        }*/
        if (!hiddenDeckPane.getChildren().isEmpty()) {

            System.out.println("Hidden Pane Size: " + hiddenDeckPane.getChildren().size());
            int counter = 1;
            double cardLoc = 0;

            while (counter <= 3) {
                ListIterator<Node> iterator = hiddenDeckPane.getChildren().listIterator(hiddenDeckPane.getChildren().size());
                if (iterator.hasPrevious()) {
                    Card card = (Card) iterator.previous();
                    card.setIsBack(false);
                    card.setTranslateX(cardLoc);
                    card.setImage(new Image(card.getName() + card.IMGEXT));
                    card.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseDrag); // Add dragging event filter

                    showDeckPane.getChildren().add(card);
                    System.out.print(card.getName() + " | ");
                }
                //cardLoc += 15;
                counter++;
            }
        } else {
            int counter = showDeckPane.getChildren().size();
            double cardLoc = 0;

            while (counter > 0) {
                ListIterator<Node> iterator = showDeckPane.getChildren().listIterator(showDeckPane.getChildren().size());

                if (iterator.hasPrevious()) {
                    Card card = (Card) iterator.previous();
                    card.setIsBack(true);
                    card.setTranslateX(cardLoc);
                    card.setImage(new Image(card.BACK));
                    card.removeEventFilter(MouseEvent.MOUSE_PRESSED, mouseDrag);

                    hiddenDeckPane.getChildren().add(card);
                    System.out.print(card.getName() + " | ");

                }
                counter--;
                System.out.println(counter);
            }
            showDeckPane.getChildren().clear();
        }

    }

    @FXML
    private void showDeckClick() {

    }
}
