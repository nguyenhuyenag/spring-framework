package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.ProceduceRepository;
import com.service.ProcedureService;

@Service
public class ProcedureServiceImpl implements ProcedureService {

	@Autowired
	private ProceduceRepository pepository;

	@Override
	public void callProcedure() {
		pepository.callProcedure().forEach(t->System.out.println());
	}

	@Override
	public void callProcedureWithParam() {
		pepository.callProcedureWithParam("th").forEach(t->System.out.println());
	}

}
