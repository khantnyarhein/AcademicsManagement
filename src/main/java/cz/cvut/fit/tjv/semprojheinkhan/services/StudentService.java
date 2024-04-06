package cz.cvut.fit.tjv.semprojheinkhan.services;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Student;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.InstructorRepository;
import cz.cvut.fit.tjv.semprojheinkhan.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends CrudService<Student, Long, StudentRepository>{
    public StudentService(StudentRepository repository, CourseService courseService) {
        super(repository);
    }

    public Collection<Student> readByStudentName(String studentName) {
        return repository.findAllByStudentName(studentName);
    }

    public List<StudentRepository.InstructorCourseProjection> getInstructorsForStudent(Long studentId) {
        return repository.findInstructorsTeachingStudent(studentId);
    }
}
