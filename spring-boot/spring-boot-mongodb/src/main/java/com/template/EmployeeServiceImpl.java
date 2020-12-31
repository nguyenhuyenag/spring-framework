package com.template;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public long getMaxEmpId() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "id"));
		query.limit(1);
		Employee maxObject = mongoTemplate.findOne(query, Employee.class);
		if (maxObject == null) {
			return 0L;
		}
		return maxObject.getId();
	}

	@Override
	public long updateEmployee(String empNo, String fullName, Date hireDate) {
		Query query = new Query(Criteria.where("empNo").is(empNo));
		Update update = new Update();
		update.set("fullName", fullName);
		update.set("hireDate", hireDate);

		UpdateResult result = this.mongoTemplate.updateFirst(query, update, Employee.class);

		if (result != null) {
			return result.getModifiedCount();
		}

		return 0;
	}

}
