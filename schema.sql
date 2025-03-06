CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       email TEXT UNIQUE NOT NULL,
                       password TEXT NOT NULL,
                       roles TEXT NOT NULL CHECK (roles IN ('student', 'teacher', 'admin')),
                       registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       update_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE students (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          name TEXT NOT NULL,
                          grade SMALLINT NOT NULL CHECK (grade BETWEEN 1 AND 12),
                          user_id UUID UNIQUE NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE teachers (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          name TEXT NOT NULL,
                          subject TEXT NOT NULL,
                          user_id UUID UNIQUE NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE classes (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         class_name TEXT NOT NULL,
                         teacher_id UUID NOT NULL REFERENCES teachers(id) ON DELETE CASCADE,
                         registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         update_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE class_students (
                                class_id UUID NOT NULL REFERENCES classes(id) ON DELETE CASCADE,
                                student_id UUID NOT NULL REFERENCES students(id) ON DELETE CASCADE,
                                PRIMARY KEY (class_id, student_id)
);

CREATE TABLE lessons (
                         id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         lesson_name TEXT NOT NULL,
                         lesson_video TEXT,
                         description TEXT,
                         class_id UUID NOT NULL REFERENCES classes(id) ON DELETE CASCADE,
                         registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         update_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE comments (
                          id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          lesson_id UUID NOT NULL REFERENCES lessons(id) ON DELETE CASCADE,
                          comment_text TEXT NOT NULL,
                          registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.update_date = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_users
    BEFORE UPDATE ON users
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER trigger_update_classes
    BEFORE UPDATE ON classes
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER trigger_update_lessons
    BEFORE UPDATE ON lessons
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
