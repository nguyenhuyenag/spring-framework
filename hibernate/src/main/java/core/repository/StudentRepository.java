package core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import core.entity.embeddable.Student;
import core.entity.embeddable.StudentId;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentId> {

	Optional<Student> findById(StudentId id);

	@Query(value = "select * from Student s where s.id = :id or s.code = :code", nativeQuery = true)
	Student getByNativeQuery(@Param("id") Integer id, @Param("code") Integer code);

	@Query(value = "select s from Student s where s.id.id = :id or s.id.code = :code")
	Student getByJpaQuery(@Param("id") Integer id, @Param("code") Integer code);

}
