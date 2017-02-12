import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteringIterator<T> implements Iterator<T> {
	
	private Iterator<T> baseIterator;
	private IObjectTest tester;
	private T nextObj;
	private boolean hasNextCalled;
	
	public FilteringIterator(Iterator<T> iterator, IObjectTest tester) {
		this.baseIterator = iterator;
		this.tester = tester;
		this.nextObj = null;
		this.hasNextCalled = false;
	}

	@Override
	public boolean hasNext() {
		while (!hasNextCalled && baseIterator.hasNext() && nextObj == null) {
			nextObj = baseIterator.next();
			if (!tester.test(nextObj)) {
				nextObj = null;
			}
		}
		hasNextCalled = true;
		return nextObj != null;
	}

	@Override
	public T next() {
		if (!hasNextCalled) {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
		}
		T ret = nextObj;
		nextObj = null;
		hasNextCalled = false;
		return ret;
	}

}
