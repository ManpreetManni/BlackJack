package blackjack;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image; // java.awt.Image is bad in this case.
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author manni
 */
public class BlackJack extends Application {
    ArrayList<Card> deck = new ArrayList<>();
    int currentCard = 0;
    ArrayList<Card> playerCards = new ArrayList<>();
    ArrayList<Card> dealerCards = new ArrayList<>();
    FlowPane playerPanel= new FlowPane();
    FlowPane dealerPanel = new FlowPane();
    int playerSum = 0;
    int dealerSum = 0;
    boolean displayHiddenCard = true;
    
    @Override
    public void start(Stage primaryStage) {
        
        initializeDeck();
        shuffleCards();
        initialState();
        
        primaryStage.setTitle("BlackJack");
        
        BorderPane root = new BorderPane();
        FlowPane buttonPanel = new FlowPane();
        

        root.setBottom(buttonPanel);
        root.setCenter(playerPanel);
        root.setTop(dealerPanel);
        
        dealerPanel.setAlignment(Pos.CENTER);
        playerPanel.setAlignment(Pos.CENTER);
        buttonPanel.setAlignment(Pos.CENTER);
        
        Button btn1 = new Button("Hit");
        buttonPanel.getChildren().add(btn1);
        
        btn1.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent event){
                dealToPlayer();
                int playerSum = calcSum(playerCards);
                System.out.println("Player's Sum is: " + playerSum);
                if(playerSum > 21){
                    System.out.println("Busted!");
                    System.out.println("Dealer Won.");
                    System.exit(0);
                }
                else if(playerSum == 21){
                    System.out.println("Black Jack!");
                    System.out.println("Player Won.");
                    System.exit(0);   
                }
            }
        });
        
        
        
//        FlowPane root = new FlowPane();
        Button btnHit = new Button();
        btnHit.setText("Hit");
        btnHit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hitting!");
                
                // import all java.fx only
                Image img = new Image("PNG/KH.png");
                ImageView imgView = new ImageView(img);
                
                imgView.setFitWidth(200);
                imgView.setPreserveRatio(true);

//                imgView.setScaleX(0.25);
//                imgView.setScaleY(0.25);
                
                root.getChildren().add(imgView);
            }
        });
        

        Button btnStand = new Button();
        
        btnStand.setText("Stand");
        buttonPanel.getChildren().add(btnStand);
        
        btnStand.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                dealToDealer();
                int dealerSum = calcSum(dealerCards);
                System.out.println("Dealer's Sum is: " + dealerSum);
                if(dealerSum > 21){
                    System.out.println("Busted!");
                    System.out.println("Player Won.");
                    System.exit(0);
                }
                else if(dealerSum == 21){
                    System.out.println("Black Jack!");
                    System.out.println("Dealer Won.");
                    System.exit(0);   
                }
            }
        });
        
//        
//        // what is the difference between FlowPane and StackPane????
//        
//        
//        root.getChildren().add(btnHit);
//        root.getChildren().add(btnStand);
        
        Scene scene = new Scene(root, 500, 450);
        
        primaryStage.setTitle("Manpreet's Black Jack!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void dealToDealer(){
        if(displayHiddenCard){
            displayHiddenCard = false;
            dealerPanel.getChildren().clear();
            
            dealerCards.remove(1);
            
            for(int i = 0; i < dealerCards.size(); i++){
            Image img = new Image(dealerCards.get(i).getImagePath());
            
                ImageView imgView = new ImageView(img);
                
                imgView.setFitWidth(100);
                imgView.setPreserveRatio(true);

                dealerPanel.getChildren().add(imgView);
        
            }
            
            
            
        }

        Card remove = deck.remove(0);
        dealerCards.add(remove);
        
                // import all java.fx only
            Image img = new Image(remove.getImagePath());
            
                ImageView imgView = new ImageView(img);
                
                imgView.setFitWidth(100);
                imgView.setPreserveRatio(true);

//                imgView.setScaleX(0.25);
//                imgView.setScaleY(0.25);
                
                dealerPanel.getChildren().add(imgView);
        
    }
    
    public void dealToPlayer(){
        // take one card form the deck(remove) and add it to the player.
//        Card c = deck.remove(0);
//        playerCards.add(c);

        // another one liner method
        Card remove = deck.remove(0);
        playerCards.add(remove);
        
//                System.out.println(deck.get(currentCard).getFullName());
//                System.out.println(deck.get(currentCard).getImageName());
//                currentCard++;
                
                // import all java.fx only
            Image img = new Image(remove.getImagePath());
            currentCard++;
                ImageView imgView = new ImageView(img);
                
                imgView.setFitWidth(100);
                imgView.setPreserveRatio(true);

//                imgView.setScaleX(0.25);
//                imgView.setScaleY(0.25);
                
                playerPanel.getChildren().add(imgView);
    }
    
    public void initializeDeck(){
        
        for (int i = 0; i < 13; i++) {
            Card c = new Card("Hearts", i + 1);
            deck.add(c);
        }
        for (int i = 0; i < 13; i++) {
            Card c = new Card("Diamonds", i + 1);
            deck.add(c);
        }
        for (int i = 0; i < 13; i++) {
            Card c = new Card("Spades", i + 1);
            deck.add(c);
        }
        for (int i = 0; i < 13; i++) {
            Card c = new Card("Clubs", i + 1);
            deck.add(c);
        }
        
    }
    
    public void shuffleCards(){
        for(int i = 0; i < deck.size(); i++){
          int randomPosition = (int)(Math.random() * 52);
          Card card = deck.get(i);
          Card temp = deck.get(randomPosition);
          deck.set(i, temp);
          deck.set(randomPosition, card);
          
        }
    }
    
    public int calcSum(ArrayList<Card> array){
        int sum = 0;
        for(int i = 0; i < array.size(); i++){
            sum += array.get(i).getBlackJackValue();
        }
        return sum;
    }
    
    public void initialState(){
        // display the cards for player.
        int card1 = (int)(Math.random() * deck.size());
        int card2 = (int)(Math.random() * deck.size());
        int card3 = (int)(Math.random() * deck.size());
        
        Image img = new Image(deck.get(card1).getImagePath());   
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);
        playerPanel.getChildren().add(imgView);
        playerCards.add(deck.get(card1));
        deck.remove(deck.get(card1));
        
        img = new Image(deck.get(card2).getImagePath());
        imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);
        playerPanel.getChildren().add(imgView);
        playerCards.add(deck.get(card2));
        deck.remove(deck.get(card2));
        
        // to display Dealer's cards
        img = new Image(deck.get(card3).getImagePath());
        imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);
        dealerPanel.getChildren().add(imgView);
        dealerCards.add(deck.get(card3));
        deck.remove(deck.get(card3));
        
        Card extra = new Card("PNG/red_back.png");
        img = new Image(extra.getPath());
        imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);
        dealerPanel.getChildren().add(imgView);
        dealerCards.add(extra);
        
    }
    
}
