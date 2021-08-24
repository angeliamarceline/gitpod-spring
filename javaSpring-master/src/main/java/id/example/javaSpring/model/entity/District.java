package id.example.javaSpring.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtId;

    @Column(length = 10, nullable = false, unique = true)
    private String districtCode;

    @Column(length = 50, nullable = false)
    private String districtName;
    
    @Column()
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public District(String districtCode, String districtName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
    }
}