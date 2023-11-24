package vn.ptit.b19dccn576.BE.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @Column(name = "applied_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date appliedDate;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

    @Column(name = "last_modified_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate = new Date();

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "category-type")
    private Type type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonManagedReference(value = "category-item")
    private List<Item> items;
}
