package com.epam.course.service;

import com.epam.course.domain.Course;
import com.epam.course.domain.CourseRating;
import com.epam.course.repository.CourseRatingRepository;
import com.epam.course.repository.CourseRepository;
import com.epam.course.web.RatingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public void createRating(long courseId, RatingDto ratingDto) {
        var course = validateCourse(courseId);
        var courseRating = new CourseRating(course, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
        courseRatingRepository.save(courseRating);
        log.info("Course rating for courseId={}, customerId={} was created with the score={} and comment='{}'",
                courseId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());
    }

    @Transactional
    public void createMultipleRatings(Long courseId, Integer score, Long[] customers, String comment) {
        var course = validateCourse(courseId);
        for (var customerId : customers) {
            var courseRating = new CourseRating(course, customerId, score, comment);
            courseRatingRepository.save(courseRating);
            log.info("Course rating for courseId={}, customerId={} was created with the score={} and comment='{}'",
                    courseId, customerId, score, comment);
        }
    }

    public List<RatingDto> getAllCourseRatings(long courseId) {
        validateCourse(courseId);
        return courseRatingRepository.findByCourseId(courseId)
                .stream()
                .map(RatingDto::new)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getCourseRatingAverage(long courseId) {
        validateCourse(courseId);
        var courseRatingAverage = courseRatingRepository.findByCourseId(courseId)
                .stream()
                .mapToInt(CourseRating::getScore)
                .average()
                .orElseThrow(() -> new NoSuchElementException("Course has no Ratings"));
        return Map.of("average", courseRatingAverage);
    }

    private Course validateCourse(long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course does not exists: " + courseId));
    }
}