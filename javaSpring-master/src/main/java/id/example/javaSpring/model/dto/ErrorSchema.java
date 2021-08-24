package id.example.javaSpring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorSchema {
    private String error_code;
    private ErrorMessage error_message;
}
