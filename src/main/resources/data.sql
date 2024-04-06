-- Insert Instructors
INSERT INTO instructor (instructorid, instructor_name, office_location, salary) VALUES
(1, 'John Doe', 'Room 101', 70000),
(2, 'Jane Smith', 'Room 102', 75000),
(3, 'Emily Johnson', 'Room 103', 72000),
(4, 'Michael Brown', 'Room 104', 68000),
(5, 'Jessica White', 'Room 105', 71000),
(6, 'William Davis', 'Room 106', 69000);

-- Insert Courses
INSERT INTO course (courseid, course_name, credits, capacity, department, taught_by_instructorid) VALUES
(1, 'Math 101', 4, 30, 'Math', 1),
(2, 'Physics 101', 4, 30, 'Physics', 2),
(3, 'Chemistry 101', 4, 30, 'Chemistry', 3),
(4, 'Biology 101', 4, 30, 'Biology', 4),
(5, 'English Literature 101', 4, 30, 'English', 5),
(6, 'Computer Science 101', 4, 30, 'CS', 6);

-- Insert Students
INSERT INTO student (studentid, student_name, date_of_birth, contact_info, gpa) VALUES
(1, 'Alice Johnson', '2000-01-01', 'alice@example.com', 3.5),
(2, 'Bob Williams', '2000-02-01', 'bob@example.com', 3.6),
(3, 'Carol Davis', '1999-03-15', 'carol@example.com', 3.7),
(4, 'David Miller', '1998-04-20', 'david@example.com', 3.4),
(5, 'Eve Brown', '2001-05-30', 'eve@example.com', 3.8),
(6, 'Frank Wilson', '2000-06-22', 'frank@example.com', 3.9);

-- Insert Course Attended by Students
INSERT INTO course_attended (attended_by_studentid, attends_courseid) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 1);
