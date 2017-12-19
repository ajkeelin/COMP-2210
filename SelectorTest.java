import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class SelectorTest {


   /** A test that always fails. **/
   @Test public void selectorTest1() {
      int[] a = {};
      int target = 10;
      int expected = 0;
      int actual = Selector.min(a);
      assertEquals(expected, actual);
   }   
}
