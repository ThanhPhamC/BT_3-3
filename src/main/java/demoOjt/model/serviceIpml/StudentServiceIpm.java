package demoOjt.model.serviceIpml;

import demoOjt.dto.request.StudentModel;
import demoOjt.model.entity.Student;
import demoOjt.model.repository.StudentRepository;
import demoOjt.model.sevice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceIpm implements StudentService<Student,Integer> {
    @Autowired
    StudentRepository studentRepo;
    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }
    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepo.save(student);
    }
    @Override
    public void delete(Integer id) {
      studentRepo.deleteById(id);
    }
    @Override
    public Student findById(Integer id) {
        return studentRepo.findById(id).get();
    }
    @Override
    public Page<Student> findByName(String name, Pageable pageable) {
        return studentRepo.findByStudentNameContaining(name,pageable);
    }
}
