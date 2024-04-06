package cz.cvut.fit.tjv.semprojheinkhan.repositories;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Instructor;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Collection<Student> findAllByStudentName(String studentName);

    public interface InstructorCourseProjection {
        Instructor getInstructor();
        String getCourseName();
    }

    @Query("SELECT i AS instructor, c.courseName AS courseName " +
            "FROM Instructor i JOIN i.teaches c JOIN c.attendedBy s " +
            "WHERE s.studentID = :studentId")
    List<InstructorCourseProjection> findInstructorsTeachingStudent(Long studentId);


}
