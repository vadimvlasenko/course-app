CREATE TABLE course_package(
    code CHAR(2) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_package_code CHAR(2) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    price VARCHAR(10) not null,
    duration VARCHAR(32) NOT NULL,
    difficulty VARCHAR(16) NOT NULL
);

ALTER TABLE course ADD CONSTRAINT fk_course_package_code FOREIGN KEY (course_package_code) REFERENCES course_package(code);

INSERT INTO course_package (code, name) VALUES
('JV', 'Java'),
('PY', 'Python'),
('AN', 'Angular');