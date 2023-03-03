package demoOjt.dto.request;

import lombok.Data;

@Data
public class StudentUpdate extends StudentModel{
    private int studentId;
    private boolean studentStatus;
}
