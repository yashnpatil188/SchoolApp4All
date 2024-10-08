import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;

public class Details {

    public static void main(String[] args) {

         HashMap<Integer, String> hmap = new HashMap<Integer, String>();
         hmap.put(1, "A");
         hmap.put(10, "C");
         hmap.put(4, "Z");
         hmap.put(77, "Y");
         hmap.put(9, "P");
         hmap.put(66, "Q");
         hmap.put(0, "R");

//         // System.out.println("Before Sorting:");
//         Set set = hmap.entrySet();
//         Iterator iterator = set.iterator();
//         while(iterator.hasNext()) {
//               Map.Entry me = (Map.Entry)iterator.next();
//               System.out.print(me.getKey() + ": ");
//               // System.out.println(me.getValue());
//         }
         
         Map<Integer, String> map = new TreeMap<Integer, String>(hmap); 
         // System.out.println("After Sorting:");
         Set set2 = map.entrySet();
         Iterator iterator2 = set2.iterator();
         while(iterator2.hasNext()) {
              Map.Entry me2 = (Map.Entry)iterator2.next();
              System.out.print(me2.getKey() + ": ");
              // System.out.println(me2.getValue());
         }
    }
}