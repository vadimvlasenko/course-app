package com.epam.course.web;

import com.epam.course.domain.CourseRating;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class RatingDto {
    @NotNull
    private Long customerId;

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    public RatingDto(@NotNull CourseRating courseRating) {
        this(courseRating.getCustomerId(), courseRating.getScore(), courseRating.getComment());
    }
}
