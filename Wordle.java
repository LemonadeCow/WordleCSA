import java.util.ArrayList;
import java.util.Scanner;


public class Wordle
{
    private static String [] words;
    private static char [] guess;
    private static String [] color;
    
    public static void main(String [] args)
    {
        words = new String[]{"looks","hello","poops","tries","snack","glues","blues","crack","mocha","lesbo","ocean","apple","roman","juice","light","lunch","books","argon","shots","maths","reach","music","clock","horse","house","loafs"}; //without new .class is expected
        play(5, 6);
    }

    public static void play(int wordLength, int attempts)
    {
        char [] word = words[(int) (Math.random()*words.length)].toCharArray();
        int [] interesting = new int[26];
        boolean won = false;

        System.out.println("Welcome to werdle\nrules:" + 
                           "\nWords are " + wordLength + " long and you will have " + attempts + " attempts to find the word\n" +
                           "\u001B[32mGreen = right letter, right position \n" +
                           "\u001B[33mYellow = right letter, wrong position \n" + 
                           "\u001B[31mRed = wrong letter\n" +
                           "\u001B[0mwhen your guess is ready press enter");

        for(int p = 0; p < attempts; p++)
        {   
            interesting = new int[26];
            if(won)
                break;
            Scanner sc = new Scanner(System.in);
            guess = sc.nextLine().toCharArray();
            System.out.print("\033[A" + "\b\b\b\b\b");
            color = new String[guess.length];
            
            //start off as black
            
            for(int i = 0; i < word.length; i++)
                if(guess[i] != word[i])
                {
                    int pos = ((int) guess[i]) - 97;
                    for(int j = interesting[pos]; j < word.length; j++)
                        if(guess[i] == word[j] && guess[j] != word[j])
                        {   //check the counter
                            interesting[pos] = j + 1;       
                            color[i] = "\u001B[33m";//yellow
                            //yellow
                            break;
                        }
                }
                else
                    if(guess[i] == word[i])
                        //green
                        color[i] = "\u001B[32m";

            for(int i = 0; i < wordLength; i++)
                System.out.print("\b");

            won = true;
            for(int i = 0; i < color.length; i++)
                if(color[i] == null)
                {   
                    won = false;
                    System.out.print("\u001B[31m" + guess[i] + "\u001B[0m");
                }
                else
                    System.out.print(color[i] + guess[i] + "\u001B[0m");
            System.out.println();
            /*
                difference between 
                
                if
                       if
                else
                
                and
            
                if
                    if
                    else
            */
            
        }
    System.out.println("the word was ");
    for(char w : word)
        System.out.print(w);
    }
}
