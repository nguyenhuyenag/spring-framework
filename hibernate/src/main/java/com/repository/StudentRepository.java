package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.embeddable.Student;
import com.entity.embeddable.Student.StudentId;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentId> {

	Optional<Student> findById(StudentId id);

	@Query(value = "SELECT s FROM Student s WHERE s.id.id = :id OR s.id.code = :code")
	Student getByJpaQuery(@Param("id") int id, @Param("code") int code);

	@Query(value = "SELECT * FROM student s WHERE s.id = :id OR s.code = :code", nativeQuery = true)
	Student getByNativeQuery(@Param("id") int id, @Param("code") int code);

}
