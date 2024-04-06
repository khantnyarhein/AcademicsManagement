package cz.cvut.fit.tjv.semprojheinkhan.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Student implements EntityWithId<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stu_seq")
    @SequenceGenerator(name = "stu_seq", sequenceName = "studentid_seq", initialValue = 100, allocationSize = 1)
    private Long studentID;
    private String studentName;
    private LocalDate dateOfBirth;
    private String contactInfo;
    private Float gpa;
    @ManyToMany
    @JoinTable(name = "course_attended",
            joinColumns = @JoinColumn(name = "attended_by_studentid"),
            inverseJoinColumns = @JoinColumn(name = "attends_courseid"))
    private Collection<Course> attends;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Collection<Course> getAttends() {
        return attends;
    }

    public void setAttends(Collection<Course> attends) {
        this.attends = attends;
    }

    @Override
    public Long getId() {
        return studentID;
    }

    @Override
    public void setId(Long aLong) {
        this.studentID = aLong;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contactInfo='" + contactInfo + '\'' +
                ", gpa=" + gpa +
                ", attends=" + attends +
                '}';
    }
}
