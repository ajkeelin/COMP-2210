import java.text.DecimalFormat;
/**
 * TimingLabClient.java.
 * Provides a simple example client of TimingLab.java.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-09-12
 *
 */
public class TimingLabClient {

   /** drives execution. */
   public static void main(String[] args) {

      /** useful contants and variables */

      // number of nanoseconds in one second
      double BILLION = 1000000000d;

      // start time of the current run
      double start = 0;

      // elapsed time of the current run
      double elapsedTime = 0;

      // elapsed time of the previous run
      double prevTime = 0;

      // ratio of current run's elapsed time to
      // previous run's elapsed time:
      // (elapsedTime / prevTime)
      double ratio = 1;
      
      // log base 2 of ratio
      double logRatio = 1;
      
      //decimal format for ratio and log ratio
      DecimalFormat df = new DecimalFormat("#.000");

      // problem size for the current run
      int N = 1;

      // key used to select one of the provided
      // internal (hidden) methods of the
      // RunningTime class
      int key = 903546772;

      /** generate timing data */

      // time a single method in this class
      // to increase accuracy of later timing
      start = System.nanoTime();
      methodToTime();
      elapsedTime = (System.nanoTime() - start) / BILLION;
      System.out.print("This call to method methodToTime() took ");
      System.out.printf("%4.3f", elapsedTime);
      System.out.println(" seconds.");

      // measure elapsed time for a single call to timeTrial
      TimingLab tl = new TimingLab(key);
      start = System.nanoTime();
      tl.timeTrial(N);
      elapsedTime = (System.nanoTime() - start) / BILLION;
      System.out.print("This call to method TimingLab.timeTrial("
         + N + ") took ");
      System.out.printf("%4.3f", elapsedTime);
      System.out.println(" seconds.");

      // measure elapsed time for multiple calls to timeTrial
      // with doubling N values
      System.out.print("Timing multiple calls to timeTrial(N) ");
      System.out.println("with increasing N values.");
      System.out.println("----------------------------------"
                           +"---------------------------------");
      System.out.println("N\t\tT (sec)\t\tRatio\t\tLog Ratio");
      System.out.println("----------------------------------"
                           +"---------------------------------");
      for (int i = 0; i < 8; i++) {
         start = System.nanoTime();
         tl.timeTrial(N);
         elapsedTime = (System.nanoTime() - start) / BILLION;
         System.out.print(N + "\t\t");
         System.out.printf("%4.3f", elapsedTime);
         if (i != 0) {
            ratio = (elapsedTime / prevTime);
            logRatio = (Math.log(ratio) / Math.log(2));
            System.out.print("\t\t" + df.format(ratio));
            System.out.print("\t\t" + df.format(logRatio) + "\n");  
         }  
         else {
            System.out.println();
         }    
         N = N * 2;
         prevTime = elapsedTime;
      }

   }

   /**
    * Provides enough work to be timed. Hopefully will
    * require more than 0.001 sec on most machines.
    */
   private static void methodToTime() {
      for (int i = 0; i < 100000; i++) {
         String s1 = "War";
         String s2 = "Eagle";
         String s3 = s1 + s2;
         s1 = null;
         s2 = null;
         s3 = null;
      }
   }

}