package pbo.f01;
import java.sql.ResultSet;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

/**
 * 12S22031 - Ioka Purba
 * 12S22039 - Meilyna Hutajulu
 */
public class App {

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;
    public static void main(String[] _args) {
        factory = Persistence.createEntityManagerFactory("dormy_pu");
        entityManager = factory.createEntityManager();
        cleanTables();
        Scanner scan = new Scanner(System.in);
        while(true){
            String input = scan.nextLine();
            String[] pecahan = new String[5];
            pecahan = input.split("#");
            if(pecahan[0].equals("---")){
                displayAll();
                break;
            }
            if(pecahan[0].equals("student-add")){
                addStudent(pecahan[1], pecahan[2], pecahan[3], pecahan[4], "");
            }
            if(pecahan[0].equals("dorm-add")){
                addDorm(pecahan[1], pecahan[2], pecahan[3]);
            }
            if(pecahan[0].equals("assign")){
                assignStudent(pecahan[1], pecahan[2]);
            }
            if(pecahan[0].equals("delete-student")){
                deleteStudent(pecahan[1]);
            }
        }
        scan.close();
    }

    private static void cleanTables(){
        String deleteStudentJpql = "DELETE FROM Student c";
        String deleteDormJpql = "DELETE FROM Dorm g";
        entityManager.getTransaction().begin();
        entityManager.createQuery(deleteStudentJpql).executeUpdate();
        entityManager.createQuery(deleteDormJpql).executeUpdate();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    private static void addStudent(String id, String nama, String tahun, String gender, String dorm) {
        // Check for existing student using a more efficient query
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Student s WHERE s.id = :id", Long.class);
        query.setParameter("id", id);
        long existingCount = (long) query.getSingleResult();
    
        if (existingCount == 0) {
            // Create and persist new student if not found
            entityManager.getTransaction().begin();
            Student student = new Student(id, nama, tahun, gender, dorm);
            entityManager.persist(student);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Student with ID " + id + " already exists."); // Informative message
        }
    }
    
    private static void addDorm(String nama, String capacity, String gender){
        entityManager.getTransaction().begin();
        Dorm dorm = new Dorm(nama, capacity, gender);
        entityManager.persist(dorm);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    // private static void assignStudent(String id, String asrama){
    //     String jpql = "SELECT s FROM Student s WHERE s.id LIKE :nim";
    //     TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
    //     query.setParameter("nim", id);
    //     List<Student> Students = query.getResultList();
    //     String tes = Students.toString();
    //     int edge = tes.length();
    //     String simpan = (tes.substring(1, edge-1));
    //     String[] token = simpan.split("|");
    //     for(int i =0; i < token.length; i++ ){
    //         if(token[i].equals("|")){
    //             token[i] = "\0";
    //             token[i] = "#";
    //         }
    //     }
    //     String rungkad = "";
    //     for(int i =0; i < token.length; i++ ){
    //         rungkad += token[i];
    //     }
    //     String[] capek = rungkad.split("#");
    //     deleteStudent(id);
    //     entityManager.flush();
    //     entityManager.getTransaction().commit();
    //     addStudent(capek[0], capek[1], capek[2], capek[3], asrama);
    // }
    private static void assignStudent(String id, String asrama){
        String Sjpql = "SELECT s FROM Student s WHERE s.id LIKE :nim";
        TypedQuery<Student> Squery = entityManager.createQuery(Sjpql, Student.class);
        Squery.setParameter("nim", id);
        Boolean ada = true;
        List<Student> students = Squery.getResultList();

        if (students.isEmpty()) {
            ada = false;
        }
        if(ada){
            String jpql = "SELECT s FROM Student s WHERE s.id LIKE :nim";
            TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
            query.setParameter("nim", id);
            List<Student> Students = query.getResultList();
            String tes = Students.toString();
            int edge = tes.length();
            String simpan = (tes.substring(1, edge-1));
            String[] token = simpan.split("|");
            for(int i =0; i < token.length; i++ ){
                if(token[i].equals("|")){
                    token[i] = "\0";
                    token[i] = "#";
                }
            }
            String rungkad = "";
            for(int i =0; i < token.length; i++ ){
                rungkad += token[i];
            }
            String[] capek = rungkad.split("#");
            deleteStudent(id);
            entityManager.flush();
            entityManager.getTransaction().commit();
            addStudent(capek[0], capek[1], capek[2], capek[3], asrama);

        }
    }
    private static void deleteStudent(String id){
        String jpql = "DELETE FROM Student c WHERE c.id = :nim";
        entityManager.getTransaction().begin();
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("nim",id);
        query.executeUpdate();
    }
    private static void displayAll(){
        String Djpql = "SELECT d FROM Dorm d ORDER BY d.name";
        List<Dorm> Dorms = entityManager.createQuery(Djpql, Dorm.class)
        .getResultList();
        
        for (Dorm d : Dorms) {
            int index = 0;
            String Sjpql = "SELECT s FROM Student s WHERE s.dorm LIKE :asrama";
            TypedQuery<Student> query = entityManager.createQuery(Sjpql, Student.class);
            query.setParameter("asrama", d.getName());
            List<Student> Students = query.getResultList();
            for (Student s : Students) {
                index++;
            }   
            System.out.println(d+"|"+index);
            for (Student s : Students) {
                System.out.println(s.getId()+"|"+s.getName()+"|"+s.getYear());
            }   
        }
    }
}