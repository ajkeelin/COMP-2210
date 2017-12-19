public class min {

   public static void main(String[] args) {
      System.out.println(min(2, 2, 4));
   }   

   public static int min(int a, int b, int c) {
      if ((a < b) && (a < c)) {
         return a;
      }
      if ((b < a) && (b < c)) {
         return b;
      }
      return c;
   }
   
}            