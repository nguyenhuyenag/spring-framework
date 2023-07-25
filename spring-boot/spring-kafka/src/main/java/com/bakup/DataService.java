package com.bakup;

public interface DataService {

	void autoInsert();

	void onSuccess(Data ipsum);

	void receiveMessage(String listener, String message);

}
