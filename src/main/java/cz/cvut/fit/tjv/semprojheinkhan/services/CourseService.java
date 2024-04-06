package cz.cvut.fit.tjv.semprojheinkhan.services;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class CourseService extends CrudService<Course, Long, CourseRepository>{
    public CourseService(CourseRepository repository) {
        super(repository);
    }
}
