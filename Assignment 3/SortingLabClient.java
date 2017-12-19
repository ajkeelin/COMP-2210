import java.text.DecimalFormat;
/**
 * SortingLabClient.java
 *
 * Provides a simple example client of SortingLab.java.
 *
 * NOTE: The generic type of SortingLab must be bound
 *       to a Comparable type. The sorting methods in
 *       SortingLab use the natural ordering of the
 *       elements in the parameter array.
 *
 * @author    Dean Hendrix (dh@auburn.edu)
 * @version   2016-09-12
 *
 */
public final class SortingLabClient {

   /** Drives execution. */
   public static void main(String[] args) {

      // instantiate the SortingLab class
      // using your Banner ID number as the key value
      int key = 903546772;
      SortingLab<String> sls = new SortingLab<String>(key);
      
      // elapsed time of the previous run
      double prevTime = 0;
      
      // ratio of current run's elapsed time to
      // previous run's elapsed time:
      // (elapsedTime / prevTime)
      double ratio = 1;

       // log base 2 of ratio
      double logRatio = 1;
      
      // decimal format for ratio and log ratio
      DecimalFormat df = new DecimalFormat("#.000");

      // run each sort a few times before trying to
      // collect timing data
      //String[] ast = {"D", "G", "B", "E", "F", "C", "A"};
      //for (int i = 0; i < 10; i++) {
        // sls.sort1(ast);
         //sls.sort2(ast);
         //sls.sort3(ast);
         //sls.sort4(ast);
         //sls.sort5(ast);
      //} 

      // generate timing data for one sort method using
      // the "doubling strategy" from lecture and lab
      SortingLab<Integer> sli = new SortingLab<Integer>(key);
      int M = 51200001; // max capacity for array
      int N = 100000;   // initial size of array
      double start;
      double elapsedTime;
      for (; N < M; N *= 2) {
         String[] as = {"D", "G", "B", "E", "F", "C", "A"};
         start = System.nanoTime();
         sls.sort3(as);
         elapsedTime = (System.nanoTime() - start) / 1000000000d;
         System.out.print(N + "\t\t");
         System.out.printf("%4.3f", elapsedTime);
         if (N != 100000) {
            ratio = (elapsedTime / prevTime);
            logRatio = (Math.log(ratio) / Math.log(2));
            System.out.print("\t\t" + df.format(ratio) + "\t\t" + df.format(logRatio) + "\n");
         }
         else {
            System.out.println();
         }   
         prevTime = elapsedTime;   
            
      }
   }

   /** return an array of random integer values. */
   private static Integer[] getIntegerArray(int N, int max) {
      Integer[] a = new Integer[N];
      java.util.Random rng = new java.util.Random();
      for (int i = 0; i < N; i++) {
         a[i] = rng.nextInt(max);
      }
      return a;
   }

}
/**
   SortingLab<String> sls = new SortingLab<String>(key);
   String[] as = {"D", "G", "B", "E", "F", "C", "A"};
   start = System.nanoTime();
   sls.sort1(as);
   
   Integer[] ai = getIntegerArray(N, Integer.MAX_VALUE);         
         start = System.nanoTime();
         sli.sort5(ai); */