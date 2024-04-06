package cz.cvut.fit.tjv.semprojheinkhan.repositories;

import cz.cvut.fit.tjv.semprojheinkhan.entities.Course;
import cz.cvut.fit.tjv.semprojheinkhan.entities.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    @Query("SELECT COUNT(DISTINCT s) " +
            "FROM Instructor i JOIN i.teaches c JOIN c.attendedBy s " +
            "WHERE i.instructorID = :instructorId")
    Integer calculateCurrentWorkload(Long instructorId);
}
