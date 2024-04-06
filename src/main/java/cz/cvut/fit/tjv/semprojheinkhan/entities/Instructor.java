package cz.cvut.fit.tjv.semprojheinkhan.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Instructor implements EntityWithId<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ins_seq")
    @SequenceGenerator(name = "ins_seq", sequenceName = "instructorid_seq", initialValue = 100, allocationSize = 1)
    private Long instructorID;
    private String instructorName;
    private Integer salary;
    private String officeLocation;
    @OneToMany (mappedBy = "taughtBy")
    @JsonIgnore
    private Collection<Course> teaches;

    public Long getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Long instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public Collection<Course> getTeaches() {
        return teaches;
    }

    public void setTeaches(Collection<Course> teaches) {
        this.teaches = teaches;
    }

    @Override
    public Long getId() {
        return instructorID;
    }

    @Override
    public void setId(Long aLong) {
        this.instructorID = aLong;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorID=" + instructorID +
                ", instructorName='" + instructorName + '\'' +
                ", salary=" + salary +
                ", officeLocation='" + officeLocation + '\'' +
                ", teaches=" + teaches +
                '}';
    }
}
