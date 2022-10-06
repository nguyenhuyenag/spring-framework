package com.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.request.SeekRequest;
import com.response.SeekResponse;
import com.service.MessageService;

@Controller
public class HoaDonController {

	@Autowired
	private MessageService messageService;

	@GetMapping({ "/", "invoice" })
	public String hoadon() {
		return "invoice";
	}

	@GetMapping("search-invoice")
	public ResponseEntity<Set<SeekRequest>> searchInvoice(String database, String matdiep, String fromdate,
			String todate) { // yyyy-MM-dd
		Set<SeekRequest> danhsach = messageService.findAllTuNgayDenNgay(database, matdiep, fromdate, todate);
		return ResponseEntity.ok(danhsach);
	}

	@PostMapping("seek-invoice")
	public ResponseEntity<?> seekInvoice(@RequestBody SeekRequest hoadon) {
		boolean check = messageService.seek(hoadon);
		return ResponseEntity.ok(check);
	}

	@PostMapping("seek-multi-invoice")
	public ResponseEntity<?> seekMultiInvoice(@RequestBody List<SeekRequest> seekList) {
		// test
//		List<SeekResponse> list = new ArrayList<>();
//		for (SeekRequest seek : seekList) {
//			list.add(new SeekResponse(seek.getMathongdiep(), System.currentTimeMillis() % 2 == 0));
//			try {
//				Thread.sleep(300);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		List<SeekResponse> list = messageService.seekMultiple(seekList);
		return ResponseEntity.ok(list);
	}

}
