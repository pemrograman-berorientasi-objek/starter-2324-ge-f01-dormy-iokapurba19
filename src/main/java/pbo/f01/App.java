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
                addStudent(pecahan[1], pecahan[2], pecahan[3], pecahan[4]);
            }
            if(pecahan[0].equals("dorm-add")){
                addDorm(pecahan[1], pecahan[2], pecahan[3]);
            }
            if(pecahan[0].equals("assign-student")){
                assignStudent(pecahan[1], pecahan[2]);
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
    private static void addStudent(String id, String nama, String tahun, String gender){
        entityManager.getTransaction().begin();
        Student student = new Student(id, nama, tahun, gender);
        entityManager.persist(student);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    private static void addDorm(String nama, String capacity, String gender){
        entityManager.getTransaction().begin();
        Dorm dorm = new Dorm(nama, capacity, gender);
        entityManager.persist(dorm);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    private static void assignStudent(String id, String asrama){
        String jpql = "UPDATE Student d SET d.dorm = :asrama WHERE d.id = :nim";
        entityManager.getTransaction().begin();
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("asrama", asrama);
        query.setParameter("nim", "%" + id + "%");
        query.executeUpdate();

        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    private static void displayAll(){
        String Djpql = "SELECT d FROM Dorm d";
        String Sjpql = "SELECT s FROM Student s";

        List<Dorm> Dorms = entityManager.createQuery(Djpql, Dorm.class)
                    .getResultList();
        List<Student> Students = entityManager.createQuery(Sjpql, Student.class)
                    .getResultList();

        System.out.println("DisplayingDorm");
        for (Dorm d : Dorms) {
            System.out.println(d);
            
        }
        System.out.println("displayAllStudents--");
        for (Student s : Students) {
            System.out.println(s);

        }
    }
}