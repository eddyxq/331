import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Scanner;


public class PrefixTest {

    @Test
    public void boundaryTests() {
	String input = "";
	Scanner s = new Scanner( input );
	assertEquals("empty expression should return 0",0,Prefix.evaluate( s ));

	input = "15";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return 15",15, Prefix.evaluate( s ) );

	input = "-15";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return -15",-15,Prefix.evaluate( s ) );
    }

    @Test
    public void binaryOperatorTests() {
	String input = "+ 5 10";
	Scanner s = new Scanner( input );
	assertEquals("'"+input+"' should return 15", 15, Prefix.evaluate( s ));

	input = "- 5 10";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return -5", -5, Prefix.evaluate( s ));

	input = "* 10 5";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return 50", 50, Prefix.evaluate( s ));

	input = "/ 10 5";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return 2", 2, Prefix.evaluate( s ));
    }

    @Test
    public void longExpressionTests() {
	// Test with prefix expressions with multiple operators and operands

	String input = "+ / 18 + 3 6 * 5 5";
	Scanner s = new Scanner( input );
	assertEquals("'"+input+"' should return 27", 27, Prefix.evaluate( s ));

	// Test with a very long prefix expression
	input = "/ + 1 * 2 + 10 * 2 + 45 * 2 + 120 * 2 + 210 * 2 + 252 * 2 + 210 * 2 + 120 * 2 + 45 * 2 + 10 2 + 1 * -2 + 9 * -2 + 36 * -2 + 84 * -2 + 126 * -2 + 126 * -2 + 84 * -2 + 36 * -2 + 9 -2";
	s = new Scanner( input );
	assertEquals("'"+input+"' should return -59049", -59049, Prefix.evaluate( s ));
	
	
    }

    public static void main( String[] args ) {
	Result result = JUnitCore.runClasses( PrefixTest.class );
	for (Failure failure : result.getFailures()) {
	    System.out.println(failure.toString());
	}
	
    }
}
