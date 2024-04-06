DROP TABLE IF EXISTS course_attended CASCADE;
DROP TABLE IF EXISTS instructor CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS student CASCADE;

-- Create Instructor Table
CREATE TABLE instructor (
                            instructorid BIGSERIAL PRIMARY KEY,
                            instructor_name VARCHAR(255),
                            salary INTEGER,
                            office_location VARCHAR(255)
);

-- Create Course Table
CREATE TABLE course (
                        courseid BIGSERIAL PRIMARY KEY,
                        course_name VARCHAR(255),
                        credits INTEGER,
                        capacity INTEGER,
                        department VARCHAR(255),
                        taught_by_instructorid BIGINT,
                        FOREIGN KEY (taught_by_instructorid) REFERENCES Instructor(instructorid)
);

-- Create Student Table
CREATE TABLE student (
                         studentid BIGSERIAL PRIMARY KEY,
                         student_name VARCHAR(255),
                         date_of_birth DATE,
                         contact_info VARCHAR(255),
                         gpa FLOAT
);

-- Create Student_Course Join Table for Many-to-Many relationship
CREATE TABLE course_attended (
                                attended_by_studentid BIGINT,
                                attends_courseid BIGINT,
                                PRIMARY KEY (attended_by_studentid, attends_courseid),
                                FOREIGN KEY (attended_by_studentid) REFERENCES Student(studentid),
                                FOREIGN KEY (attends_courseid) REFERENCES Course(courseid)
);
