package pbo.f01.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "Std_id", nullable = false, length = 255 )
    private String id;
    @Column(name = "Std_name", nullable = false, length = 255 )
    private String name;
    @Column(name = "Std_year", nullable = false, length = 255 )
    private String year;
    @Column(name = "Std_gender", nullable = false, length = 255 )
    private String gender;
    @Column(name = "Std_Dorm")
    private String dorm;

    public Student(){

    }
    public Student(String id, String name, String year, String gender){
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.dorm = "";
    }

    @Override
    public String toString(){
        if(this.dorm.equals("")){
            return id+"|"+name+"|"+year+"|"+gender;
        } else {
            return id+"|"+name+"|"+year+"|"+gender+"|"+dorm;
        }
    }
    


}
