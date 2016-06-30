/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author flashery
 */
public class SolitaireAnimation {
     /*
     * Animation
     */
    public void flipCard(Pane pane, EventHandler mouseDrag) {

        Card card = new Card();
        for (Node children : pane.getChildren()) {
            card = (Card) children;
        }

        if (card.getIsBack()) {
            // Adding card's property
            card.setImage(new Image(card.getName() + card.IMGEXT));
            card.setIsBack(false);
            card.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseDrag); // Add dragging event filter

            // Flip animation
            RotateTransition rotator = new RotateTransition(Duration.millis(50), card);
            rotator.setAxis(Rotate.Y_AXIS);
            rotator.setFromAngle(90);
            rotator.setToAngle(0);
            rotator.setInterpolator(Interpolator.LINEAR);
            rotator.setCycleCount(1);
            rotator.play();
        }
    }

}
