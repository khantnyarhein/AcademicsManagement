package cz.cvut.fit.tjv.semprojheinkhan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Optional;

@Entity
public class Course implements EntityWithId<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "courseid_seq", initialValue = 100, allocationSize = 1)
    private Long courseID;
    private String courseName;
    private Integer credits;
    private Integer capacity;
    private String department;
    @ManyToMany(mappedBy = "attends")
    @JsonIgnore
    private Collection<Student> attendedBy;

    @ManyToOne
    private Instructor taughtBy;

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Collection<Student> getAttendedBy() {
        return attendedBy;
    }

    public void setAttendedBy(Collection<Student> attendedBy) {
        this.attendedBy = attendedBy;
    }

    public Instructor getTaughtBy() {
        return taughtBy;
    }

    public void setTaughtBy(Instructor taughtBy) {
        this.taughtBy = taughtBy;
    }
    @Override
    public Long getId() {
        return courseID;
    }
    @Override
    public void setId(Long aLong) {
        this.courseID = aLong;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", capacity=" + capacity +
                ", department='" + department + '\'' +
                ", attendedBy=" + attendedBy +
                ", taughtBy=" + taughtBy +
                '}';
    }
}
