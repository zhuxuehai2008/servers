package com.zxh.core.entity;

public class Pair<T,V> {
	public T key;
	public V value;
	public Pair(T key,V value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}
}
