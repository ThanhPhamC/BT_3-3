package demoOjt.controller;

import demoOjt.dto.request.StudentModel;
import demoOjt.dto.request.StudentUpdate;
import demoOjt.model.entity.Student;
import demoOjt.model.sevice.StudentService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity<Map<String,Object>> getAllStudent(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5") int size,
                @RequestParam String direction) {
        Sort.Order order;
        if (direction.equals("asc")){
            order = new Sort.Order(Sort.Direction.ASC,"studentName");
        }else {
            order = new Sort.Order(Sort.Direction.DESC,"studentName");
        }
        Pageable pageable= PageRequest.of(page,size,Sort.by(order));
        Page<Student> students= studentService.getAll(pageable);
        Map<String, Object> data= new HashMap<>();
        data.put("students",students.getContent());
        data.put("total",students.getSize());
        data.put("totalItems", students.getTotalElements());
        data.put("totalPages",students.getTotalPages());
        return new  ResponseEntity<>(data,HttpStatus.OK);
    }

        @PostMapping("/newStudent")
    public ResponseEntity<?> creatNewStudent(@RequestBody Student student) {
        Student st = (Student) studentService.saveOrUpdate(student);
        if (st != null) {
            return ResponseEntity.ok(st);
        } else {
            return ResponseEntity.badRequest().body("creat new error");
        }
    }
    @PutMapping ("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){
        Student st= (Student) studentService.saveOrUpdate(student);
        if (st != null) {
            return ResponseEntity.ok(st);
        } else {
            return ResponseEntity.badRequest().body("update error");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteStudent( int studentId){
        try {
            studentService.delete(studentId);
            return ResponseEntity.ok("delete successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("delete error");
        }
    }
        @GetMapping("/searchByName")
    public ResponseEntity<Map<String,Object>> searchByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam String studentName){
       Pageable pageable= PageRequest.of(page,size);
       Page<Student> students=studentService.findByName(studentName,pageable);
      Map<String,Object> data= new HashMap<>();
        data.put("students",students.getContent());
        data.put("total",students.getSize());
        data.put("totalItems", students.getTotalElements());
        data.put("totalPages",students.getTotalPages());
        return new  ResponseEntity<>(data,HttpStatus.OK);
    }

}

