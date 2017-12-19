import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Austin Keelin (ajk0033@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2016-08-24
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * @param a array that is searched
    * @return return the min value
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }   
      int minValue = 0;
      if (a != null) {
         minValue = a[0];
      }
      if (a.length == 1) {
         return a[0];
      }      
      for (int i = 0; i < a.length; i++) {
         if (a[i] < minValue) {
            minValue = a[i];
         }
         
      }              
      return minValue;
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    * @param a array that is searched
    * @return return the min value
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int maxValue = 0;
      if (a != null) {
         maxValue = a[0];
      } 
      if (a.length == 1) {
         return a[0];
      }     
      for (int i = 0; i < a.length; i++) {
         if (a[i] > maxValue) {
            maxValue = a[i];
         }
      }
      return maxValue;      
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    * @param a array that is searched
    * @param k key used to determine which min value to return
    * @return return the kmin value
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k > a.length) {
         throw new IllegalArgumentException();
      }
      
      if (a.length == 1 && k == 1) {
         return a[0];
      } 
      
      int[] aa = Arrays.copyOfRange(a, 0, a.length);        
      Arrays.sort(aa);
      int[] b = new int[a.length];
      int i = 0;
      int j = 1;
      int m = 0;
      while (j < a.length) {
         if (aa[i] == aa[j]) {
            i++;
            j++;
         }            
         else {
            b[m] = aa[i];
            i++;
            j++;
            m++;
         }
      } 
      
      if (aa[(aa.length - 2)] != aa[(aa.length - 1)]) {
         b[m] = aa[aa.length - 1];
      }
                  
      int[] c = Arrays.copyOfRange(b, 0, m + 1);
      if (c == null) {
         throw new IllegalArgumentException();
      }
      int kminValue = -1;
      if ((k <= c.length) && (k > 0)) {
         kminValue = c[k - 1];
      }      
      return kminValue;   
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    * @param a array that is searched
    * @param k key used to determine which max value to return
    * @return return the kmax value
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1
          || k > a.length) {
         throw new IllegalArgumentException();
      }  
      
      if (a.length == 1 && k == 1) {
         return a[0];
      }
       
      int[] aa = Arrays.copyOfRange(a, 0, a.length);    
      Arrays.sort(aa);
      int[] b = new int[aa.length];
      int i = 0;
      int j = 1;
      int m = 0;
      while (j < aa.length) {
         if (aa[i] == aa[j]) {
            i++;
            j++;
         }            
         else {
            b[m] = aa[i];
            i++;
            j++;
            m++;
         }
      }
       
      if (aa[(aa.length - 2)] != aa[(aa.length - 1)]) {
         b[m] = aa[aa.length - 1]; 
      } 
             
      int[] c = Arrays.copyOfRange(b, 0, m + 1);
      if (c == null) {
         throw new IllegalArgumentException();
      }
      int kmaxValue = -1;
      if ((k <= c.length) && (k > 0)) {
         kmaxValue = c[c.length - k];
      }
      return kmaxValue;   
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    * @param a array that is searched
    * @param low low value to determine range
    * @param high high value to determine range
    * @return return the range
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();         
      }
      
      int[] b = new int[a.length];
      int i = 0;
      int j = 0;
      while (i < a.length) {
         if ((a[i] >= low) && (a[i] <= high)) {
            b[j] = a[i];
            j++;
         }
         i++;
      }      
      int[] c = Arrays.copyOfRange(b, 0, j);
      if (c == null) {
         throw new IllegalArgumentException();
      }   
      return c;
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * @param a array that is searched
    * @param key key to determine ceiling
    * @return return the ceiling value
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();        
      }
      
      if (a.length == 1 && a[0] >= key) {
         return a[0];
      }
      
      if (a.length == 1 && a[0] <= key) {
         throw new IllegalArgumentException();
      }      
      
      int[] b = new int[a.length];
      int i = 0;
      int j = 0;

      while (i < a.length) {
         if ((a[i] >= key)) {
            b[j] = a[i];
            j++;   
         }
         i++; 
      }
      
      int[] c = Arrays.copyOfRange(b, 0, j);
      
      if (c.length == 0 || c == null) {
         throw new IllegalArgumentException();
      }
         
      int ceilingValue = 0;
      if (c != null) {
         ceilingValue = c[0];
      }     
         
      int k = 0;
      while (k < c.length) {
         if (c[k] < ceilingValue) {
            ceilingValue = c[k];
         }
         k++;
      } 
      return ceilingValue;          
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    * @param a array that is searched
    * @param key key to determine floor
    * @return return the ceiling floor
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }  
      
      if (a.length == 1 && a[0] <= key) {
         return a[0];
      }
      
      if (a.length == 1 && a[0] >= key) {
         throw new IllegalArgumentException();
      }      
       
      int[] b = new int[a.length];
      int i = 0;
      int j = 0;

      while (i < a.length) {
         if ((a[i] <= key)) {
            b[j] = a[i];
            j++;   
         }
         i++; 
      }
      
      int[] c = Arrays.copyOfRange(b, 0, j);
      
      if (c.length == 0 || c == null) {
         throw new IllegalArgumentException();
      }
         
      int floorValue = 0;
      if (c != null) {
         floorValue = c[0];
      }           
         
      int k = 0;
      while (k < c.length) {
         if (c[k] > floorValue) {
            floorValue = c[k];
         }
         k++;
      } 
      return floorValue;
   }

}
