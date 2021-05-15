package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.Classroom;
import com.stefanpascu.pao.model.Grade;
import com.stefanpascu.pao.model.Student;
import com.stefanpascu.pao.model.enums.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.stefanpascu.pao.service.contants.Constants.*;

class ClassroomService {
    private static ClassroomService instance = null;

    private ClassroomService() { }

    public static ClassroomService getInstance() {
        if (instance == null) {
            instance = new ClassroomService();
        }

        return instance;
    }

    public List<Classroom> getClassrooms() {
        List<Classroom> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(CLASSROOMS_FILE));) {
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

    public Classroom getClassroom(String classroomId) {
        return null;
    }

    public boolean classroomExists(String classroomId) {
        List<Classroom> classrooms = getClassrooms();
        
        return classrooms.stream().anyMatch(classroom -> classroom.getId().equals(classroomId));
    }

    private Classroom getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        return new Classroom(values.get(0), values.get(1));
    }
}
