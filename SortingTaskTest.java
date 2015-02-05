
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

/** Test class.
 * @author Jaanus
 */
public class SortingTaskTest {

   ArrayList<Integer> al = null;
   ArrayList<Integer> bl = null;
   LinkedList<String> sl = null;
   LinkedList<String> tl = null;
   List<Pair> pl = null;
   String msg = "";
   int l, r;

   static <T extends Object & Comparable<? super T>>
    boolean checkOrder (List<T> a, int l, int r) {
      if (a==null)
         throw new IllegalArgumentException();
      if (a.size() < 2)
         return true;
      if (l<0 || r>a.size() || l>=r)
         throw new IllegalArgumentException();
      if (r-l < 2)
         return true;
      for (int i=l; i<r-1; i++) {
         if (a.get (i).compareTo (a.get (i+1)) > 0)
            return false;
      } // for
      return true;
   }

   /** Is the list sorted in a stable manner. */
   static boolean checkStability (List<Pair> a, int l, int r) {
            if (a==null)
         throw new IllegalArgumentException();
      if (a.size() < 2)
         return true;
      if (l<0 || r>a.size() || l>=r)
         throw new IllegalArgumentException();
      if (r-l < 2)
         return true;
      for (int i=l; i<r-1; i++) {
         if (a.get (i).compareTo (a.get (i+1)) > 0)
            return false;
         else if (a.get(i).compareTo (a.get(i+1)) == 0) {
            // System.out.print("=");
            if (((Pair)a.get(i)).getSecundo() 
              > ((Pair)a.get(i+1)).getSecundo()) {
               System.out.println ("Method is not stable. First error: " +
                  i + ". " + a.get(i) + " <--> " + (i+1) +
                  ". " + a.get(i+1));
               return false;
            }
         }
      } // for
      return true;
   }

   @Test (timeout=1000)
   public void testArrayListInteger() {
      al = new ArrayList<Integer>(Arrays.asList (1, 3, 2));
      l = 0; r = 3;
      msg = al.toString()+ " l=" + l + " r=" + r;
      SortingTask.binaryInsertionSort (al, l, r);
      bl = new ArrayList<Integer>(Arrays.asList (1, 2, 3));
      assertEquals (msg, bl, al);
   }

   @Test (timeout=1000)
   public void testLinkedListString() {
      sl = new LinkedList<String>(Arrays.asList ("B", "A"));
      l = 0; r = 2;
      msg = sl.toString()+ " l=" + l + " r=" + r;
      SortingTask.binaryInsertionSort (sl, l, r);
      tl = new LinkedList<String>(Arrays.asList ("A", "B"));
      assertEquals (msg, tl, sl);
   }

   @Test (timeout=1000)
   public void testRandom1000() {
      al = new ArrayList<Integer>();
      l = 0;  r = 1000;
      Random generaator = new Random();
      for (int i = l; i < r; i++) {
         al.add (new Integer (generaator.nextInt (100)));
      }
      SortingTask.binaryInsertionSort (al, l, r);
      msg = " list not sorted! l=" + l + " r=" + r;
      assertTrue (msg, checkOrder (al, l, r));
   }

   @Test (timeout=1000)
   public void testStability() {
      pl = new ArrayList<Pair>();
      l = 0;  r = 1000;
      Random generaator = new Random();
      for (int i = l; i < r; i++) {
         pl.add (new Pair (generaator.nextInt (100), i));
      }
      SortingTask.binaryInsertionSort (pl, l, r);
      msg = " method is not stable! l=" + l + " r=" + r;
      assertTrue (msg, checkStability (pl, l, r));
   }

   @Test (timeout=1000)
   public void testBoundaries() {
      al = new ArrayList<Integer>(Arrays.asList (1, 3, 2, 1, 5, 2));
      l = 0; r = 3;
      msg = al.toString()+ " l=" + l + " r=" + r + " changed after r;";
      SortingTask.binaryInsertionSort (al, l, r);
      bl = new ArrayList<Integer>(Arrays.asList (1, 2, 3, 1, 5, 2));
      assertEquals (msg, bl, al);
      al = new ArrayList<Integer>(Arrays.asList (5, 3, 2, 1, 5, 2));
      l = 2; r = 6;
      msg = al.toString()+ " l=" + l + " r=" + r + " changed before l;";
      SortingTask.binaryInsertionSort (al, l, r);
      bl = new ArrayList<Integer>(Arrays.asList (5, 3, 1, 2, 2, 5));
      assertEquals (msg, bl, al);
   }
}

