package org.example.sewingFactory.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private TaskType type;
    private LocalDate deadline;
    private Boolean complete;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
