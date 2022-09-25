package me.wgoddard.PolyannaBackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {

    private final StudentRepository repo;

    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public void addNewStudent(Student student) {
        if (repo.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        repo.save(student);
    }
}
