package com.stefanpascu.pao;

import com.stefanpascu.pao.model.*;
import com.stefanpascu.pao.model.enums.Gender;
import com.stefanpascu.pao.model.enums.SubjectName;
import com.stefanpascu.pao.service.ClassbookService;
import com.stefanpascu.pao.service.ClassbookService2;
import com.stefanpascu.pao.service.StudentService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassbookService classbookService = ClassbookService.getInstance();

        classbookService.loadData();

//        classbookService.printClassrooms();

//        classbookService.printSubjects();

//        classbookService.printStudents();

//        classbookService.addStudent();

//        classbookService.addGrade();

//        classbookService.addAbsence();

//        classbookService.showStudentsGradesForSubject();

//        classbookService.showStudentsAbsencesForSubject();

//        classbookService.getNoOfAbsences();

        while (true) {
            System.out.println("\nThis is your classbook!" +
                    "\n You are now able to use the following methods(by pressing their corresponding number) to modify the classbook:" +
                    "\n1 - show the students's information" +
                    "\n2 - show the classrooms's information" +
                    "\n3 - show the subjects's's information" +

                    "\n4 - add a new student" +
                    "\n5 - add a grade for a specific student" +
                    "\n6 - add an absence for a specific student" +

                    "\n7 - show a student's grades for a specific subject" +
                    "\n8 - show a student's absences for a subject" +
                    "\n9 - show the total number of absences a student has");

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
                    classbookService.addStudent();
                    break;

                case 5:
                    classbookService.addGrade();
                    break;

                case 6:
                    classbookService.addAbsence();
                    break;

                case 7:
                    classbookService.showStudentsGradesForSubject();
                    break;

                case 8:
                    classbookService.showStudentsAbsencesForSubject();
                    break;

                case 9:
                    classbookService.getNoOfAbsences();
                    break;

                default:
                    System.out.println("The given number does not correspond to any action.");
                    break;
            }
        }
    }
}
