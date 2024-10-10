package edu.nyu.cs.assignment3;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A program to play blackjack.
 * 
 */
public class Blackjack {
    /* Do not modify this method */
    public static Random initRandom(String[] args) {
        if (args.length >= 1) {
          return new Random(Long.parseLong(args[0]));
        } else {
          return new Random();
        }
      }

    public static String cardRead(ArrayList<Integer> cardInput) {
      String retval = "";
        for(int i = 0; i < cardInput.size(); i++){
          retval = retval + cardInput.get(i) + ((i == cardInput.size()-1)?(""):(", ")); 
        }
        return retval;
      }
      public static boolean busted(ArrayList<Integer> cardInput) {
        int total = 0;
          for(int i = 0; i < cardInput.size(); i++){
           total = total + cardInput.get(i);
          }
          if(total > 21){return true;}
          else{return false;}
        }

        public static int totaler(ArrayList<Integer> cardInput) {
          int total = 0;
            for(int i = 0; i < cardInput.size(); i++){
             total = total + cardInput.get(i);
            }
            return total;
          }

    public static void main(String[] args) throws Exception {
        Random r = initRandom(args);    // Do not modify this line
          Scanner scanner = new Scanner(System.in);
        
          ArrayList<Integer> userCard = new ArrayList<>();
          ArrayList<Integer> dealerCard = new ArrayList<>();
          userCard.add(r.nextInt(2, 12));
          userCard.add(r.nextInt(2, 12));
          dealerCard.add(r.nextInt(2, 12));
          dealerCard.add(r.nextInt(2, 12));
          System.out.println("Welcome to Blackjack!");
          boolean hasStood = false;
          boolean dHasStood = false; 
          String hitorStand = "";
          while (!hasStood) {
            System.out.println("Your cards are: " + cardRead(userCard));
              System.out.println("Would you like to hit or stand?");
              hitorStand = scanner.nextLine().toLowerCase().trim();
              if (hitorStand.equals("hit")) {
                userCard.add(r.nextInt(2, 12));
                if (busted(userCard)) {
                  System.out.println("You have bust!");
                  hasStood = true;
                  dHasStood = true;
                  break; 
                }
              }
              else if (hitorStand.equals("stand")){
                hasStood = true;
                break;
              }
            
          }
          if(busted(userCard)){
            System.out.println("Your cards are: " + cardRead(userCard));
            System.out.println("The dealer's cards are: " + cardRead(dealerCard));
            System.out.println("Dealer wins!");
          }
          else{
          boolean dealerHit = r.nextBoolean();
          while (dealerHit) {
            dealerCard.add(r.nextInt(2, 12));
            System.out.println("The dealer hits.");
            if(busted(dealerCard)){
              System.out.println("The dealer has bust!");
              System.out.println("Your cards are: " + cardRead(userCard));
              System.out.println("The dealer's cards are: " + cardRead(dealerCard));
              System.out.println("You win!");
              break;
            }
            else{
            dealerHit = r.nextBoolean();
            }
          }
          if (!busted(dealerCard)) {
            System.out.println("The dealer has stood.");
            System.out.println("Your cards are: " + cardRead(userCard));
            System.out.println("The dealer's cards are: " + cardRead(dealerCard));
            if (totaler(dealerCard) > totaler(userCard)) {
              System.out.println("Dealer wins!");
            }
            else if (totaler(dealerCard) < totaler(userCard)) {
              System.out.println("You win!");
            }
            else if (totaler(dealerCard) == totaler(userCard)) {
              System.out.println("Tie!");
            }
          }
        }
          
          
          
          


    }
} // end of class
