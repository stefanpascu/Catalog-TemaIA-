package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.*;
import com.stefanpascu.pao.model.enums.Gender;
import com.stefanpascu.pao.model.enums.SubjectName;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.stefanpascu.pao.service.contants.Constants.*;

public class ClassbookService {

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

    public void saveAudit(String text) throws IOException {
        FileWriter fw = new FileWriter(LOG_FILE, true);
        fw.write("\n");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        fw.write(recordToLine(text, strDate));
        fw.close();
    }

    public void loadData() {
        classrooms = classroomService.getClassrooms();
        students = studentService.getStudents();
        grades = gradeService.getGrades();
        absences = absenceService.getAbsences();

        System.out.println(classrooms);
        System.out.println(students);
        System.out.println(grades);
        System.out.println(absences);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printClassrooms() throws ParseException, IOException {
        List<Classroom> classrooms = classroomService.getClassrooms();
        System.out.println(classrooms);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void printSubjects() throws ParseException {
        List<Subject> subjects = subjectsService.getSubjects();
        System.out.println(subjects);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStudents() throws ParseException {
        List<Student> students = studentService.getStudents();
        System.out.println(students);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getNoOfAbsences() {
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

    public void showStudentsGradesForSubject() throws ParseException {
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
                studentId = in.nextLine();
            }
        }
        List<Grade> grades = gradeService.getGrades(studentId, subjectId);
        System.out.println(grades);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStudentsAbsencesForSubject() throws ParseException {
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
                studentId = in.nextLine();
            }
        }
        List<Absence> absences = absenceService.getAbsences(studentId, subjectId);
        System.out.println(absences);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        try {
            saveAudit(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent() throws ParseException, IOException {
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

        System.out.println("Student was added");
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void addGrade() throws ParseException, IOException {
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
                studentId = in.nextLine();
            }
        }

        System.out.println("Grade value: ");
        int value = Integer.parseInt(in.nextLine());

        System.out.println("Date ('yyyy-MM-dd' format): ");
        Date date = DATE_FORMAT.parse(in.nextLine());


        Grade grade = new Grade(value, date, subjectId, studentId);
        gradeService.saveGrade(grade);
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    public void addAbsence() throws ParseException, IOException {
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
                studentId = in.nextLine();
            }
        }

        System.out.println("Number of absences: ");
        int value = Integer.parseInt(in.nextLine());

        System.out.println("Date ('yyyy-MM-dd' format): ");
        Date date = DATE_FORMAT.parse(in.nextLine());


        Absence absence = new Absence(value, date, subjectId, studentId);
        absenceService.saveAbsence(absence);

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        saveAudit(name);
    }

    private String recordToLine(String text, String date) {
        return String.format("%s ---- %s",
                text, date);
    }

}
