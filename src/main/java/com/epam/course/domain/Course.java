package com.epam.course.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column(length = 2000)
    private String description;

    @Column
    private Integer price;

    @Column
    private String duration;

    @ManyToOne
    private CoursePackage coursePackage;

    @Column
    @Enumerated
    private Difficulty difficulty;

    public Course(String name,
                  String description,
                  Integer price,
                  String duration,
                  CoursePackage coursePackage,
                  Difficulty difficulty) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.coursePackage = coursePackage;
        this.difficulty = difficulty;
    }
}