package com.stefanpascu.pao;

import com.stefanpascu.pao.model.*;
import com.stefanpascu.pao.model.enums.Gender;
import com.stefanpascu.pao.model.enums.SubjectName;
import com.stefanpascu.pao.service.ClassbookService2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main2 {

//    public static Date StringToDate(String s){
//
//        Date result = null;
//        try{
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//            result  = dateFormat.parse(s);
//        }
//
//        catch(ParseException e){
//            e.printStackTrace();
//
//        }
//        return result ;
//    }
//
//    public static Gender StringToGender(String s){
//
//        Gender result = null;
//        result = Gender.valueOf(s);
//        return result ;
//    }
//
//    public static SubjectName StringToSubjectName(String s){
//
//        SubjectName result = null;
//        result = SubjectName.valueOf(s);
//        return result ;
//    }
//
//    public static void main(String[] args) throws ParseException {
//
//        // Using Scanner for Getting Input from User
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("You are about to create your own Classroom.\n" +
//                "Write a name for your Classroom: ");
//        String classbookName = in.nextLine();
//
//        System.out.println("Great job! Now you have to 'hire' a new teacher.\n" +
//                "Write the firstname, lastname, gender(MALE, FEMALE or OTHER), birthdate and hire date \n(using this format: 'dd.mm.yyyy') for the classbook your classroom is going to have: ");
//        String teacherfn1 = in.nextLine();
//        String teacherln1 = in.nextLine();
//        String stringteachergender1 = in.nextLine();
//        Gender teachergender1 = StringToGender(stringteachergender1);
//        //Date teaherbd1 = in.next();
//
//        String teacherbd1 = in.nextLine();
//        String teacherhd1 = in.nextLine();
//
//        Teacher teacher1 = new Teacher(teacherfn1,
//                teacherln1,
//                teachergender1,
//                StringToDate(teacherbd1),
//                StringToDate(teacherhd1));
//
//        System.out.println("Perfect! Now we need to name the subject's name the teacher is teaching." +
//                "\nName a subject(subject list: MATHEMATICS, " +
//                "    COMPUTER_SCIENCE, " +
//                "    GEOGRAPHY, " +
//                "    HISTORY, " +
//                "    SPORT, " +
//                "    ECONOMICS): ");
//        String stringSubjectName1 = in.nextLine();
//        SubjectName subjectName1 = StringToSubjectName(stringSubjectName1);
//
//        Map<Teacher, SubjectName> map1 = new HashMap<Teacher,SubjectName>();
//        map1.put(teacher1, subjectName1);
//
//        ClassbookService2 classbook = new ClassbookService2(classbookName, teacher1, subjectName1, map1);
//        while(true) {
//            System.out.println("Congratulations! You have now just created your classroom, together with a corresponding classbook." +
//                    "\n You are now able to use the following methods(by pressing their corresponding number) to modify the classbook:" +
//                    "\n1 - add a grade for a specific student" +
//                    "\n2 - add a new student" +
//                    "\n3 - remove an existing student" +
//                    "\n4 - show a student's grades" +
//                    "\n5 - show a student's grades for a specific subject" +
//                    "\n6 - add an absence for a specific student" +
//                    "\n7 - show a student's absences" +
//                    "\n8 - show the number of unmotivated absences a student has" +
//                    "\n9 - motivate a specific absence for a specific student" +
//                    "\n10 - add a new teacher" +
//                    "\n11 - add a new subject(can only be added if there is a teacher to teach it)" +
//                    "\n12 - show the final grade(arithmetic mean) for a specific subject for a specific student");
//
//            int caz = in.nextInt();
//            switch (caz) {
//                case 1:
//                    System.out.println("\nTo add a new grade, you will have to introduce it's value(a number between 1 and 10), \n" +
//                            "subject name, firstname and lastname of the teacher who set it, the student's firstname and lastname and the date when he recieved the grade:");
//                    int gradeValue = in.nextInt();
//
//                    String subjectName = in.nextLine();
//                    String teacherfn = in.nextLine();
//                    String teacherln = in.nextLine();
//
//                    String studentfn = in.nextLine();
//                    String studentln = in.nextLine();
//                    String stringGradeDate = in.nextLine();
//                    Date gradeDate = StringToDate(stringGradeDate);
//
//                    Student student1 = null;
//                    Set<Student> students = classbook.getAllStudents();
//                    for (Student i : students) {
//                        if (i.getFirstName() == studentfn && i.getLastName() == studentln) {
//                            student1 = i;
//                            break;
//                        }
//                    }
//
//                    Teacher teacher2 = null;
//                    Set<Teacher> teachers = classbook.getAllTeachers();
//                    for (Teacher i : teachers) {
//                        if (i.getFirstName() == teacherfn && i.getLastName() == teacherln) {
//                            teacher2 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject1 = null;
//                    Set<Subject> subjects = classbook.getAllSubjects();
//                    for (Subject i : subjects) {
//                        if (i.getName() == StringToSubjectName(subjectName) && i.getTeacherId() == teacher2) {
//                            subject1 = i;
//                            break;
//                        }
//                    }
//
//                    Grade grade1 = new Grade(gradeValue, gradeDate, subject1);
//                    classbook.addGrade(student1, grade1);
//                    break;
//
//                case 2:
//                    System.out.println("\nTo add a new student, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy').");
//                    String studentfn2 = in.nextLine();
//                    in.nextLine();
//                    String studentln2 = in.nextLine();
//                    String stringstudentgender = in.nextLine();
//                    Gender studentgender2 = StringToGender(stringstudentgender);
//                    String birthdate2 = in.nextLine();
//                    String enrollmentDate2 = in.nextLine();
//                    Student student2 = new Student(studentfn2, studentln2, studentgender2, StringToDate(birthdate2), StringToDate(enrollmentDate2));
//                    classbook.addStudent(student2);
//                    break;
//
//                case 3:
//                    System.out.println("\nTo remove an existing student, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy').");
//                    String studentfn3 = in.nextLine();
//                    String studentln3 = in.nextLine();
//                    String stringstudentgender3 = in.nextLine();
//                    Gender studentgender3 = StringToGender(stringstudentgender3);
//                    String birthdate3 = in.nextLine();
//                    String enrollmentDate3 = in.nextLine();
//
//                    Student student3 = null;
//                    Set<Student> students3 = classbook.getAllStudents();
//                    for (Student i : students3) {
//                        if (i.getFirstName() == studentfn3 && i.getLastName() == studentln3 && i.getGender() == studentgender3 && i.getBirthDate() == StringToDate(birthdate3) && i.getEnrollmentDate() == StringToDate(enrollmentDate3)) {
//                            student3 = i;
//                            break;
//                        }
//                    }
//
//                    classbook.removeStudent(student3);
//                    break;
//
//                case 4:
//                    System.out.println("\nTo display a student's grades, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy').");
//                    String studentfn4 = in.nextLine();
//                    String studentln4 = in.nextLine();
//                    String stringstudentgender4 = in.nextLine();
//                    Gender studentgender4 = StringToGender(stringstudentgender4);
//                    String birthdate4 = in.nextLine();
//                    String enrollmentDate4 = in.nextLine();
//
//                    Student student4 = null;
//                    Set<Student> students4 = classbook.getAllStudents();
//                    for (Student i : students4) {
//                        if (i.getFirstName() == studentfn4 && i.getLastName() == studentln4 && i.getGender() == studentgender4 && i.getBirthDate() == StringToDate(birthdate4) && i.getEnrollmentDate() == StringToDate(enrollmentDate4)) {
//                            student4 = i;
//                            break;
//                        }
//                    }
//
//                    System.out.println(classbook.getStudentGrades(student4));
//                    break;
//
//                case 5:
//                    System.out.println("\nTo display an existing student's grades for a specific subject, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy'), and the teacher's firstname, lastname" +
//                            "and the subject's name.");
//                    String studentfn5 = in.nextLine();
//                    String studentln5 = in.nextLine();
//                    String stringstudentgender5 = in.nextLine();
//                    Gender studentgender5 = StringToGender(stringstudentgender5);
//                    String birthdate5 = in.nextLine();
//                    String enrollmentDate5 = in.nextLine();
//
//                    Student student5 = null;
//                    Set<Student> students5 = classbook.getAllStudents();
//                    for (Student i : students5) {
//                        if (i.getFirstName() == studentfn5 && i.getLastName() == studentln5 && i.getGender() == studentgender5 && i.getBirthDate() == StringToDate(birthdate5) && i.getEnrollmentDate() == StringToDate(enrollmentDate5)) {
//                            student5 = i;
//                            break;
//                        }
//                    }
//
//                    String teacherfn5 = in.nextLine();
//                    String teacherln5 = in.nextLine();
//
//                    String subjectName5 = in.nextLine();
//
//                    Teacher teacher5 = null;
//                    Set<Teacher> teachers5 = classbook.getAllTeachers();
//                    for (Teacher i : teachers5) {
//                        if (i.getFirstName() == teacherfn5 && i.getLastName() == teacherln5) {
//                            teacher5 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject5 = null;
//                    Set<Subject> subjects5 = classbook.getAllSubjects();
//                    for (Subject i : subjects5) {
//                        if (i.getName() == StringToSubjectName(subjectName5) && i.getTeacherId() == teacher5) {
//                            subject5 = i;
//                            break;
//                        }
//                    }
//
//                    System.out.println(classbook.getStudentGradesForSubject(student5, subject5));
//                    break;
//
//                case 6:
//                    System.out.println("\nTo add an absence for a specific subject, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy'),the date on which the student was absent," +
//                            " the firstname and lastname of the teacher who\n sets it and the subject's name.");
//
//                    String studentfn6 = in.nextLine();
//                    String studentln6 = in.nextLine();
//                    String stringstudentgender6 = in.nextLine();
//                    Gender studentgender6 = StringToGender(stringstudentgender6);
//                    String birthdate6 = in.nextLine();
//                    String enrollmentDate6 = in.nextLine();
//
//                    Student student6 = null;
//                    Set<Student> students6 = classbook.getAllStudents();
//                    for (Student i : students6) {
//                        if (i.getFirstName() == studentfn6 && i.getLastName() == studentln6 && i.getGender() == studentgender6 && i.getBirthDate() == StringToDate(birthdate6) && i.getEnrollmentDate() == StringToDate(enrollmentDate6)) {
//                            student6 = i;
//                            break;
//                        }
//                    }
//
//                    String absenceDate6 = in.nextLine();
//
//                    String teacherfn6 = in.nextLine();
//                    String teacherln6 = in.nextLine();
//
//                    String subjectName6 = in.nextLine();
//
//                    Teacher teacher6 = null;
//                    Set<Teacher> teachers6 = classbook.getAllTeachers();
//                    for (Teacher i : teachers6) {
//                        if (i.getFirstName() == teacherfn6 && i.getLastName() == teacherln6) {
//                            teacher6 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject6 = null;
//                    Set<Subject> subjects6 = classbook.getAllSubjects();
//                    for (Subject i : subjects6) {
//                        if (i.getName() == StringToSubjectName(subjectName6) && i.getTeacherId() == teacher6) {
//                            subject6 = i;
//                            break;
//                        }
//                    }
//
//                    Absence absence6 = new Absence(StringToDate(absenceDate6), subject6);
//
//                    classbook.addAbsence(student6, absence6);
//                    break;
//
//                case 7:
//                    System.out.println("\nTo display a student's absences, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy').");
//                    String studentfn7 = in.nextLine();
//                    String studentln7 = in.nextLine();
//                    String stringstudentgender7 = in.nextLine();
//                    Gender studentgender7 = StringToGender(stringstudentgender7);
//                    String birthdate7 = in.nextLine();
//                    String enrollmentDate7 = in.nextLine();
//
//                    Student student7 = null;
//                    Set<Student> students7 = classbook.getAllStudents();
//                    for (Student i : students7) {
//                        if (i.getFirstName() == studentfn7 && i.getLastName() == studentln7 && i.getGender() == studentgender7 && i.getBirthDate() == StringToDate(birthdate7) && i.getEnrollmentDate() == StringToDate(enrollmentDate7)) {
//                            student7 = i;
//                            break;
//                        }
//                    }
//
//                    System.out.println(classbook.getStudentAbsences(student7));
//                    break;
//
//                case 8:
//                    System.out.println("\nTo display a student's number of absences, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy').");
//                    String studentfn8 = in.nextLine();
//                    String studentln8 = in.nextLine();
//                    String stringstudentgender8 = in.nextLine();
//                    Gender studentgender8 = StringToGender(stringstudentgender8);
//                    String birthdate8 = in.nextLine();
//                    String enrollmentDate8 = in.nextLine();
//
//                    Student student8 = null;
//                    Set<Student> students8 = classbook.getAllStudents();
//                    for (Student i : students8) {
//                        if (i.getFirstName() == studentfn8 && i.getLastName() == studentln8 && i.getGender() == studentgender8 && i.getBirthDate() == StringToDate(birthdate8) && i.getEnrollmentDate() == StringToDate(enrollmentDate8)) {
//                            student8 = i;
//                            break;
//                        }
//                    }
//
//                    System.out.println(classbook.getNoOfUnmotivatedAbsences(student8));
//                    break;
//
//                case 9:
//                    System.out.println("\nTo motivate an absence for a student, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy'),the date on which the student was absent," +
//                            " the firstname and lastname of the teacher who\n sets it and the subject's name.");
//
//                    String studentfn9 = in.nextLine();
//                    String studentln9 = in.nextLine();
//                    String stringstudentgender9 = in.nextLine();
//                    Gender studentgender9 = StringToGender(stringstudentgender9);
//                    String birthdate9 = in.nextLine();
//                    String enrollmentDate9 = in.nextLine();
//
//                    Student student9 = null;
//                    Set<Student> students9 = classbook.getAllStudents();
//                    for (Student i : students9) {
//                        if (i.getFirstName() == studentfn9 && i.getLastName() == studentln9 && i.getGender() == studentgender9 && i.getBirthDate() == StringToDate(birthdate9) && i.getEnrollmentDate() == StringToDate(enrollmentDate9)) {
//                            student9 = i;
//                            break;
//                        }
//                    }
//
//                    String absenceDate9 = in.nextLine();
//
//                    String teacherfn9 = in.nextLine();
//                    String teacherln9 = in.nextLine();
//
//                    String subjectName9 = in.nextLine();
//
//                    Teacher teacher9 = null;
//                    Set<Teacher> teachers9 = classbook.getAllTeachers();
//                    for (Teacher i : teachers9) {
//                        if (i.getFirstName() == teacherfn9 && i.getLastName() == teacherln9) {
//                            teacher9 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject9 = null;
//                    Set<Subject> subjects9 = classbook.getAllSubjects();
//                    for (Subject i : subjects9) {
//                        if (i.getName() == StringToSubjectName(subjectName9) && i.getTeacherId() == teacher9) {
//                            subject9 = i;
//                            break;
//                        }
//                    }
//
//                    classbook.motivateStudentAbsence(student9, StringToDate(absenceDate9), subject9);
//                    break;
//
//                case 10:
//                    System.out.println("In order to add a new teacher, you have to write the firstname, lastname, gender(MALE, FEMALE or OTHER), birthdate \nand hire date (using this format: 'dd.mm.yyyy') of the new teacher: ");
//                    String teacherfn10 = in.nextLine();
//                    String teacherln10 = in.nextLine();
//                    String stringteachergender10 = in.nextLine();
//                    Gender teachergender10 = StringToGender(stringteachergender10);
//                    //Date teaherbd1 = in.next();
//
//                    String teacherbd10 = in.nextLine();
//                    String teacherhd10 = in.nextLine();
//
//                    Teacher teacher10 = new Teacher(teacherfn10,
//                            teacherln10,
//                            teachergender10,
//                            StringToDate(teacherbd10),
//                            StringToDate(teacherhd10));
//                    classbook.addTeacher(teacher10);
//                    break;
//
//                case 11:
//                    System.out.println("In order to add a new subject, you will have to write it's " +
//                            "\nname and the lastname and firstname of the teacher who is going to teach it.");
//                    String subjectName11 = in.nextLine();
//                    String teacherfn11 = in.nextLine();
//                    String teacherln11 = in.nextLine();
//
//                    Teacher teacher11 = null;
//                    Set<Teacher> teachers11 = classbook.getAllTeachers();
//                    for (Teacher i : teachers11) {
//                        if (i.getFirstName() == teacherfn11 && i.getLastName() == teacherln11) {
//                            teacher11 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject11 = new Subject(StringToSubjectName(subjectName11), teacher11);
//                    break;
//
//                case 12:
//                    System.out.println("\nTo display a student's final grade for a specific subject, you will have to introduce his/her firstname, lastname, " +
//                            "gender(FEMALE, MALE or OTHER), \nbirthdate and enrollment date(in the format: 'dd.mm.yyyy'),the date on which the student was absent," +
//                            " the firstname and lastname of the teacher who\n sets it and the subject's name.");
//
//                    String studentfn12 = in.nextLine();
//                    String studentln12 = in.nextLine();
//                    String stringstudentgender12 = in.nextLine();
//                    Gender studentgender12 = StringToGender(stringstudentgender12);
//                    String birthdate12 = in.nextLine();
//                    String enrollmentDate12 = in.nextLine();
//
//                    Student student12 = null;
//                    Set<Student> students12 = classbook.getAllStudents();
//                    for (Student i : students12) {
//                        if (i.getFirstName() == studentfn12 && i.getLastName() == studentln12 && i.getGender() == studentgender12 && i.getBirthDate() == StringToDate(birthdate12) && i.getEnrollmentDate() == StringToDate(enrollmentDate12)) {
//                            student12 = i;
//                            break;
//                        }
//                    }
//
//                    String absenceDate12 = in.nextLine();
//
//                    String teacherfn12 = in.nextLine();
//                    String teacherln12 = in.nextLine();
//
//                    String subjectName12 = in.nextLine();
//
//                    Teacher teacher12 = null;
//                    Set<Teacher> teachers12 = classbook.getAllTeachers();
//                    for (Teacher i : teachers12) {
//                        if (i.getFirstName() == teacherfn12 && i.getLastName() == teacherln12) {
//                            teacher12 = i;
//                            break;
//                        }
//                    }
//
//                    Subject subject12 = null;
//                    Set<Subject> subjects12 = classbook.getAllSubjects();
//                    for (Subject i : subjects12) {
//                        if (i.getName() == StringToSubjectName(subjectName12) && i.getTeacherId() == teacher12) {
//                            subject12 = i;
//                            break;
//                        }
//                    }
//
//                    System.out.println(classbook.getSubjectFinalGrade(student12, subject12));
//                    break;
//
//                default:
//                    System.out.println("The given number does not correspond to any action.");
//                    break;
//
//            }
//        }
//
//    }

}
