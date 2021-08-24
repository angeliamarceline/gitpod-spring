package id.example.javaSpring.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchemaDto<T> {
    private ErrorSchema error_schema;
    private T output_schema;
}
