package com.mingi.springadvice.advice.Introduction;

public class LockedException extends RuntimeException {

	public LockedException() {
		super("other is locked, No modification are allowed");
	}
}
