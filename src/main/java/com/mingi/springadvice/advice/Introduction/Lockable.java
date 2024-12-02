package com.mingi.springadvice.advice.Introduction;

public interface Lockable {
	void lock();
	void unlock();
	boolean locked();
}
