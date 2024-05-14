CREATE TABLE course_rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_id BIGINT,
    customer_id BIGINT,
    score INT,
    comment VARCHAR(100)
);

ALTER TABLE course_rating ADD CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course(id);
ALTER TABLE course_rating ADD UNIQUE unique_course_rating_constraint (course_id, customer_id);