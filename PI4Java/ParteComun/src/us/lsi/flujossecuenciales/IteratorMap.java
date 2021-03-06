package us.lsi.flujossecuenciales;

import java.util.Iterator;
import java.util.function.Function;

public class IteratorMap<E,R> implements Iterator<R>, Iterable<R> {
	
	private Iterator<E> iterator;
	private Function<E,R> fmap;
	
	public static <E,R> IteratorMap<E,R> of(Iterator<E> iterator, Function<E, R> fmap) {
		return new IteratorMap<>(iterator, fmap);
	}
	
	public IteratorMap(Iterator<E> iterator, Function<E, R> fmap) {
		super();
		this.iterator = iterator;
		this.fmap = fmap;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public R next() {
		return this.fmap.apply(this.iterator.next());
	}
	
	@Override
	public Iterator<R> iterator() {
		return this;
	}
	
}