package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.*;
import com.stefanpascu.pao.model.enums.SubjectName;

import java.util.*;

public class ClassbookService {

    private Map<Teacher, SubjectName> teacherTeachesSubject;
    private String classroom;
    private Map<Student, List<Grade>> grades;
    private Map<Student, List<Absence>> absences;
    private Set<Student> students;
    private Set<Teacher> teachers;
    private Set<Subject> subjects;

    public ClassbookService(String classroom, Teacher teacher, SubjectName subjectName, Map<Teacher, SubjectName> teacherTeachesSubject) {
        this.classroom = classroom;
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
        this.addTeacher(teacher);
        this.subjects = new HashSet<>();
        Subject subject = new Subject(subjectName, teacher);
        this.addSubject(subject);
        this.teacherTeachesSubject = teacherTeachesSubject;

        this.grades = new HashMap<>();
        students.forEach(student -> grades.put(student, new ArrayList<>()));

        this.absences = new HashMap<>();
        students.forEach(student -> absences.put(student, new ArrayList<>()));
    }

    public void addGrade(Student student, Grade grade) {
        grades.get(student).add(grade);
    }
    public void addStudent(Student student) {
        students.add(student);
        grades.put(student, new ArrayList<>());
        absences.put(student, new ArrayList<>());
    }
    public void addAbsence(Student student, Absence absence) {
        absences.get(student).add(absence);
    }
    public void removeStudent(Student student) {
        students.remove(student);
        grades.remove(student);
        absences.remove(student);
    }
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
    public List<Grade> getStudentGrades(Student student) {
        return grades.get(student);
    }
    public List<Absence> getStudentAbsences(Student student) {
        return absences.get(student);
    }
    public int getNoOfUnmotivatedAbsences(Student student) {
        List<Absence> studentAbsences = absences.get(student);
        int no = 0;
        for (Absence a : studentAbsences) {
            if (!a.isMotivated()) {
                no++;
            }
        }
        return no;
    }
    public List<Grade> getStudentGradesForSubject(Student student, Subject subject) {
        List<Grade> studentGrades = getStudentGrades(student);
        List<Grade> studentGradesForSubject = new ArrayList<>();
        for(Grade g : studentGrades) {
            if (g.getSubject() == subject) {
                studentGradesForSubject.add(g);
            }
        }
        return studentGradesForSubject;
    }
    public void motivateStudentAbsence(Student student, Date date, Subject subject) {
        List<Absence> studentAbsences = absences.get(student);
        boolean found = false;
        for (Absence a : studentAbsences) {
            if (a.getDate() == date && a.getSubject() == subject) {
                found = true;
                a.setMotivated();
                break;
            }
        }

        if (!found) {
            System.out.println("The absence for the given date and subject could not be found.");
        }
    }

    public double getSubjectFinalGrade(Student student, Subject subject) {
        List<Grade> studentGradesForSubject = getStudentGradesForSubject(student, subject);
        if(studentGradesForSubject.size() == 0) {
            System.out.println("The student does not have any grades for this subject.");
            return 0;
        }
        int sum = 0;
        for(Grade g : studentGradesForSubject) {
            if (g.getSubject() == subject) {
                sum += g.getValue();
            }
        }
        return (double) (sum / studentGradesForSubject.size());
    }
    public Set<Student> getAllStudents() {
        Set<Student> students1 = students;
        return students1;
    }
    public Set<Teacher> getAllTeachers() {
        Set<Teacher> teachers1 = teachers;
        return teachers1;
    }
    public Set<Subject> getAllSubjects() {
        Set<Subject> subjects1 = subjects;
        return subjects1;
    }
}
