package demoOjt.dto.response;

import lombok.Data;

@Data
public class StudentDto {
    private int studentId;
    private String studentName;
    private int studentAge;
    private boolean studentStatus;
}
