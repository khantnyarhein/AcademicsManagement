package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.StudentRepository;
import cz.cvut.fit.tjv.semprojheinkhan.services.StudentService;
import org.springframework.stereotype.Component;

@Component
class CrudControllerSubclass extends CrudController<Student, Long, StudentService, StudentRepository> {
    public CrudControllerSubclass(StudentService service) {
        super(service);
    }
}
