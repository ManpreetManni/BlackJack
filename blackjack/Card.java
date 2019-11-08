package blackjack;


/**
 *
 * @author manni
 */
public class Card {
    String suit;
    int rank;       // 1 means Ace, 2 means two.. 11 means Jack, 12 = Q, 13 = K
    String path;
    
    public Card(String suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }
    
    // for Cards Turned upside down.
    public Card(String path){
        this.path = path;
    }
    
    public String getPath(){
        return this.path;
    }
    
    public String getImageName(){
        if(rank == 1){
            return "A" + suit.charAt(0);
        }
        else if(rank == 11){
            return "J" + suit.charAt(0);
        }
        else if(rank == 12){
            return "Q" + suit.charAt(0);
        }
        else if(rank == 13){
            return "K" + suit.charAt(0); 
        }
        
        return this.rank + "" + suit.charAt(0);
    }
    
    public String getImagePath(){
        return "PNG/" + getImageName() + ".png";
    }
    
    public String getFullName(){
        if(rank == 1){
            return "Ace of " + suit;
        }
        if(rank == 11){
            return "Jack of " + suit;
        }
        else if(rank == 12){
            return "Queen of " + suit;
        }
        else if(rank == 13){
            return "King of " + suit;
        }
        
        return rank + " of " + suit;
    }
    
    public int getBlackJackValue(){
        if(rank > 10){
            return 10;
        }
        return rank;
    }
}
