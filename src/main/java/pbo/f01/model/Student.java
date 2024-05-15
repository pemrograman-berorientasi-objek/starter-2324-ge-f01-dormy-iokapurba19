package pbo.f01.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity	
@Table(name = "students")
public class Student {
    @Id 
    @Column(name = "Std_id", nullable = false, length = 255)
    private String id;
    @Column(name = "Std_name", nullable=false, length =255)
    private String name;
    @Column(name = "Std_year", nullable=false, length =255)
    private String year;
    @Column(name = "Std_gender", nullable=false, length =255)
    private String gender;

    @OneToOne(mappedBy = "students", cascade = CascadeType.ALL)
    private Set<Dorm> dorms;

    @Column(name = "Std_Dorm")
    private String dorm;

    public Student(){

    }
    public Student(String id, String name, String year, String gender, String dorm){
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.dorm = dorm;
    }

    public Student(String id, String name, String year, String gender) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
    }

    public Student(String id, String name, String year, String gender , Set<Dorm> dorms) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.dorms = dorms;
    }

    
    public String getid() {
        return id;
    }
    
    public void setid(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getyear() {
        return year;
    }

    public void setyear(String year) {
        this.year = year;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Set<Dorm> getDorms() {
        return dorms;
    }
    
    public void setDorms(Set<Dorm> dorms) {
        this.dorms = dorms;
    }

    @Override
    public String toString() {
        return  id + "|" + name + "|" + year + "|" + gender ;

    }
}