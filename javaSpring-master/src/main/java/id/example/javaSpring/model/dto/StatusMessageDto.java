package id.example.javaSpring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusMessageDto<T> {
    private String status;
    private String message;
    private T data;
}
