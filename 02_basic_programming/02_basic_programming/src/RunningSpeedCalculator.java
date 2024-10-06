import java.util.Scanner;

public class RunningSpeedCalculator {
    public static void main(String[] args) {
        double kmPerMile = 1.609344;
        Scanner meowScanner = new Scanner(System.in);
        System.out.println("How many kilometers did you run?");
        double milesRan = Double.parseDouble(meowScanner.nextLine());
        milesRan /= kmPerMile;
        System.out.println("How many minutes did it take you?");
        double minutesRan = Double.parseDouble(meowScanner.nextLine());
        minutesRan = minutesRan/60;
        double idkMan = (double) Math.round((milesRan/minutesRan + 0.05) * 10) / 10;
        System.out.println( "Your average speed was " + milesRan/minutesRan + " miles per hour.");
        meowScanner.close();
    }
}