package com.stefanpascu.pao;

import com.stefanpascu.pao.service.ClassbookService;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassbookService classbookService = ClassbookService.getInstance();

        classbookService.loadData();

        System.out.println("\nThis is your classbook!");

        while (true) {
            System.out.println(
                    "\n You are now able to use the following methods(by introducing their corresponding number) to modify the classbook:" +
                    "\n1 - show the students' information" +
                    "\n2 - show the classrooms' information" +
                    "\n3 - show the subjects' information" +
                            "\n" +
                    "\n4 - add a new classroom" +
                    "\n5 - add a new student" +
                    "\n6 - add a grade for a specific student" +
                    "\n7 - add an absence for a specific student" +
                            "\n" +
                    "\n8 - show a students grades for a specific subject" +
                    "\n9 - show a students absences for a subject" +
                    "\n10 - show the total number of absences a student has" +
                            "\n" +
                    "\n11 - change a students classroom ID(transfer him to the specific classroom)" +
                    "\n12 - change a students grade value" +
                    "\n13 - change a classrooms name(two or more classrooms may share the same name)" +
                    "\n14 - change an absences value(partly motivate it)" +
                            "\n" +
                    "\n15 - motivate a students absence(remove it completely from the database)" +
                    "\n16 - remove a student from the classbook(together with his absences and grades)" +
                    "\n17 - cancel a students grade" +
                    "\n18 - remove a classroom from the classbook" +
                    "\n19 - CLOSE the classbook"
            );

            Scanner in = new Scanner(System.in);
            int caz = in.nextInt();

            switch (caz) {
                case 1:
                    classbookService.printStudents();
                    break;

                case 2:
                    classbookService.printClassrooms();
                    break;

                case 3:
                    classbookService.printSubjects();
                    break;

                case 4:
                    classbookService.addClassroom();
                    break;

                case 5:
                    classbookService.addStudent();
                    break;

                case 6:
                    classbookService.addGrade();
                    break;

                case 7:
                    classbookService.addAbsence();
                    break;

                case 8:
                    classbookService.showStudentsGradesForSubject();
                    break;

                case 9:
                    classbookService.showStudentsAbsencesForSubject();
                    break;

                case 10:
                    classbookService.getNoOfAbsences();
                    break;

                case 11:
                    classbookService.updateSudentsClassroomId();
                    break;

                case 12:
                    classbookService.updateGradeValue();
                    break;

                case 13:
                    classbookService.updateClassroomName();
                    break;

                case 14:
                    classbookService.updateAbsenceValue();
                    break;

                case 15:
                    classbookService.deleteAbsence();
                    break;

                case 16:
                    classbookService.deleteStudent();
                    break;

                case 17:
                    classbookService.deleteGrade();
                    break;

                case 18:
                    classbookService.deleteClassroom();
                    break;

                case 19:
                    return;

                default:
                    System.out.println("The inserted number does not correspond to any action.");
                    break;
            }
        }
    }
}
