package com.service;

import java.util.List;
import java.util.Set;

import com.request.SeekRequest;
import com.response.SeekResponse;
import com.response.UnConsumer;

public interface MessageService {

	void send(String message);

	// seek 1 hoadon
	boolean seek(SeekRequest hoadon);

	//  seek nhieu hoa don
	List<SeekResponse> seekMultiple(List<SeekRequest> seekList);
	
	Set<SeekRequest> findAllTuNgayDenNgay(String database, String matdiep, String fromdate, String todate);
	
	List<UnConsumer> countUnreadMessage();

}
