package com.service;

public interface SpringTransaction {

	void rollBackWithStatus();

	void testRollBack();

	void hibernateTransaction();

}
