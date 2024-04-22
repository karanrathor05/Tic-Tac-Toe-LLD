
public class Main{
    public static void main (String args[]){
        Game game=new Game();
        String res=game.StartGame();
        if(res==" Match Tied"){
            System.out.println(res);
        }
        else{
            System.out.println("Winner is "+res);
        }
    }
}