package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import com.sun.source.tree.ModuleTree;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.StudentRepository;
import cz.cvut.fit.tjv.semprojheinkhan.services.CrudService;
import cz.cvut.fit.tjv.semprojheinkhan.services.EntityAlreadyExistsException;
import cz.cvut.fit.tjv.semprojheinkhan.services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrudControllerUnitTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private CrudControllerSubclass controller;

    Student studentWithId;
    Student studentWithoutId;

    @BeforeEach
    void setUp() {
        studentWithoutId = new Student();
        studentWithId = new Student();

        studentWithoutId.setStudentName("test");
        studentWithoutId.setGpa(4.0F);
        studentWithoutId.setDateOfBirth(LocalDate.parse("2000-01-01"));

        studentWithId.setStudentName(studentWithoutId.getStudentName());
        studentWithId.setGpa(studentWithoutId.getGpa());
        studentWithId.setDateOfBirth(studentWithoutId.getDateOfBirth());
        studentWithId.setId(1L);

        Mockito.when(studentService.create(studentWithId)).thenReturn(studentWithId);
        Mockito.when(studentService.create(studentWithoutId)).thenReturn(studentWithId);
    }

    @Test
    void createDuplicate() {
        Mockito.when(studentService.create(studentWithId)).
                thenThrow(EntityAlreadyExistsException.class);

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> controller.create(studentWithId));
    }

    @Test
    void createStudentWithoutId() {
        var res = controller.create(studentWithoutId);
        Assertions.assertEquals(studentWithId, res);
        Mockito.verify(studentService, Mockito.atLeastOnce()).create(studentWithoutId);
    }

    @Test
    void createStudentWithId() {
        var res = controller.create(studentWithId);
        Assertions.assertEquals(studentWithId, res);
        Mockito.verify(studentService, Mockito.atLeastOnce()).create(studentWithId);
    }
}