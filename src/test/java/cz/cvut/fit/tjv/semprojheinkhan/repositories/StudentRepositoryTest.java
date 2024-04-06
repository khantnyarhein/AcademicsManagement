package cz.cvut.fit.tjv.semprojheinkhan.repositories;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Instructor;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void basicCrudOperations() {
        // Create
        Student student = new Student();
        student.setStudentName("John Doe");

        student = studentRepository.save(student);
        Long studentId = student.getId();
        Assertions.assertNotNull(studentId);

        // Read
        Student retrievedStudent = studentRepository.findById(studentId).orElse(null);
        Assertions.assertNotNull(retrievedStudent);
        Assertions.assertEquals("John Doe", retrievedStudent.getStudentName());

        // Update
        retrievedStudent.setStudentName("Updated Name");
        studentRepository.save(retrievedStudent);

        // Verify Update
        Student updatedStudent = studentRepository.findById(studentId).orElse(null);
        Assertions.assertNotNull(updatedStudent);
        Assertions.assertEquals("Updated Name", updatedStudent.getStudentName());

        // Delete
        studentRepository.deleteById(studentId);

        // Verify Delete
        Student deletedStudent = studentRepository.findById(studentId).orElse(null);
        Assertions.assertNull(deletedStudent);
    }

    @Test
    void findAllByStudentName() {
        Student s1 = new Student();
        Student s2 = new Student();

        String studentName = "John Doe";
        s1.setStudentName(studentName);
        s2.setStudentName(studentName);

        studentRepository.saveAll(List.of(s1, s2));

        var res = studentRepository.findAllByStudentName(studentName);
        Assertions.assertIterableEquals(List.of(s1, s2), res);
    }

    @Test
    void findInstructorsTeachingStudent() {
        Instructor instructor = new Instructor();
        instructor.setInstructorName("Instructor1");
        instructorRepository.save(instructor);

        Course course = new Course();
        course.setCourseName("Math101");
        courseRepository.save(course);

        Student student = new Student();
        student.setStudentName("John Doe");
        studentRepository.save(student);
        student.setAttends(new ArrayList<>());
        student.getAttends().add(course);

        course.setTaughtBy(instructor);

        List<StudentRepository.InstructorCourseProjection> result = studentRepository.findInstructorsTeachingStudent(student.getId());

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }
}