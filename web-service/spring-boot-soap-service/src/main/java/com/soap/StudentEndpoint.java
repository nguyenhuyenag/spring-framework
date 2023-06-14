package com.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.howtodoinjava.xml.school.StudentDetailsRequest;
import com.howtodoinjava.xml.school.StudentDetailsResponse;

@Endpoint
public class StudentEndpoint {

	private StudentRepository studentRepository;

	public StudentEndpoint(StudentRepository StudentRepository) {
		this.studentRepository = StudentRepository;
	}

	@ResponsePayload
	@PayloadRoot(namespace = WsConfig.NAMESPACE_URI, localPart = "StudentDetailsRequest")
	public StudentDetailsResponse getStudent(@RequestPayload StudentDetailsRequest request) {
		StudentDetailsResponse response = new StudentDetailsResponse();
		response.setStudent(studentRepository.findStudent(request.getName()));
		return response;
	}
	
}