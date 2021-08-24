package id.example.javaSpring.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;

@Entity(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;

    // A method that returns firstName and Lastname when an object of the class is logged
    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName;
    }
}