package id.example.javaSpring.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phone_number;

    @Column()
    private boolean isDeleted = false;

    public Person(String full_name, String address, String phone_number) {
        this.full_name = full_name;
        this.address = address;
        this.phone_number = phone_number;
    }
}
