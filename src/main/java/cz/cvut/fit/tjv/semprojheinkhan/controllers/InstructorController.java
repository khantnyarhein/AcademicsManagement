package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Instructor;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.InstructorRepository;
import cz.cvut.fit.tjv.semprojheinkhan.services.EntityDoesNotExistException;
import cz.cvut.fit.tjv.semprojheinkhan.services.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/instructor")
@CrossOrigin(origins = "http://localhost:5173")
public class InstructorController extends CrudController<Instructor, Long, InstructorService, InstructorRepository>{
    public InstructorController(InstructorService service) {
        super(service);
    }

    @PostMapping("/{id}/create/course")
    public Course canAddNewCourse(@RequestBody Course course, @PathVariable Long id) {
        try {
            Optional<Course> res = service.createNewCourse(course, id);
            if (res.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
            }
            return res.get();
        }
        catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/workload")
    public Integer calWorkload(@PathVariable Long id) {
        return service.returnWorkload(id);
    }
}


