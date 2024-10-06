import java.time.Year;

public  class  PopulationProjector {
    public  static  void  main(String [] args) {
        // each year is 365 days
        // One birth every 9 seconds
        // one death every 10 seconds
        // one immigrant every 32 seconds
        int yearPop = 334300850;
        int yearToSeconds = 365 * 24 * 60 * 60; 
        int addPop = 0;
        double bPY = yearToSeconds/9;
        double dPY = yearToSeconds/10 * -1;
        double iPY = yearToSeconds/32;
        addPop = (int) Math.round(bPY + dPY + iPY);
        int yearX = 2024;
        System.out.println("Here are the projected population numbers for the next five years:");
        for(int i = 0; i < 5; i++){
            System.out.println("- Year "+ yearX++ +": "+ (yearPop=yearPop+addPop));
            
        }
    }
}