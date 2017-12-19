import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Austin Keelin (ajk0033@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2016-11-25
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   String first;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      int i = 0;
      int j = 0;
      int k = K;
      first = sourceText.substring(0 , k);
      while (i + k <= sourceText.length()) {
         String empt = "";
         String kg = sourceText.substring(i, i + k);
         if (!model.containsKey(kg)) {
            int m = k;
            while (j + m < sourceText.length()) {
               String g = sourceText.substring(j, j + m);
               if (j + k >= sourceText.length()) {
                  empt += '\u0000';
               }
               if (kg.equals(g)) {
                  empt += sourceText.substring(j + m, j + m + 1);
               }
               j++;
            }
            model.put(kg, empt);
         }
         j = 0;
         i++;
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return first;      
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      int i = model.size();
      int j = 0;
      Random r = new Random();
      int index = r.nextInt(i);
      for (String n : model.keySet()) {
         if (index == j) {
            return n;
         }
         j++;
      }
      return null;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random r = new Random();
      String empt = "";
      int i = 0;
      for (String n: model.keySet()) {
         if (n.equals(kgram)) {
            empt = model.get(kgram);
            int j = empt.length();
            if (j > 0) {
               i = r.nextInt(j) + 1;
            }
         }
      }
      int k = i - 1;
      if (!empt.equals("")) {
         return empt.charAt(k);
      }
      return '\u0000';
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
