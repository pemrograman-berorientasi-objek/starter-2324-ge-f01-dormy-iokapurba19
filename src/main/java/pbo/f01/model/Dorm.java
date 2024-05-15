package pbo.f01.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 12S22031 - Ioka Purba
 * 12S22039 - Meilyna Hutajulu
 */

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
    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return name+"|"+gender+"|"+capacity;
    }
}
