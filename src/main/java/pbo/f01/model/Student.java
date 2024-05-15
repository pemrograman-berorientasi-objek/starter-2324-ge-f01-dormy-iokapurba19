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
    public Student(String id, String name, String year, String gender, String dorm){
        this.id = id;
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.dorm = dorm;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getYear(){
        return this.year;
    }
    public String getGender(){
        return this.gender;
    }

    @Override
    public String toString(){
        return id+"|"+name+"|"+year+"|"+gender+"|"+dorm;
        // if(this.dorm.equals("")){
        //     return id+"|"+name+"|"+year+"|"+gender;
        // } else {
        // }
    }
    


}
