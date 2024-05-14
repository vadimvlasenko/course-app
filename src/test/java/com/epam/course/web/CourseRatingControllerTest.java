package com.epam.course.web;

import com.epam.course.service.CourseRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CourseRatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseRatingService courseRatingService;

    @InjectMocks
    private CourseRatingController courseRatingController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseRatingController).build();
    }

    // Example test method
    @Test
    void testCreateCourseRating() throws Exception {
        // Setup mock behavior
        // Perform request
        // Assert results
    }

    // Add more test methods here
}
