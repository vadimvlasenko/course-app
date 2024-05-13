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

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseRatingServiceTest {
    private static final long COURSE_ID = 1L;
    private static final long CUSTOMER_ID = 2L;
    private static final RatingDto RATING_DTO = new RatingDto(CUSTOMER_ID, 5, "Excellent");

    @Mock
    private CourseRatingRepository courseRatingRepositoryMock;
    @Mock
    private CourseRepository courseRepositoryMock;
    @InjectMocks
    private CourseRatingService courseRatingService;

    @Test
    void shouldCreateRatingSuccessfully() {
        // given
        var coursePackage = new CoursePackage("QWER", "course package");
        var course = new Course("name", "description", 100, "3H", coursePackage, Difficulty.MEDIUM);
        when(courseRepositoryMock.findById(COURSE_ID)).thenReturn(Optional.of(course));

        var expected = new CourseRating(course, CUSTOMER_ID, 5, "Excellent");

        // when
        courseRatingService.createRating(COURSE_ID, RATING_DTO);

        // then
        verify(courseRatingRepositoryMock).save(expected);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenCreateRating() {
        // given, when, then
        assertThrows(NoSuchElementException.class, () -> courseRatingService.createRating(COURSE_ID, RATING_DTO));
    }

    @Test
    void shouldGetAllCourseRatingsSuccessfully() {
        // given
        var coursePackage = new CoursePackage("QWER", "course package");
        var course = new Course("name", "description", 100, "3H", coursePackage, Difficulty.MEDIUM);
        when(courseRepositoryMock.findById(COURSE_ID)).thenReturn(Optional.of(course));
        when(courseRatingRepositoryMock.findByCourseId(COURSE_ID)).thenReturn(List.of(new CourseRating(course, CUSTOMER_ID, 5, "Excellent")));
        var expected = List.of(new RatingDto(CUSTOMER_ID, 5, "Excellent"));

        // when
        var actual = courseRatingService.getAllCourseRatings(COURSE_ID);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenGetAllCourseRatings() {
        // given, when, then
        assertThrows(NoSuchElementException.class, () -> courseRatingService.getAllCourseRatings(COURSE_ID));
    }

    @Test
    void shouldGetCourseRatingAverageSuccessfully() {
        // given
        var coursePackage = new CoursePackage("QWER", "course package");
        var course = new Course("name", "description", 100, "3H", coursePackage, Difficulty.MEDIUM);
        when(courseRepositoryMock.findById(COURSE_ID)).thenReturn(Optional.of(course));
        when(courseRatingRepositoryMock.findByCourseId(COURSE_ID))
                .thenReturn(List.of(new CourseRating(course, CUSTOMER_ID, 5, "Excellent"),
                        new CourseRating(course, 10L, 4, "Good Course")));
        var expected = Map.of("average", 4.5d);

        // when
        var actual = courseRatingService.getCourseRatingAverage(COURSE_ID);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenGetAllCourseRatingAverage() {
        // given, when, then
        assertThrows(NoSuchElementException.class, () -> courseRatingService.getCourseRatingAverage(COURSE_ID));
    }
}