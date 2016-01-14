package datastructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> implements Map<K, V> {
	
	private static final int DEFAULT_CAPACITY=16;
	private static final float DEFAULT_LOAD_FACTOR=0.75f;
	
	private final float loadFactor;
	private Entry<K, V>[] data;
	
	private static class Entry<X, Y> implements Map.Entry<X, Y> {
		
		private X key;
		private Y value;
		private Entry<X, Y> next;
		
		@Override
		public boolean equals(Object o) {
			return false;
		}
		
		@Override
		public X getKey() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Y getValue() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Y setValue(Y arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
