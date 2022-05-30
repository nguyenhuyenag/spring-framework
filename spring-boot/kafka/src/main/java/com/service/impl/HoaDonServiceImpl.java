package com.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.History;
import com.model.HoaDon;
import com.repository.HistoryRepository;
import com.repository.HoaDonRepository;
import com.service.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService {
	
	private static final Logger LOG = LoggerFactory.getLogger(HoaDonServiceImpl.class);

	@Autowired
	private HoaDonRepository repository;
	
	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public void reset() {
		LOG.info("Reset database ...");
		// historyRepository.deleteAll();
		repository.resetTinhTrangGui(); // set = 0
	}

	@Override
	public void onSuccess(HoaDon hoadon) {
		String mtp = hoadon.getMatdiep();
		repository.updateTinhTrangGui(hoadon.getGuid());
		if (!historyRepository.existsByMaThongDiep(mtp)) {
			historyRepository.save(new History(mtp));
		} else {
			LOG.info("Duplicate");
			historyRepository.save(new History(mtp, "Duplicate"));
			System.exit(0);
		}
	}

	@Override
	public List<HoaDon> findAllWithLimit(int limit) {
		return repository.findAllWithLimit(limit);
	}

//	@Override
//	public void saveHistory(String mathongdiep) {
//		// historyRepository.existsById(id)
//	}

}
