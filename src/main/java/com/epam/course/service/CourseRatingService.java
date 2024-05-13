package com.epam.course.service;

import com.epam.course.domain.Course;
import com.epam.course.domain.CourseRating;
import com.epam.course.repository.CourseRatingRepository;
import com.epam.course.repository.CourseRepository;
import com.epam.course.web.RatingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class CourseRatingService {
    private final CourseRatingRepository courseRatingRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseRatingService(CourseRatingRepository courseRatingRepository, CourseRepository courseRepository) {
        this.courseRatingRepository = courseRatingRepository;
        this.courseRepository = courseRepository;
    }

    public void createRating(Long courseId, RatingDto ratingDto) {
        var course = getCourse(courseId);
        var courseRating = new CourseRating(course, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
        courseRatingRepository.save(courseRating);
        log.info("Course rating for courseId={}, customerId={} was created with the score={} and comment='{}'",
                courseId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
    }

    private Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course does not exists: " + courseId));
    }
}