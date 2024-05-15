package pbo.f01.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "dorms")
public class Dorm {

    @Id
    @Column(name = "Drm_name", length = 225, nullable = false)
    private String name;
    @Column(name = "Drm_capacity", nullable = false)
    private String capacity;
    @Column(name = "Drm_gender", length = 225, nullable = false)
    private String gender;

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    private Set<Student> students;

    public Dorm(){
        //empty
    }
    
    public Dorm(String name, String capacity, String gender) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
    }

    public Dorm(String name, String capacity, String gender, Set<Student> students) {
        this.name = name;
        this.capacity = capacity;
        this.gender = gender;
        this.students = students;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return name + "|" + capacity + "|" + gender;
    }

}