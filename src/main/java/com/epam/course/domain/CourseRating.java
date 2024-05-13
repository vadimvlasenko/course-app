package com.epam.course.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CourseRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name="customer_id")
    private Long customerId;

    @Setter
    @Column(nullable = false)
    private Integer score;

    @Setter
    @Column
    private String comment;

    public CourseRating(Course course, Long customerId, Integer score, String comment) {
        this.course = course;
        this.customerId = customerId;
        this.score = score;
        this.comment = comment;
    }
}
