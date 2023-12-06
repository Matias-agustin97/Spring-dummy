package com.letsgo.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Objects;
import java.util.Optional;

//In sinthesis, we need to mark the class we want spring to instaciete as a bean with component, and
//the recieving class with Autowired.We can use Component or Service
@Service
public class StudentService {

    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
      return  studentRepository.findAll();
    }

    public Student getStudent(Student student){
        return studentRepository.getReferenceById(student.getId());
    }

    public void addStudent(Student student){

        Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());


        if (studentByEmail.isPresent()){
            throw new IllegalStateException("User with that email already exist, please try another");
        }



        studentRepository.save(student);
    }




    //We dont need to implemenet a JPQLquery, we use setter from our entity, to check if we can or cannot update
    //we can use the setters to alter the row in the db
    @Transactional
    public void updateStudent(Long studentId,String name,String email){

        //We get a hold of the student object, so that we can edit the value of the row in the db with
        //setters, we can ddo this thnaks to the @Transactional annotation

        Student student = studentRepository.findById(studentId).orElseThrow(()->{
          return new IllegalStateException("Student with id "+studentId+"not found");
        });

        if (name!=null && name.length()> 0 && Objects.equals(student.getName(),name)){
            //Basically, if the name isnt null, more than 0 characters, and not the same that the name
            //on the db, do this.change his name to the one i ll give you
            student.setName(name);

        if (email !=null &&email.length()>6 && !Objects.equals(student.getEmail(),email)){
            //if the mail isnt null, is bigger than 6, and is diffrent that the one the student has, do this

            student.setEmail(email);
        }

        }
    }




    public void deleteStudent(Long id){

        boolean exist =studentRepository.existsById(id);

        if (exist){
            studentRepository.deleteById(id);
        }else {
            throw new IllegalStateException("No user with "+ id+ " id was found");
        }



    }






}
//List.of(
//        new Student(1L,"Matias","mcuervo@lesko.com.ar", LocalDate.of(1997, Month.MARCH,20),26),
//        new Student(1L,"Alba","ajames@lesko.com.ar", LocalDate.of(1996, Month.SEPTEMBER,5),27),
//        new Student(1L,"Adela","adelgadillo@lesko.com.ar", LocalDate.of(2000, Month.MAY,23),23)
//        );