package com.stefanpascu.pao.service;

import com.DatabaseConnection;
import com.stefanpascu.pao.model.*;
import com.stefanpascu.pao.model.enums.Gender;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.stefanpascu.pao.service.contants.Constants.*;

public class ClassbookService {

    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static ClassbookService instance = null;

    private ClassbookService() { }

    public static ClassbookService getInstance() {
        if (instance == null) {
            instance = new ClassbookService();
        }

        return instance;
    }

    private final StudentService studentService = StudentService.getInstance();
    private final GradeService gradeService = GradeService.getInstance();
    private final AbsenceService absenceService = AbsenceService.getInstance();
    private final ClassroomService classroomService = ClassroomService.getInstance();
    private final SubjectService subjectsService = SubjectService.getInstance();

    private List<Classroom> classrooms;
    private List<Student> students;
    private List<Grade> grades;
    private List<Absence> absences;
    private List<Subject> subjects;

    public void saveAudit(String text) throws IOException {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        String sql = "INSERT INTO log (action_name, time_stamp)\n" +
                "VALUES (?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(1, text);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(2, strDate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("log table was updated.");
        }
    }

    public void loadData() throws SQLException, ParseException {
        classrooms = classroomService.getClassrooms();
        try {
            students = studentService.getStudents();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }
        grades = gradeService.getGrades();
        absences = absenceService.getAbsences();
        subjects = subjectsService.getSubjects();

        System.out.println(classrooms);
        System.out.println(students);
        System.out.println(grades);
        System.out.println(absences);
        System.out.println(subjects);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printClassrooms() throws IOException, SQLException {
        List<Classroom> classrooms = classroomService.getClassrooms();
        System.out.println(classrooms);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void printSubjects() throws SQLException {
        List<Subject> subjects = subjectsService.getSubjects();
        System.out.println(subjects);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStudents() throws ParseException, SQLException {
        List<Student> students = studentService.getStudents();
        System.out.println(students);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getNoOfAbsences() throws SQLException, ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Student ID: ");
        String studentId = in.nextLine();

        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }

        }

        List<Absence> studentAbsences = absenceService.getAbsences(studentId);
        int no = 0;
        for (Absence a : studentAbsences) {
            if (a.getStudentId().equals(studentId)) {
                no += a.getValue();
            }
        }
        System.out.println("The student with the given ID currently has " + no + " absences");

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentsGradesForSubject() throws ParseException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Student ID: ");
        String studentId = in.nextLine();

        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }

        }

        System.out.println("Subject ID: ");
        String subjectId = in.nextLine();

        validId = false;
        while (!validId) {
            if (subjectsService.subjectExists(subjectId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid subject ID");
                subjectId = in.nextLine();
            }
        }

        List<Grade> grades = gradeService.getGrades(studentId, subjectId);
        if(grades.isEmpty()) {
            System.out.println("This student has no current grades for this subject.");
        } else {
            System.out.println(grades);
        }

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentsAbsencesForSubject() throws ParseException, SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Student ID: ");
        String studentId = in.nextLine();

        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }
        }

        System.out.println("Subject ID: ");
        String subjectId = in.nextLine();

        validId = false;
        while (!validId) {
            if (subjectsService.subjectExists(subjectId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid subject ID");
                subjectId = in.nextLine();
            }
        }
        List<Absence> absences = absenceService.getAbsences(studentId, subjectId);
        if(absences.isEmpty()) {
            System.out.println("This student has no current absences at this subject.");
        } else {
            System.out.println(absences);
        }

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("First name: ");
        String fName = in.nextLine();
        System.out.println("Last name: ");
        String lName = in.nextLine();
        System.out.println("Gender (MALE, FEMALE or OTHER): ");
        Gender gender = Gender.get(in.nextLine());
        System.out.println("Birth date ('yyyy-MM-dd' format): ");
        Date birthDate = DATE_FORMAT.parse(in.nextLine());
        System.out.println("Classroom ID: ");
        String classroomId = in.nextLine();

        boolean validId = false;
        while (!validId) {
            if (classroomService.classroomExists(classroomId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid classroom ID");
                classroomId = in.nextLine();
            }
        }

        Student student = new Student(fName, lName, gender, birthDate, classroomId);
        studentService.saveStudent(student);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void addClassroom() throws IOException, SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println("Classroom name: ");
        String classroomName = in.nextLine();

        Classroom classroom = new Classroom(classroomName);

        classroomService.saveClassroom(classroom);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void addGrade() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Student ID: ");
        String studentId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }
        }

        System.out.println("Subject ID: ");
        String subjectId = in.nextLine();
        validId = false;
        while (!validId) {
            if (subjectsService.subjectExists(subjectId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid subject ID");
                subjectId = in.nextLine();
            }
        }

        System.out.println("Grade value: ");
        int value = Integer.parseInt(in.nextLine());
        validId = false;
        while (!validId) {
            if (value >= 1 && value <= 10) {
                validId = true;
            } else {
                System.out.println("Please enter a valid grade value.");
                value = Integer.parseInt(in.nextLine());
            }
        }

        System.out.println("Date ('yyyy-MM-dd' format): ");
        Date date = DATE_FORMAT.parse(in.nextLine());

        Grade grade = new Grade(value, date, subjectId, studentId);
        gradeService.saveGrade(grade);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void addAbsence() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Student ID: ");
        String studentId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }
        }

        System.out.println("Subject ID: ");
        String subjectId = in.nextLine();
        validId = false;
        while (!validId) {
            if (subjectsService.subjectExists(subjectId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid subject ID");
                subjectId = in.nextLine();
            }
        }

        System.out.println("Absence value: ");
        int value = Integer.parseInt(in.nextLine());

        System.out.println("Date ('yyyy-MM-dd' format): ");
        Date date = DATE_FORMAT.parse(in.nextLine());

        Absence absence = new Absence(value, date, subjectId, studentId);
        absenceService.saveAbsence(absence);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void updateClassroomName() throws IOException, SQLException, ParseException {
        Scanner in = new Scanner(System.in);

        System.out.println("Classroom ID: ");
        String classroomId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (classroomService.classroomExists(classroomId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid classroom ID");
                classroomId = in.nextLine();
            }
        }

        System.out.println("New classroom name: ");
        String newName = in.nextLine();

        classroomService.updateName(classroomId, newName);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void updateSudentsClassroomId() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Student ID: ");
        String studentId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }
        }

        System.out.println("New classroom ID: ");
        String newClassroomId = in.nextLine();

        studentService.updateClassroomId(studentId, newClassroomId);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void updateGradeValue() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Grade ID: ");
        String gradeId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (gradeService.gradeExists(gradeId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid grade ID");
                gradeId = in.nextLine();
            }
        }

        System.out.println("New grade value: ");
        int newValue = Integer.parseInt(in.nextLine());
        validId = false;
        while (!validId) {
            if (newValue >= 1 && newValue <= 10) {
                validId = true;
            } else {
                System.out.println("Please enter a valid grade value.");
                newValue = Integer.parseInt(in.nextLine());
            }
        }
        gradeService.updateGradeValue(gradeId, newValue);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void updateAbsenceValue() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Absence ID: ");
        String absenceId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (absenceService.absenceExists(absenceId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid absence ID");
                absenceId = in.nextLine();
            }
        }

        System.out.println("New absence value: ");
        int newValue = Integer.parseInt(in.nextLine());

        absenceService.updateAbsenceValue(absenceId, newValue);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void deleteAbsence() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Absence ID: ");
        String absenceId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (absenceService.absenceExists(absenceId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid absence ID");
                absenceId = in.nextLine();
            }
        }

        absenceService.deleteAbsence(absenceId);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void deleteGrade() throws ParseException, IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.println("Grade ID: ");
        String gradeId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (gradeService.gradeExists(gradeId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid grade ID");
                gradeId = in.nextLine();
            }
        }

        gradeService.deleteGrade(gradeId);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void deleteClassroom() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("Classroom ID: ");
        String classroomId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (classroomService.classroomExists(classroomId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid classroom ID");
                classroomId = in.nextLine();
            }
        }

        classroomService.deleteClassroom(classroomId);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void deleteStudent() throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("Student ID: ");
        String studentId = in.nextLine();
        boolean validId = false;
        while (!validId) {
            if (studentService.studentExists(studentId)) {
                validId = true;
            } else {
                System.out.println("Please enter a valid student ID");
                studentId = in.nextLine();
            }
        }

        studentService.deleteStudent(studentId);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }
}
