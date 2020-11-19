package quartz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quartz.entity.Quartz;
import quartz.repository.QuartzRepository;
import quartz.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	private QuartzRepository repository;

	@Override
	public void insert() {
		List<Quartz> list = new ArrayList<>();
		int random = RandomUtils.nextInt(1, 5);
		for (int i = 0; i < random; i++) {
			String text = UUID.randomUUID().toString();
			list.add(new Quartz(text));
		}
		repository.saveAll(list);
		System.out.println("Insert " + list.size() + " record");
	}

	@Override
	public void delete() {
		List<Quartz> list = repository.findAll();
		int size = list.size();
		int from = RandomUtils.nextInt(0, size / 2);
		int to = RandomUtils.nextInt(size / 2, size);
		List<Quartz> listRemove = list.subList(from, to);
		repository.deleteAll(listRemove);
		System.out.println("Delete " + (to - from) + " record");
	}

}
