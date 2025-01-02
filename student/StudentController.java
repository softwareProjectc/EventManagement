package com.software.eventmanagement.student;
import com.software.eventmanagement.cookies.CookieController;
import com.software.eventmanagement.entities.LoginRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    // tested successfully
    @PostMapping("/verifyStudent")
    public ResponseEntity<?> verifyUser(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (studentService.verifyStudent(request) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Invalid username or password\"}");
        CookieController.setStudentCookie(response, request.getUsername());
        System.out.println("login successful");
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Login successful\"}");
    }

    // tested successfully
    @PostMapping("/enrollInEvent/{eventId}/")
    public ResponseEntity<?> enrollInEvent(@PathVariable Long eventId, @CookieValue(value = "studentAuthenticationToken") String studentId) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        if (studentService.enrollInEvent(eventId, studentId))
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Enrolled successfully\"}");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Student not found\"}");
    }

    // tested successfully
    @PostMapping("/cancelEvent/{eventId}")
    public ResponseEntity<?> cancelEvent(@PathVariable Long eventId, @CookieValue(value = "studentAuthenticationToken") String studentId) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        if(studentService.cancelEnrollment(eventId, studentId))
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Cancelled successfully\"}");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Student or event not found\"}");
    }

    // tested successfully
    @PostMapping("/rate/{eventId}/{rating}")
    public ResponseEntity<?> rateEvent(@PathVariable Long eventId, @CookieValue(value = "studentAuthenticationToken") String studentId, @PathVariable short rating) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        if(studentService.rateEvent(eventId, studentId, rating))
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Rate successful\"}");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Student or event not found\"}");
    }

    // tested successfully
    @PostMapping("/rate/{eventId}/")
    public ResponseEntity<?> saveFeedback(@PathVariable Long eventId, @CookieValue(value = "studentAuthenticationToken") String studentId, @RequestBody String feedback) {
        studentId = CookieController.getUsernameFromCookie(studentId);
        if(studentService.saveFeedback(eventId, studentId, feedback))
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Feedback successful\"}");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Student or event not found\"}");
    }
}