package com.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.jaxb2.generate.StudentRequest;
import com.jaxb2.generate.StudentResponse;

@Endpoint
public class StudentEndpoint {

	private StudentRepository studentRepository;

	public StudentEndpoint(StudentRepository StudentRepository) {
		this.studentRepository = StudentRepository;
	}

	@ResponsePayload
	@PayloadRoot(namespace = WsConfig.NAMESPACE_URI, localPart = "StudentDetailsRequest")
	public StudentResponse getStudent(@RequestPayload StudentRequest request) {
		StudentResponse response = new StudentResponse();
		response.setStudent(studentRepository.findStudent(request.getName()));
		return response;
	}

}