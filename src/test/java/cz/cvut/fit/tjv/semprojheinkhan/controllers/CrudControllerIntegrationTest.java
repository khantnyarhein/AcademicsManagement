package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrudControllerIntegrationTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CrudControllerSubclass controller;

    @Test
    void createStudentWithoutId() {
        var newStudent = new Student();
        newStudent.setStudentName("New Student");

        var createdStudent = controller.create(newStudent);

        Assertions.assertNotNull(createdStudent.getId());
        Assertions.assertEquals(newStudent, createdStudent);

        studentRepository.deleteById(createdStudent.getId());
    }

    @Test
    void createDuplicate() {
        var student = new Student();
        student.setStudentName("test");
        student.setId(1L);
        studentRepository.save(student);

        Assertions.assertThrows(
                ResponseStatusException.class, () -> controller.create(student)
        );
    }

    @Test
    void createStudentWithId() {
        var student = new Student();
        student.setStudentName("test");
        student.setId(1L);

        var res = controller.create(student);

        Assertions.assertNotNull(res.getId());
        Assertions.assertEquals(student.getId(), res.getId());
        Assertions.assertEquals(student.getStudentName(), res.getStudentName());

        studentRepository.deleteById(res.getId());
    }
}