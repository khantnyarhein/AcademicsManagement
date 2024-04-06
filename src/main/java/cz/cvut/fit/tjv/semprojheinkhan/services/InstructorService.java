package cz.cvut.fit.tjv.semprojheinkhan.services;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Instructor;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService extends CrudService<Instructor, Long, InstructorRepository>{
    @Autowired
    public CourseService courseService;

    public InstructorService(InstructorRepository repository) {
        super(repository);
    }

    public boolean canAddNewCourse(Long instructorId, Integer newCourseCapacity) {
        Integer currentWorkload = repository.calculateCurrentWorkload(instructorId);
        int threshold = 150;
        return (currentWorkload + newCourseCapacity) <= threshold;
    }

    public Optional<Course> createNewCourse(Course course, Long instructorId) {
        Optional<Instructor> instructorOpt = repository.findById(instructorId);
        if(!instructorOpt.isPresent()) {
            throw new EntityDoesNotExistException();
        }

        if(canAddNewCourse(instructorId, course.getCapacity())){
            course.setTaughtBy(instructorOpt.get());
            return Optional.of(courseService.repository.save(course));
        } else {
            return Optional.empty();
        }
    }

    public Integer returnWorkload(Long instructorId) {
        return repository.calculateCurrentWorkload(instructorId);
    }

}
