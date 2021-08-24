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
@Table(name = "village")
public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer villageId;

    @Column(length = 10, nullable = false, unique = true)
    private String villageCode;

    @Column(length = 50, nullable = false)
    private String villageName;
    
    @Column()
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public Village(String villageCode, String villageName) {
        this.villageCode = villageCode;
        this.villageName = villageName;
    }
}
