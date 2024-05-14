package com.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.repository.OrderDetailRepository;
import com.repository.OrderRepository;
import com.repository.PersonRepository;
import com.service.CriteriaService;
import com.service.EntityManagerService;
import com.service.FindByExample;
import com.service.GetSession;
import com.service.JDBCTemplateService;
import com.service.JpaSelectSpecificColumns;
import com.service.MappingQueryToPOJOService;
import com.service.MySQLToolService;
import com.service.ProcedureService;
import com.service.QueryDSLService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	EntityManagerService emService;

	@Autowired
	CriteriaService vbService;

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
	JpaSelectSpecificColumns springDataSelectSpecificColumns;

	@Autowired
	MySQLToolService mysqlToolService;

	// @Autowired
	// MapStructServiceTest mapStructServiceTest;

	@Autowired
	FindByExample findByExample;
	
	@Autowired
	GetSession getSession;

	@Override
	public void run(String... args) throws Exception {
		callTest();
	}

	public void callTest() {
		// selectSomeField();
		// service();
		// vbService();
		// repository();
		// proceduce();
		// queryDSLService();
		// jdbcTemplateService();
		// mapStructServiceTest.convert();
		// System.out.println(mysqlToolService.checkTableExist("categories"));

		// try (Connection connection = mysqlToolService.getConnection()) {
		// System.out.println(connection.getMetaData().getURL().toString());
		// };

		// findByExample.test();
		getSession.getSession();
	}

	public void selectSomeField() {
		// selectSomeFieldService.forTuple();
		// selectSomeFieldService.forSession();
		// selectSomeFieldService.forJdbcTemplate();
		// selectSomeFieldService.forEntityManager();
		// selectSomeFieldService.forSqlResultSetMapping();
		List<com.service.JpaSelectSpecificColumns.SubOrder> findAllSubOrder = springDataSelectSpecificColumns
				.findAllSubOrder();
		findAllSubOrder.forEach(t -> System.out.println(t));
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
