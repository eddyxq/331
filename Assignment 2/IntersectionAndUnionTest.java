import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;



public class IntersectionAndUnionTest {
  
  @Test
  public void boundaryTests() {
    
    SLL<Integer> list1 = new SLL<>();
    SLL<Integer> list2 = new SLL<>();
    
    SLL<Integer> result = new SLL<Integer>();
    
    SLL.intersection( list1, list2, result );
    assertEquals( "Intersection of two empty lists should be empty", true,
                  result.isEmpty() );
    
    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    assertEquals( "Union of two empty lists should be empty", true,
                  result.isEmpty() );
    
    list1.addToTail( 1 );
    result = new SLL<Integer>();
    SLL.intersection( list1, list2, result );
    assertEquals( "Intersection with an empty list should be empty", true,
                  result.isEmpty() );

    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    assertEquals( "Union of a one element list and an empty list should be the one element list", 1,
                    result.deleteFromTail().intValue() );
  assertEquals( "Union of a one element list and an empty list: size of resulting list incorrect", true, result.isEmpty() );
  // change the order of the lists
  result = new SLL<Integer>();
    SLL.union( list2, list1, result );
    assertEquals( "Union of a one element list and an empty list should be the one element list", 1, result.deleteFromTail().intValue() );
    assertEquals( "Union of a one element list and an empty list: size of resulting list incorrect", true, result.isEmpty() );
    
    
    list2.addToTail( 1 );
    result = new SLL<Integer>();
    SLL.intersection( list1, list2, result );
    assertEquals( "Intersection of two single element identical lists. ", 1,
                  result.deleteFromHead().intValue() );
    assertEquals( "Intersection of two single element identical lists, after deleting the head, the resulting list should be empty ", true, result.isEmpty() );
    
    
    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    assertEquals( "Union of two single element identical lists. ", 1,
                  result.deleteFromTail().intValue() );
    assertEquals( "Union of two single element identical lists: size of resulting list incorrect", true, result.isEmpty() );

    
    list2 = new SLL<Integer>();
    list2.addToTail( 2 );
    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    assertEquals( "Union of two single element distinct lists. ", 1,
                  result.deleteFromHead().intValue() );
    assertEquals( "Union of two single element distinct lists. ", 2,
                  result.deleteFromHead().intValue() );
    assertEquals( "Union of two single element distinct lists: size of resulting list incorrect", true, result.isEmpty() );
    
    // change the order of the lists
    result = new SLL<Integer>();
    SLL.union( list2, list1, result );
    assertEquals( "Union of two single element distinct lists. ", 1,
                  result.deleteFromHead().intValue() );
    assertEquals( "Union of two single element distinct lists. ", 2,
                  result.deleteFromHead().intValue() );
    assertEquals( "Union of two single element distinct lists: size of resulting list incorrect", true, result.isEmpty() ); 
  }


  @Test
  public void identicalListTests() {

    SLL<Integer> list1 = new SLL<>();
    SLL<Integer> list2 = new SLL<>();

    // two identical lists
    int NUM = 5;
    for( int i = 0; i < NUM; i++) {
      list1.addToTail( i );
      list2.addToTail( i );
    }
    
    SLL<Integer> interResult = new SLL<Integer>();
    SLL<Integer> unionResult = new SLL<Integer>();
    SLL.intersection( list1, list2, interResult );
    SLL.union( list1, list2, unionResult );
    
    for( int i = 0; i < NUM; i++ ) {
      assertEquals( "Intersection of two " + NUM + " element identical lists: "
                    , i, interResult.deleteFromHead().intValue() );
      assertEquals( "Union of two " + NUM + " element identical lists: "
                    , i, unionResult.deleteFromHead().intValue() );
    }
    assertEquals( "Intersection of two " + NUM + " element identical lists. Size mismatch in result ", true, interResult.isEmpty() );
    assertEquals( "Union of two " + NUM + " element identical lists. Size mismatch in result ", true, unionResult.isEmpty() );
  }
  
  @Test
  public void differentListTests() {

    SLL<Integer> list1 = new SLL<>();
    
    // two unequal sized lists with no common elements
    
    int NUM = 5;
    for( int i = 0; i < NUM; i++)
      list1.addToTail( i );
    SLL<Integer> list2 = new SLL<>();
    for( int i = NUM; i < 2*NUM + 2; i++ )
      list2.addToTail( i );

    // Intersection Result
    SLL<Integer> result = new SLL<Integer>();
    SLL.intersection( list1, list2, result );
    assertEquals( "Intersection of two lists with no elements in common should be empty.", true, result.isEmpty() );

    // Union Result
    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    for( int i = 0; i < 2*NUM + 2; i++ )
      assertEquals( "Union of two disjoint lists: "
                    , i, result.deleteFromHead().intValue() );
    assertEquals( "Union of two disjoint lists. Size mismatch in result ", true, result.isEmpty() );

    // ---------------------------------
    
    
    // two unequal sized lists with some elements in common
    list2 = new SLL<>();
    for( int i = NUM - 3; i < 2*NUM - 3 + 2; i++ )
      list2.addToTail( i );


    // intersection result
    result = new SLL<Integer>();
    SLL.intersection( list1, list2, result );
    for( int i = NUM - 3; i < NUM; i++)
      assertEquals( "Intersection of two unequal sized lists with some elements in common ", i, result.deleteFromHead().intValue() );
    assertEquals( "intersection of two unequal sized lists with some elements in common. Size mismatch in result ", true, result.isEmpty() );

    // union result
    result = new SLL<Integer>();
    SLL.union( list1, list2, result );
    for( int i = 0; i < 2*NUM - 3 + 2; i++)
      assertEquals( "Union of two unequal sized lists with some elements in common ", i, result.deleteFromHead().intValue() );
    assertEquals( "Union of two unequal sized lists with some elements in common. Size mismatch in result ", true, result.isEmpty() );
  }
  
  
  public static void main( String[] args ) {
    Result result = JUnitCore.runClasses( IntersectionAndUnionTest.class );
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }    
  }
}
