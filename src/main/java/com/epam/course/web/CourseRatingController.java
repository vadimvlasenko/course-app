package com.epam.course.web;

import com.epam.course.service.CourseRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/courses/{courseId}/ratings")
public class CourseRatingController {
    private final CourseRatingService courseRatingService;

    @Autowired
    public CourseRatingController(CourseRatingService courseRatingService) {
        this.courseRatingService = courseRatingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourseRating(@PathVariable("courseId") Long courseId,
                                   @RequestBody @Validated RatingDto ratingDto) {
        courseRatingService.createRating(courseId, ratingDto);
    }

    @PostMapping("/{score}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMultipleCourseRating(@PathVariable("courseId") Long courseId,
                                           @PathVariable("score") Integer score,
                                           @RequestParam("customers") Long[] customers,
                                           @RequestParam("comment") String comment) {
        courseRatingService.createMultipleRatings(courseId, score, customers, comment);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RatingDto> getAllCourseRatings(@PathVariable("courseId") Long courseId) {
        return courseRatingService.getAllCourseRatings(courseId);
    }

    @GetMapping(path = "/average")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Double> getCourseRatingAverage(@PathVariable("courseId") Long courseId) {
        return courseRatingService.getCourseRatingAverage(courseId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    private String notFound(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
