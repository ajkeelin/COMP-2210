import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * ArraySet.java.
 *
 * Provides an implementation of the Set interface using an
 * array as the underlying data structure. Values in the array
 * are kept in ascending natural order and, where possible,
 * methods take advantage of this. Many of the methods with parameters
 * of type ArraySet are specifically designed to take advantage
 * of the ordered array implementation.
 *
 * @author Austin Keelin (ajk0033@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-09-29
 *
 */
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {

   ////////////////////////////////////////////
   // DO NOT CHANGE THE FOLLOWING TWO FIELDS //
   ////////////////////////////////////////////
   T[] elements;
   int size;
   Node front;
   Node rear;

   ////////////////////////////////////
   // DO NOT CHANGE THIS CONSTRUCTOR //
   ////////////////////////////////////
   /**
    * Instantiates an empty set.
    */
   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
   }

   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements,
    *               false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this ArraySet.
    *
    * @return a string representation of this ArraySet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }

    /**
     * Ensures the collection contains the specified element. Elements are
     * maintained in ascending natural order at all times. Neither duplicate nor
     * null values are allowed.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
   public boolean add(T element) {
      if (size == elements.length) {
         resize(size * 2);
      }
      
      if (this.contains(element)) {
         return false;
      }
       
      if (element == null) {
         throw new NullPointerException();
      }
      
      if (this.size() == 0) {
         elements[0] = element;
         return true;
      }   
               
      T[] a = Arrays.copyOf(elements, elements.length + 1);
      T ceiling = this.ceiling(element); 
      int location = this.locate(ceiling);
      
      if (location > 0 && location < elements.length) {      
         for (int i = location; i < elements.length; i++) {
           a[i] = elements[i - 1];
           a[location - 1] = element;
         }
         System.arraycopy(a, 0, elements, 0, elements.length);  
      }    
      elements = a;
      return true;        
      
  }   

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. Elements are maintained in ascending natural
     * order at all times.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
   public boolean remove(T element) {
      int loc = locate(element);
      if (loc < 0) {
         return false;
      }
      size--;
      elements[loc] = elements[size];
      elements[size] = null;
      if (isSparse()) {
         resize(elements.length / 2);
      }
      return true;      
   }

   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection
    *                   is to be tested.
    * @return  true if this collection contains the specified element,
    *               false otherwise.
    */
   public boolean contains(T element) {
      if (locate(element) >= 0) {
         return true;
      }
      return false;   
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(Set<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      return false;   
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(ArraySet<T> s) {
      if (size == s.size() && complement(s).size() == 0) {
         return true;
      }
      
      return false;
   }

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(Set<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      Iterator<T> itr = this.iterator();
      while (itr.hasNext()) {
         a.add(itr.next());
      }
      
      Iterator<T> sitr = s.iterator();
      while (sitr.hasNext()) {
         if (this.contains(sitr.next())) {
            continue;
         }
         else {
            a.add(sitr.next());
         }
      }
      return a;      
                     
   }

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(ArraySet<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      for (T elem : this) {
         a.add(elem);
      }
      for (T elem : s) {
         if (this.contains(elem)) {
            continue;
         }   
         else {
            a.add(elem);
         }
      }      
            
      return a; 
   }


   /**
    * Returns a set that is the intersection of this set
    * and the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(Set<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      for (T elem : this) {
         if (this.contains(elem) && s.contains(elem)) {
            a.add(elem);
         }
         else {
            continue;
         }
      }
      return a;              
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(ArraySet<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      for (T elem : this) {
         if (this.contains(elem) && s.contains(elem)) {
            a.add(elem);
         }
         else {
            continue;
         }
      }
      return a;              
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(Set<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      for (T elem : s) {
         if (this.contains(elem)) {
            continue;
         }
         else {
            a.add(elem); 
         }
      }
      return a;              
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(ArraySet<T> s) {
      ArraySet<T> a = new ArraySet<T>();
      for (T elem : s) {
         if (this.contains(elem)) {
            continue;
         }
         else {
            a.add(elem); 
         }
      }
      return a;              
   }


   /**
    * Returns an iterator over the elements in this ArraySet.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> iterator() {

      // ALMOST ALL THE TESTS DEPEND ON THIS METHOD WORKING CORRECTLY.
      // MAKE SURE YOU GET THIS ONE WORKING FIRST.
      // HINT: JUST USE THE SAME CODE/STRATEGY AS THE ARRAYBAG CLASS
      // FROM LECTURE. THE ONLY DIFFERENCE IS THAT YOU'LL NEED THE
      // ARRAYITERATOR CLASS TO BE NESTED, NOT TOP-LEVEL.

      return new ArrayIterator<T>(elements, size);
   }

   /**
    * Returns an iterator over the elements in this ArraySet.
    * The elements are returned in descending order.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> descendingIterator() {
      return null;
   }

   /**
    * Returns an iterator over the members of the power set
    * of this ArraySet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return null;
   }
   
   ////////////////////
   // Nested classes //
   ////////////////////

   /** Defines a node for a doubly-linked list. */
   private class Node {
      private T element;
      private Node next;
      private Node prev;

      /** Creates a new node. */
      public Node(T e) {
         element = e;
      }

   }
   
   private class myIterator implements Iterator<T> {
      
      int count = size;
      int current = 0;
   
   
      /**
       * Returns true if there is at least one more element remaining
       * in the iteration sequence.
       *
       * @return true if there is a next element to iterate over
       */
      public boolean hasNext() {
         return current < count;
      }
   
      /**
       * Returns the next element in the iteration sequence.
       * @return the next element in the iteration sequence
       */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         return elements[current++];
      }
   
      /**
       * Unsupported operation.
       */
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }   
   
     
      
   private class DescendingIterator implements Iterator<T> {
      private Node current = front;

      /** Returns true if more elements are left in the iteration. */
      public boolean hasNext() {
         return current != null;
      }

      /** Returns the next element in the iteration. */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }

         T result = current.element;
         current = current.next;
         return result;
      }

      /** Unsupported operation. */
      public void remove() {
         throw new UnsupportedOperationException();
      } 
      
   }       

   
   
   //private class powerIterator implements Iterator<Set<T>> {
   
   //}   
   
   /////////////////////////////
   // Private utility methods //
   /////////////////////////////
   
   /**
    * Reassign elements to a new array of capacity newSize with the
    * current elements of this bag.
    *
    * @param newSize the new capacity of the array
    */
   private void resize(int newSize) {
      assert newSize > 0;
      @SuppressWarnings("unchecked")
      T[] newArray = (T[]) new Comparable[newSize];
      System.arraycopy(elements, 0, newArray, 0, size);
      elements = newArray;
   }
   
   /**
    * Return the index of the specified element in the
    * array or -1 if not present.
    *
    * @param  element the element to be located
    * @return         the index of element in the array
    */
   private int locate(T element) {
      for (int i = 0; i < size; i++) {
         if (elements[i].equals(element)) {
            return i;
         }
      }
      return -1;
   }
   
   /**
    * Returns true if the number of elements in this bag is strictly less than
    * 25% of the capacity of the elements array.
    *
    * @return true if this bag is too sparse
    */
   private boolean isSparse() {
      return (size > 0) && (size < elements.length / 4);
   }
   
   /**
    *
    */
   private T ceiling(T element) {
      T ceiling = elements[size];
      for (T e : elements) {
         if (e == null) {
            continue;
         }   
         if (ceiling.compareTo(e) > 0 && element.compareTo(e) < 0) {
            ceiling = e;
         }
         else {
            continue;
         }
      }
      return ceiling;
   }                   

}

