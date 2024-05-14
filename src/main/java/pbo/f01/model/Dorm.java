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
@Table(name = "Dorm")
public class Dorm {
    @Id
    @Column(name = "Drm_name", nullable = false, length = 255 )
    private String name;
    @Column(name = "Drm_capacity", nullable = false, length = 255 )
    private String capacity;
    @Column(name = "Drm_Gender", nullable = false, length = 255 )
    private String gender;

    public Dorm(){
        
    }
    public Dorm(String name, String capasity, String gender){
        this.name = name;
        this.capacity = capasity;
        this.gender = gender;
    }

    @Override
    public String toString(){
        return name+"|"+capacity+"|"+gender;
    }
}
