package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.StudentRepository;
import cz.cvut.fit.tjv.semprojheinkhan.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController extends CrudController<Student, Long, StudentService, StudentRepository>{
    public StudentController(StudentService service) {
        super(service);
    }

    @GetMapping("/search")
    public Iterable<Student> readAllOrByStudentName(@RequestParam Optional<String> studentName) {
        if (studentName.isEmpty()) {
            return super.readAll();
        }
        else {
            return service.readByStudentName(studentName.get());
        }
    }

    @GetMapping("/searchinstructor/{id}")
    public List<StudentRepository.InstructorCourseProjection>
                searchInstructorByStudentId (@PathVariable Long id) {
        return service.getInstructorsForStudent(id);
    }
}
