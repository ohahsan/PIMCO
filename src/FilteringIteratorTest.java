

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilteringIteratorTest {

	@Test
	public void testOddsOnly() {
		IObjectTest oddChecker = new IObjectTest() {
			public boolean test(Object o) {
				if (!(o instanceof Integer)) return false;
				Integer i = (Integer)o;
				return i % 2 == 1;
			}
		};
		
		Integer[] arr = {1, 3, 5, 2, 6, 3, 4};
		List<Integer> l = Arrays.asList(arr);
		
		FilteringIterator<Integer> fi = new FilteringIterator<>(l.iterator(), oddChecker);
		
		assertTrue(fi.next().equals(1));
		assertTrue(fi.next().equals(3));
		assertTrue(fi.next().equals(5));
		assertTrue(fi.next().equals(3));
		assertFalse(fi.hasNext());	
	}
	
	@Test
	public void testEmpty() {
		IObjectTest autoFail = new IObjectTest() {
			public boolean test(Object o) {
				return false;
			}
		};
		
		Character[] x = {'a', 'b', 'c', 'd'};
		Character[] y = {};
		
		FilteringIterator<Character> xi = new FilteringIterator<>(Arrays.asList(x).iterator(), autoFail);
		FilteringIterator<Character> yi = new FilteringIterator<>(Arrays.asList(y).iterator(), autoFail);
		
		assertFalse(xi.hasNext());
		assertFalse(yi.hasNext());
	}

}
