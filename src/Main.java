
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String display="The curious cat tiptoed across the windowsill, chasing shadows cast by the moonlight.";
        System.out.println("Type the following");
        System.out.println(display);
        System.out.println("Press Enter to start...");
        sc.nextLine();
        Calculate cal=new Calculate(sc,display);
        Thread t=new Thread(cal);
        t.start();
    }
}
class Calculate implements Runnable{
    String output;
    Scanner sc;
    Calculate(Scanner sc,String output){
        this.sc=sc;
        this.output=output;
    }
    public void run(){
        long startTime=System.currentTimeMillis();
        String input=sc.nextLine();
        long endTime=System.currentTimeMillis();
        Accuracy accuracy=new Accuracy(input,output);
        int correct=accuracy.accur();
       try {
           double totalTime = (endTime - startTime) / 1000.0;
           int wordCount = input.trim().split("\\s+").length;
           int wpm = (int)(wordCount / (totalTime/60));
           int accur=(correct*100)/output.length();
           System.out.println("Total WPM:" + wpm);
           System.out.println("Time Take:"+totalTime+"secs");
           System.out.println("Accuracy:"+accur+"%");
       }

       catch(ArithmeticException e){
           System.out.println(e.getMessage());
       }
    }
}
class Accuracy{
    String output;
    String input;
    int correct=0;
    Accuracy(String input,String output){
        this.input=input;
        this.output=output;
    }
    int accur() {
        int len = Math.min(output.length(), input.length());
        for (int i = 0; i < len; i++) {
            if (input.charAt(i) == output.charAt(i)){
            correct++;
            }
        }
        return correct;
    }

}