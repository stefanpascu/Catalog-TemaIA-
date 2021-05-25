package com.stefanpascu.pao.service;

import com.DatabaseConnection;
import com.stefanpascu.pao.model.Absence;
import com.stefanpascu.pao.model.Grade;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.stefanpascu.pao.service.contants.Constants.*;
import static java.lang.Integer.parseInt;

class AbsenceService {
    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static AbsenceService instance = null;

    private AbsenceService() { }

    public static AbsenceService getInstance() {
        if (instance == null) {
            instance = new AbsenceService();
        }

        return instance;
    }

    public List<Absence> getAbsences(String studentId) throws SQLException, ParseException {
        List<Absence> records = new ArrayList<>();

        String sql = "SELECT * FROM absence;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(studentId.equals(result.getString(5)))
                records.add(new Absence(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5)));
        }

        return records;
    }

    public Absence getAbsence(String absenceId) throws SQLException, ParseException {
        String sql = "SELECT * FROM absence;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(absenceId.equals(result.getString(1)))
                return new Absence(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5));
        }
        System.out.println("The absence with the given ID does not exist.");
        return null;
    }

    public List<Absence> getAbsences(String studentId, String subjectId) throws SQLException, ParseException {
        List<Absence> records = new ArrayList<>();

        String sql = "SELECT * FROM absence;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(subjectId.equals(result.getString(4)) && studentId.equals(result.getString(5)))
                records.add(new Absence(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5)));
        }

        return records;
    }

    public void saveAbsence(Absence absence) {
        String sql = "INSERT INTO absence (value , date, subject_id, student_id)\n" +
                "VALUES (?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            assert stmt != null;
            stmt.setString(1, String.valueOf(absence.getValue()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(2, dateToString(absence.getDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(3, absence.getSubjectId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(4, absence.getStudentId());
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
            System.out.println("Absence successfully added.");
        }
    }

    public List<Absence> getAbsences() throws SQLException, ParseException {
        List<Absence> records = new ArrayList<>();

        String sql = "SELECT * FROM absence;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            Absence absence = new Absence(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5));
            records.add(absence);
        }

        return records;
    }

    public void updateAbsenceValue(String absenceId, int absenceValue) throws SQLException {
        String sql = "UPDATE absence SET value=? WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(absenceValue));
        stmt.setString(2, absenceId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Absence value successfully updated.");
        }
    }

    public void deleteAbsence(String absenceId) throws SQLException {
        String sql = "DELETE FROM absence WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, absenceId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("The absence has been motivated.");
        }
    }


    public boolean absenceExists(String absenceId) throws SQLException, ParseException {
        Absence absence = getAbsence(absenceId);

        if(absence == null) {
            return false;
        }
        return true;
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
}