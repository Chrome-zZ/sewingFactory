package org.example.sewingFactory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Table(name = "time_periods")
public class TimePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "begin_time")
    private LocalTime beginTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "shift")
    private Shift shift;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "timePeriod")
    private List<Schedule> schedules;
}
