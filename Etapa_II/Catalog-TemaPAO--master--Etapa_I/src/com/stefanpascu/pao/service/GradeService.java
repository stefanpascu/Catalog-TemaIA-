package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.Grade;
import com.stefanpascu.pao.model.Student;
import com.stefanpascu.pao.model.enums.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.stefanpascu.pao.service.contants.Constants.*;
import static com.stefanpascu.pao.service.contants.Constants.DATE_FORMAT;

class GradeService {
    private static GradeService instance = null;

    private GradeService() { }

    public static GradeService getInstance() {
        if (instance == null) {
            instance = new GradeService();
        }

        return instance;
    }

    public List<Grade> getGrades(String studentId) {
        List<Grade> grades = getGrades();
        return grades.stream().filter(grade -> grade.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public List<Grade> getGrades(String studentId, String subjectId) {
        List<Grade> grades = getGrades();
        return grades.stream()
                .filter(grade -> grade.getStudentId().equals(studentId))
                .filter(grade -> grade.getSubjectId().equals(subjectId))
                .collect(Collectors.toList());
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

    public void saveGrade(Grade grade) throws IOException {
        FileWriter fw = new FileWriter(GRADES_FILE, true);
        fw.write("\n");
        fw.write(recordToLine(grade));
        fw.close();
    }

    public List<Grade> getGrades() {
        List<Grade> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(GRADES_FILE));) {
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

    private Grade getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }

        try {
            Date date = DATE_FORMAT.parse(values.get(2));
            int value = Integer.parseInt(values.get(1));
            return new Grade(values.get(0), value, date, values.get(4), values.get(3));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String recordToLine(Grade grade) {
        return String.format("%s,%s,%s,%s,%s",
                grade.getId(), grade.getValue(), dateToString(grade.getDate()), grade.getStudentId(), grade.getSubjectId());
    }
}