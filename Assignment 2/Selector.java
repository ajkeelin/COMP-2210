import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Austin Keelin (ajk0033@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2016-09-05
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }
      if (coll.size() == 1) {
         return coll.iterator().next();
      } 
      
      T minValue = coll.iterator().next();
      
      for (T val : coll) {
         if (comp.compare(val, minValue) < 0) {
            minValue = val;
         }
      }
      return minValue;         
            
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      Iterator<T> itr = coll.iterator();
      T max = itr.next();
      while (itr.hasNext()) {
         if (comp.compare(max, itr.next()) < 0) {
            max = itr.next();
         }
      }   
      return max;      
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      } 
      
      ArrayList<T> a = new ArrayList<T>(coll);
      java.util.Collections.sort(a, comp);
      ArrayList<T> b = new ArrayList<T>(0);
      
      int m = 0;
      int n = 1;
      while (n < a.size()) {
         if (a.get(m) == a.get(n)) {
            m++;
            n++;
         }
         else {
            b.add(a.get(m));
            m++;
            n++;
         }
      }
      
      if (a.get(a.size() - 2) != a.get(a.size() - 1)) {
         b.add(a.get(a.size() - 1));
      }    
      
      if (b.isEmpty() || k > b.size()) {
         throw new NoSuchElementException();
      }
         
      return b.get(k - 1);                 
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty() || k < 1 || k > coll.size()) {
         throw new NoSuchElementException();
      }
      
      if (coll.size() == 1 && k == 1) {
         return coll.iterator().next();
      } 
      
      ArrayList<T> a = new ArrayList<T>(coll);      
      java.util.Collections.sort(a, comp);
      java.util.Collections.reverse(a);
      ArrayList<T> b = new ArrayList<T>(0);
      
      int m = 0;
      int n = 1;
      while (n < a.size()) {
         if (a.get(m) == a.get(n)) {
            m++;
            n++;
         }
         else {
            b.add(a.get(m));
            m++;
            n++;
         }
      } 
      
      if (a.get(a.size() - 2) != a.get(a.size() - 1)) {
         b.add(a.get(a.size() - 1));
      }       
      
      if (b.isEmpty() || k > b.size()) {
         throw new NoSuchElementException();
      }
         
      return b.get(k - 1);                 
   }
   


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      }  
      
      ArrayList<T> a = new ArrayList<T>(coll);
      ArrayList<T> b = new ArrayList<T>(0);
      
      if (comp.compare(low, high) <= 0) {
         for (T val : a) {
            if ((comp.compare(val, low) >= 0) && (comp.compare(val, high) <= 0)) {
               b.add(val);
            }
         } 
      }
      if (b.isEmpty()) {
         throw new NoSuchElementException();
      }
         
      return b;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      } 
      
      Collection<T> a = range(coll, key, (max(coll, comp)), comp);
      
      if (a.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T ceilingValue = min(a, comp);
      return ceilingValue;        
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      if (coll == null || comp == null) {
         throw new IllegalArgumentException();
      }
      if (coll.isEmpty()) {
         throw new NoSuchElementException();
      } 
      
      Collection<T> a = range(coll, key, (min(coll, comp)), comp);
      
      if (a.isEmpty()) {
         throw new NoSuchElementException();
      }
      
      T floorValue = max(a, comp);
      return floorValue;
   }

}


           
   