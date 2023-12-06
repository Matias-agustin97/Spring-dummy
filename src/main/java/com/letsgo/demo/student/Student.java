package com.letsgo.demo.student;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
@Table //This one is for hibernate
@Entity //This is mapping our class to the db
public class Student {

    @Id
    @SequenceGenerator(name = "students_sequence",sequenceName = "students_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "students_sequence")
    private Long id;

    //Learn to fucking type monkey, you mess up the properties file 2 times now,
    //hibernate creates our database sequence

    private String name;
    private String email;
    private LocalDate dob;

    @Transient //Transient makes this field not be a column on the table
    private Integer age;

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public Student(){

    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getAge() {
        //this operation gives us the age of a student
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
