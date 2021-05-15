package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.Student;
import com.stefanpascu.pao.model.enums.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.stefanpascu.pao.service.contants.Constants.*;

public class StudentService {
    private static StudentService instance = null;

    private StudentService() { }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }

        return instance;
    }

    public List<Student> getStudents() {
        List<Student> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(STUDENTS_FILE));) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        return records;
    }

    public Student getStudent(String id) {
        List<Student> students = getStudents();
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("The student with ID " + id + " does not exist"));
    }

    public boolean studentExists(String id) {
        List<Student> students = getStudents();
        return students.stream().anyMatch(student -> student.getId().equals(id));
    }

    public void saveStudent(Student student) throws IOException {
        FileWriter fw = new FileWriter(STUDENTS_FILE, true);
        fw.write("\n");
        fw.write(recordToLine(student));
        fw.close();
    }

    private Student getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        Date date;
        try {
            date = DATE_FORMAT.parse(values.get(4));
            return new Student(values.get(0), values.get(1), values.get(2), Gender.get(values.get(3)), date, values.get(5));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private String recordToLine(Student student) {
        return String.format("%s,%s,%s,%s,%s,%s",
                student.getId(), student.getFirstName(), student.getLastName(), student.getGender(), DATE_FORMAT.format(student.getBirthDate()), student.getClassroomId());
    }
}
