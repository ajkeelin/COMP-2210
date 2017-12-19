import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2016-10-19
 * @author Austin Keelin (ajk0033)
 * @version 2016-10-28
 *
 */
public class LinkedSet<T extends Comparable<? super T>> implements Set<T> {

   //////////////////////////////////////////////////////////
   // Do not change the following three fields in any way. //
   //////////////////////////////////////////////////////////

   /** References to the first and last node of the list. */
   Node front;
   Node rear;

   /** The number of nodes in the list. */
   int size;

   /////////////////////////////////////////////////////////
   // Do not change the following constructor in any way. //
   /////////////////////////////////////////////////////////

   /**
    * Instantiates an empty LinkedSet.
    */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


   //////////////////////////////////////////////////
   // Public interface and class-specific methods. //
   //////////////////////////////////////////////////

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this LinkedSet.
    *
    * @return a string representation of this LinkedSet
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
    * @return  true if this collection contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return (size == 0);
   }


   /**
    * Ensures the collection contains the specified element. Neither duplicate
    * nor null values are allowed. This method ensures that the elements in the
    * linked list are maintained in ascending natural order.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   public boolean add(T element) {
      boolean answer = false; //return this boolean variable
      if (element == null) { //Returns false if element in parameter is null
         return false;
      }
      if (contains(element)) { //Check dupes
         return answer;
      }
     
      Node a = new Node(element);
      Node now = front;
      Node nowBack = front;
        
      if (isEmpty()) {
         front = a;
         rear = a;
         a.next = null;
         a.prev = null;
         size++;
         answer = true;
      }
      
      while ((now != null) && !answer) {
         //Check front
         if (a.element.compareTo(front.element) < 0 && !answer) {
            a.next = front;
            if (front != null) {
               front.prev = a;
            }
            front = a;
            size++;
            answer = true;
            break;
         }
         //Add to a point within the list
         if (a.element.compareTo(now.element) < 0 && !answer) {
            nowBack = now.prev;
            nowBack.next = a;
            now.prev = a;
            a.prev = nowBack;
            a.next = now;
            size++;
            answer = true;
            break;
         }
         //Add to rear
         if ((now.next == null) && !answer) {
            now.next = a;
            a.prev = now;
            rear = a;
            size++;
            answer = true;
         }
         if (rear.next != null) {
            rear = rear.next;
         }
         now = now.next;
      }
      return answer;
   }
     
     /**
      * Ensures the collection does not contain the specified element.
      * If the specified element is present, this method removes it
      * from the collection. This method, consistent with add, ensures
      * that the elements in the linked lists are maintained in ascending
      * natural order.
      *
      * @param   element  The element to be removed.
      * @return  true if collection is changed, false otherwise.
      */
   public boolean remove(T element) {       
      if (element == null) {          
         return false;
      }
      if (!contains(element)) { 
         return false;
      }
      if (isEmpty()) { 
         return false;
      }
      
      Node a = new Node(element);
      Node now = front;
      
      while (now != null) {
         //Removing element if front
         if (a.element.compareTo(now.element) == 0 && now == front) {
            if (front != null) {
               front = front.next;
               if (size() > 1) {
                  front.prev = null;
               }
            }
            else {
               front = null;
            }
            size--;
            if (size() == 0) {
               rear = null;
            }
            return true;
         }
         //Anywhere else
         if (a.element.compareTo(now.element) == 0 && now != front) {
            if (now == rear) {
               rear = rear.prev;
            }
            now.prev.next = now.next;
            if (now.next != null) {
               now.next.prev = now.prev;
            }
            size--;
            return true;
         }
         now = now.next;
      }
      return false;
   }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
   public boolean contains(T element) {
      Node current = front;
      while (current != null) {
         if (current.element.equals(element)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
   public boolean equals(Set<T> s) {
      if (s == null) {
         return false;
      }
      
      if (s.size() != size()) {
         return false;
      }
     
      Iterator<T> sitr = s.iterator();
      while (sitr.hasNext()) {
         T obj = sitr.next();
         if (!contains(obj)) {
            return false;
         }
      }
      return true;
   
   }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
   public boolean equals(LinkedSet<T> s) {
      if (s == null) {
         return false;
      }
      
      if (s.size() != size()) {
         return false;
      }
      
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
      while (itr.hasNext()) {
         T obj1 = itr.next();
         T obj2 = sitr.next();
         if (obj1 != obj2) {
            return false;
         }
      }
      return true;
   }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
   public Set<T> union(Set<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
     
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
     
      while (itr.hasNext()) {
         T obj = itr.next();
         a.add(obj);
      }
      while (sitr.hasNext()) {
         T obj = sitr.next();
         a.add(obj);
      }
      return a;
   }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
   public Set<T> union(LinkedSet<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
   
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
    
      while (itr.hasNext()) {
         T obj = itr.next();
         a.add(obj);
      }
      while (sitr.hasNext()) {
         T obj = sitr.next();
         a.add(obj);
      }
      return a;
   }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
   public Set<T> intersection(Set<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
      
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
      
      while (itr.hasNext()) {
         T obj = itr.next();
         if (s.contains(obj)) {
            a.add(obj);
         }
      }
      while (sitr.hasNext()) {
         T obj = sitr.next();
         if (contains(obj)) {
            a.add(obj);
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
   public Set<T> intersection(LinkedSet<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
      
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
      
      while (itr.hasNext()) {
         T obj = itr.next();
         if (s.contains(obj)) {
            a.add(obj);
         }
      }
      while (sitr.hasNext()) {
         T obj = sitr.next();
         if (contains(obj)) {
            a.add(obj);
         }
      }
      return a;
   }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
   public Set<T> complement(Set<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
      
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
      
      while (itr.hasNext()) {
         T obj = itr.next();
         if (!s.contains(obj)) {
            a.add(obj);
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
   public Set<T> complement(LinkedSet<T> s) {
      LinkedSet<T> a = new LinkedSet<T>();
      
      Iterator<T> itr = this.iterator();
      Iterator<T> sitr = s.iterator();
      
      while (itr.hasNext()) {
         T obj = itr.next();
         if (!s.contains(obj)) {
            a.add(obj);
         }
      }
      return a;
   }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
   public Iterator<T> descendingIterator() {
      return new LinkedDescendingIterator();
   }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
   public Iterator<Set<T>> powerSetIterator() {
      return new LinkedPowerSetIterator();
   }
     
    
    
    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////
    
   private int length(Node n) {
      Node a = n;
      int l = 0;
      while (a != null) {
         l++;
         a = a.next;
      }
      return l;
   }

    ////////////////////
    // Nested classes //
    ////////////////////
    
   private class LinkedIterator implements Iterator<T> {
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
   
   private class LinkedDescendingIterator implements Iterator<T> {
      private Node current = rear;
            
      @Override
      public boolean hasNext() {
         return (current != null);
      }
            
      @Override
      public T next() {
         T result = current.element;
         current = current.prev;               
         return result;
      }
            
      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
   
   private class LinkedPowerSetIterator implements Iterator<Set<T>> {
      private Node current = front;
            
      int count = 0;
      int power = (int) Math.pow(2, size());
                        
      @Override
      public boolean hasNext() {
         return (count < power);
      }
            
      @Override
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Set<T> a = new LinkedSet<T>();
         for (current = front; current != null; current = current.next) {
            if (((count >> 1) & 1) == 1) {
               a.add(current.element);
            }
         }
         count++;
         return a;
      }
            
      @Override
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
   class Node {
      /** the value stored in this node. */
      T element;
      /** a reference to the node after this node. */
      Node next;
      /** a reference to the node before this node. */
      Node prev;

      /**
       * Instantiate an empty node.
       */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }

      /**
       * Instantiate a node that containts element
       * and with no node before or after it.
       */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
