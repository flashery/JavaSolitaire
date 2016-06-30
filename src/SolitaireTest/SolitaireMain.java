/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SolitaireTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author flashery
 */
public class SolitaireMain extends Application {
    public Stage primaryStage;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        new SolitaireMain().arrayTest();
    }

    /*
    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Timeline Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(512, 512);
        //root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clubs
        ImageView twoClubs = new ImageView("background.png") {
        };
        ImageView threeClubs = new ImageView("background.png");
        ImageView fourClubs = new ImageView("4_of_clubs.png");

        // Diamonds
        ImageView twoDiamonds = new ImageView("background.png") {
        };
        ImageView threeDiamonds = new ImageView("background.png");
        ImageView fourDiamonds = new ImageView("4_of_diamonds.png");

        /*
        gc.drawImage(twoClubs, 5, 0, 125,182);
        gc.drawImage(threeClubs, 5, 40, 125,182);
        gc.drawImage(fourClubs, 5, 80, 125,182);
        
        gc.drawImage(twoDiamonds, 150, 0, 125,182);
        gc.drawImage(threeDiamonds, 150, 40, 125,182);
        gc.drawImage(fourDiamonds, 150, 80, 125,182);
     */
 /*
        twoClubs.setTranslateX(5);
        threeClubs.setTranslateX(5);
        fourClubs.setTranslateX(5);
        twoClubs.setTranslateY(0);
        threeClubs.setTranslateY(40);
        fourClubs.setTranslateY(80);

        twoClubs.setFitWidth(125);
        threeClubs.setFitWidth(125);
        fourClubs.setFitWidth(125);
        twoClubs.setFitHeight(182);
        threeClubs.setFitHeight(182);
        fourClubs.setFitHeight(182);

        root.getChildren().addAll(twoClubs, threeClubs, fourClubs);

        EventHandler filter = (EventHandler) (Event event) -> {
            System.out.println("Naay gipislit nga baraha");
            if (event.getSource().equals(twoClubs)) {
                System.out.println("Dos Kamungay kay gipislit sa mouse");
            } else if (event.getSource().equals(threeClubs)) {
                System.out.println("Tres Kamungay kay gipislit sa mouse");
            } else if (event.getSource().equals(fourClubs)) {
                System.out.println("Ang Kwatro Kamungay kay gipislit sa mouse");
            }
        };

        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);

        twoClubs.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);
        threeClubs.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);
        fourClubs.addEventFilter(MouseEvent.MOUSE_PRESSED, filter);

        theStage.show();
        
    }
     */

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("SolitaireMain.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    public void close() {
        primaryStage.hide();
    }
    
    public void arrayTest() {

        String cardR = "R";
        String cardC = "C";

        final String[] cards = {
            "2C", "2D", "2H", "2S",
            "3C", "3D", "3H", "3S",
            "4C", "4D", "4H", "4S",
            "5C", "5D", "5H", "5S",
            "6C", "6D", "6H", "6S",
            "7C", "7D", "7H", "7S",
            "8C", "8D", "8H", "8S",
            "9C", "9D", "9H", "9S",
            "10C", "10D", "10H", "10S",
            "JC", "JD", "JH", "JS",
            "QC", "QD", "QH", "QS",
            "KC", "KD", "KH", "KS",
            "AC", "AD", "AH", "AS"};

        //ArrayDeque arrayDeque = new ArrayDeque();
        List<List<String>> list = new ArrayList<>();

        // Randomize the cards
        System.out.println("Shuffling Cards..");
        Random randomizer = new Random();
        for (int i = 0; i < cards.length; i++) {
            int random = randomizer.nextInt(cards.length);

            String temp = cards[i];
            cards[i] = cards[random];
            cards[random] = temp;

        }

        // Deal cards
        System.out.println("Dealing Cards..");
        // Create columns
        for (int i = 0; i < 7; i++) {
            ArrayList<String> arrayList = new ArrayList<>();
            list.add(arrayList);
        }

        // Input columns with cards..
        int index = 0;
        int steps = 1;
        for (int listIndex = 0; listIndex < 7; listIndex++) {
            //System.out.println("====Column: " + (listIndex+1) + "| steps: " + steps + "====");
            for (int arrayIndex = 0; arrayIndex < steps; arrayIndex++) {
                //System.out.println("Card in index " + index + " is:"+ cards[index]);
                list.get(listIndex).add(cards[index]);
                index++;
            }
            steps++;
        }
        
        System.out.println("done..");
        
        System.out.print("List Array: " + Arrays.toString(list.toArray()));
        System.out.println();
        System.out.print("Array: " + Arrays.toString(cards));

        // Getting the inner list largest size
        int size = list.size();
        int largestSize = 0;
        for (int i = 0; i < size; i++) {
            // Compare the greatest number
            largestSize = list.get(i).size() > largestSize ? list.get(i).size() : largestSize;
        }

        System.out.println();
        System.out.println("Largest: " + largestSize);
        // Display the cards

        System.out.println("******************Solitaire Test******************");

        int index1 = 0;
        for (List<String> list1 : list) {
            System.out.println(list.get(index1));
            index1++;
        }
        
        System.out.println("******************Solitaire Test******************");
        int i = 0;
        for (int r = 0; r < 7; r++) {
            int e = r;
            for (int c = 0; c < 7; c++) {
                if (e != 0) {
                    System.out.print(String.format("%5s", " "));
                    e--;
                } else {
                    System.out.print(String.format("%5s", cards[i]));
                    i++;
                }
            }
            System.out.println();
        }
         
    }
    
    /*
    public void arrayTest() {

        String cardR = "R";
        String cardC = "C";

        final static String[] cards = {
            "2C", "2D", "2H", "2S",
            "3C", "3D", "3H", "3S",
            "4C", "4D", "4H", "4S",
            "5C", "5D", "5H", "5S",
            "6C", "6D", "6H", "6S",
            "7C", "7D", "7H", "7S",
            "8C", "8D", "8H", "8S",
            "9C", "9D", "9H", "9S",
            "10C", "10D", "10H", "10S",
            "JC", "JD", "JH", "JS",
            "QC", "QD", "QH", "QS",
            "KC", "KD", "KH", "KS",
            "AC", "AD", "AH", "AS"};
    }*/
}
