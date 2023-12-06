package com.letsgo.demo.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

    private final StudentService studentService;


    //We make use of dependency injection by not instancieting a new object  with the keyword new, but instead delegate the task to spring, spring will create a studenservice object
    //for us, we just need to use the @Autowired annotation in the constructor
    //We also need to annotate the class studentService as a bean for spring to instaciete, for that we use @Component over the class definition
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> getList() {
        return studentService.getStudents();

    }


    @PostMapping(path = "/add")
    public void registerNewStudent(@RequestBody Student student){
        //We take the request body and map it into a student
        studentService.addStudent(student);
    }



    @DeleteMapping(path = "{studentId}")
    public void delateStudent(@PathVariable("studentId")Long id ){
    //We can take the path and use it in our operations with the @Pathvariable
        studentService.deleteStudent(id);


    }



    //The url would look something like http:localhost:8080/api/students/1?name=Mateo
    //1 is the student id path variable, and name is the request param
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId")Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email
                              ){
        studentService.updateStudent(studentId,name,email);
    }
}
