package com.epam.course.service;

import com.epam.course.domain.Course;
import com.epam.course.domain.CoursePackage;
import com.epam.course.domain.CourseRating;
import com.epam.course.domain.Difficulty;
import com.epam.course.repository.CourseRatingRepository;
import com.epam.course.repository.CourseRepository;
import com.epam.course.web.RatingDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseRatingServiceTest {
    private static final long COURSE_ID = 1L;
    private static final long CUSTOMER_ID = 2L;

    @Mock
    private CourseRatingRepository courseRatingRepositoryMock;
    @Mock
    private CourseRepository courseRepositoryMock;
    @InjectMocks
    private CourseRatingService courseRatingService;

    @Test
    void shouldCreateRatingSuccessfully() {
        // given
        var ratingDto = new RatingDto(CUSTOMER_ID, 5, "Excellent");
        var coursePackage = new CoursePackage("QWER", "course package");
        var course = new Course("name", "description", 100, "3H", coursePackage, Difficulty.MEDIUM);
        when(courseRepositoryMock.findById(COURSE_ID)).thenReturn(Optional.of(course));

        var expected = new CourseRating(course, CUSTOMER_ID, 5, "Excellent");

        // when
        courseRatingService.createRating(COURSE_ID, ratingDto);

        // then
        verify(courseRatingRepositoryMock).save(expected);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenCreateRating() {
        // given
        var ratingDto = new RatingDto(CUSTOMER_ID, 5, "Excellent");

        // when, then
        assertThrows(NoSuchElementException.class, () -> courseRatingService.createRating(COURSE_ID, ratingDto));
    }
}