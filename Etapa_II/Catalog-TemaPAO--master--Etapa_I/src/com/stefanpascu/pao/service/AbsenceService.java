package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.Absence;
import com.stefanpascu.pao.model.Grade;

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

class AbsenceService {
    private static AbsenceService instance = null;

    private AbsenceService() { }

    public static AbsenceService getInstance() {
        if (instance == null) {
            instance = new AbsenceService();
        }

        return instance;
    }

    public List<Absence> getAbsences(String studentId) {
        List<Absence> absences = getAbsences();
        return absences.stream().filter(absence -> absence.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public List<Absence> getAbsences(String studentId, String subjectId) {
        List<Absence> absences = getAbsences();
        return absences.stream()
                .filter(absence -> absence.getStudentId().equals(studentId))
                .filter(absence -> absence.getSubjectId().equals(subjectId))
                .collect(Collectors.toList());
    }

    public void saveAbsence(Absence absence) throws IOException {
        FileWriter fw = new FileWriter(ABSENCES_FILE, true);
        fw.write("\n");
        fw.write(recordToLine(absence));
        fw.close();
    }

    public List<Absence> getAbsences() {
        List<Absence> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ABSENCES_FILE));) {
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

    private Absence getRecordFromLine(String line) {
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
            return new Absence(values.get(0), value, date, values.get(3), values.get(4));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

    private String recordToLine(Absence absence) {
        return String.format("%s,%s,%s,%s,%s",
                absence.getId(), absence.getValue(), dateToString(absence.getDate()), absence.getStudentId(), absence.getSubjectId());
    }
}