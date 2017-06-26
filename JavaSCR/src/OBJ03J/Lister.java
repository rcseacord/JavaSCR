package OBJ03J;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Lister {

  public static List<?> fetchNextBatchRecords(List<?> agents, int batchSize) {
    List<?> subItems;
    if (agents.size() < batchSize + 1) {
      subItems = new ArrayList<Object>(agents);
      agents.clear();
    }
    else {
      subItems = new ArrayList<Object>(agents.subList(0, batchSize - 1));
      agents.removeAll(subItems);
    }
    return subItems;
  }
  
  public static void main(String[] args) {
    List<String> agents = new LinkedList<>(Arrays.asList("steven", "sean", "rex", "donald")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    List<Integer> payments = new LinkedList<>(Arrays.asList(100000, 200000, 300000, 500000));
    
    List<?> agents5 = fetchNextBatchRecords(agents, 5);
    for (Object agent : agents5) {
      System.out.println(agent);
    } // end for
    
    System.out.println("-------------------------"); //$NON-NLS-1$
    
    List<?> agents2 = fetchNextBatchRecords(agents, 2);
    for (Object agent : agents2) {
      System.out.println(agent);
    } // end for
  
    System.out.println("-------------------------"); //$NON-NLS-1$
    
    List<?> bribes = fetchNextBatchRecords(payments, 5);
    for (Object bribe : bribes) {
      System.out.println(bribe);
    } // end for
    
    System.out.println("-------------------------"); //$NON-NLS-1$
    
     for (Object agent : agents) {
      System.out.println(agent);
    } // end for
    
  } // end main
} // end class Lister

/*
 * This version has unchecked call exceptions
 * 
public class Lister {

  public static List fetchNextBatchRecords(List agents, int batchSize) {
    List subItems;
    if (agents.size() < batchSize + 1) {
      subItems = new ArrayList(agents);
      agents.clear();
    }
    else {
      subItems = new ArrayList(agents.subList(0, batchSize - 1));
      agents.removeAll(subItems);
    }
    return subItems;
  }
  
  public static void main(String[] args) {
    List<String> agents = Arrays.asList("steven", "sean", "rex", "donald");
    agents = fetchNextBatchRecords(agents, 3);
    for (String agent : agents) {
      System.out.println(agent);
    } // end for
  } // end main
} // end class Lister
*/

/*
 * This version specifies the exact type. Ok, unless we need it to work with different types
 * 
  public static List<String> fetchNextBatchRecords(List<String> agents, int batchSize) {
    List<String> subItems;
    if (agents.size() < batchSize + 1) {
      subItems = new ArrayList<String>(agents);
      agents.clear();
    }
    else {
      subItems = new ArrayList<String>(agents.subList(0, batchSize - 1));
      agents.removeAll(subItems);
    }
    return subItems;
  }
  */
