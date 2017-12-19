import java.util.List;

/**
 * Provider.java
 * Provides generalized search and min methods.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-08-31
 *
 */
public class Provider {

   /** Returns the index of target in a or -1 if not present. */
   public static <T> int search(List<T> a, T target) {
      for (int i = 0; i < a.size(); i++) {
         if (a.get(i).equals(target)) {
            return i;
         }
      }
      return -1;
   }


   /** Returns the minimum value in a. */
   public static <T extends Comparable<T>> T min(List<T> a) {
      T min = a.get(0);
      for (T val : a) {
         if (val.compareTo(min) < 0) {
            min = val;
         }
      }
      return min;
   }

}
