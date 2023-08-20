package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.repository.OrderDetailRepository;
import com.repository.OrderRepository;
import com.repository.PersonRepository;
import com.service.EntityManagerService;
import com.service.JDBCTemplateService;
import com.service.MySQLToolService;
import com.service.ProcedureService;
import com.service.QueryDSLService;
import com.service.VocabService;
import com.service.impl.MapStructServiceTest;
import com.service.impl.MappingQueryToPOJOService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	EntityManagerService emService;

	@Autowired
	VocabService vbService;

	@Autowired
	PersonRepository repository;

	@Autowired
	ProcedureService proceduce;

	@Autowired
	QueryDSLService queryDSLService;

	// @Autowired
	// TransactionSevice transactionSevice;

	@Autowired
	JDBCTemplateService jdbcTemplateService;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	MappingQueryToPOJOService selectSomeFieldService;
	
	@Autowired
	MySQLToolService mysqlToolService;
	
	@Autowired
	MapStructServiceTest mapStructServiceTest;

	@Override
	public void run(String... args) throws Exception {
		selectSomeField();
		// service();
		// vbService();
		// repository();
		// proceduce();
		// queryDSLService();
		// jdbcTemplateService();
		// System.out.println(mysqlToolService.checkTableExits("categories"));
		// mapStructServiceTest.convert();
	}
	
	public void selectSomeField() {
		// selectSomeFieldService.forTuple();
		// selectSomeFieldService.forSession();
		// selectSomeFieldService.forJdbcTemplate();
		selectSomeFieldService.forEntityManager();
		// selectSomeFieldService.forSqlResultSetMapping();
	}

	public void service() {
		// service.insert();
		// service.findOne();
		// service.findAllByQuery();
		// service.findAllByTypedQuery();
	}

	public void vbService() {
		vbService.CollectionParam();
		vbService.findUserByEmails();
	}

	public void repository() {
		// epository.findAll().stream().limit(10).forEach(t->System.out.println(t));
		// repository.updateLastModified("actual");
	}

	public void proceduce() {
		proceduce.callProcedureWithParam();
		// proceduce.callProcedureByQuery().forEach(t -> System.out.println(t));
	}

	public void queryDSLService() {
		// queryDSLService.findAll();
		queryDSLService.findOne();
		queryDSLService.forAndCondition();
		queryDSLService.delete();
	}

	public void jdbcTemplateService() {
		jdbcTemplateService.insert();
		jdbcTemplateService.deleteById(8);
		jdbcTemplateService.findAll().forEach(t -> System.out.println(t));
		jdbcTemplateService.findAllName().forEach(t -> System.out.println(t));
		System.out.println(jdbcTemplateService.findMaxAge());
		System.out.println(jdbcTemplateService.findByName("XUMYP").toString());
		jdbcTemplateService.batchUpdate();
	}

}
