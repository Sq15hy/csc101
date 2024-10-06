import java.util.Scanner;
public  class  CompoundInflation {
    public  static  void  main(String [] args) {
    Scanner meowScan = new Scanner(System.in);
    System.out.println("Please enter your monthly savings rate:");
    double meowVar = Double.parseDouble( meowScan.nextLine());
    double meowVarInflate = meowVar;
    for(int i = 0; i < 6; i++){
        meowVarInflate = meowVarInflate * 0.991;
        
    }
    int meowVar2 = (int) Math.round(meowVarInflate);
    System.out.println("If you save $" + (int)meowVar + " per month with 0.9% monthly inflation, after 6 months, your account will hold an amount equivalent to $" + meowVar2 + " today.");
    meowScan.close();

    }
}