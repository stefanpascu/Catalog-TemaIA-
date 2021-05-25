package com.stefanpascu.pao.service;

import com.DatabaseConnection;
import com.stefanpascu.pao.model.Grade;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.stefanpascu.pao.service.contants.Constants.DATE_FORMAT;
import static java.lang.Integer.parseInt;

class GradeService {
    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static GradeService instance = null;

    private GradeService() { }

    public static GradeService getInstance() {
        if (instance == null) {
            instance = new GradeService();
        }

        return instance;
    }

    public Grade getGrades(String gradeId) throws SQLException, ParseException {
        String sql = "SELECT * FROM grade;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(gradeId.equals(result.getString(1)))
                return new Grade(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5));
        }
        System.out.println("The grade with the given ID does not exist.");
        return null;
    }

    public List<Grade> getGrades(String studentId, String subjectId) throws SQLException, ParseException {
        List<Grade> records = new ArrayList<>();

        String sql = "SELECT * FROM grade;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(subjectId.equals(result.getString(4)) && studentId.equals(result.getString(5)))
                records.add(new Grade(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5)));
        }

        return records;
    }

    public List<Grade> getGrades() throws SQLException, ParseException {
        List<Grade> records = new ArrayList<>();

        String sql = "SELECT * FROM grade;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            Grade grade = new Grade(result.getString(1), parseInt(result.getString(2)), DATE_FORMAT.parse(result.getString(3)), result.getString(4), result.getString(5));
            records.add(grade);
        }

        return records;
    }

    public void saveGrade(Grade grade) {
        String sql = "INSERT INTO grade (value , date, subject_id, student_id)\n" +
                "VALUES (?, ?, ?, ?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(1, String.valueOf(grade.getValue()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(2, dateToString(grade.getDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(3, grade.getSubjectId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(4, grade.getStudentId());
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
            System.out.println("Grade successfully added.");
        }
    }

    public void updateGradeValue(String gradeId, int gradeValue) throws SQLException {
        String sql = "UPDATE grade SET value=? WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(gradeValue));
        stmt.setString(2, gradeId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Grade value successfully updated.");
        }
    }

    public void deleteGrade(String gradeId) throws SQLException {
        String sql = "DELETE FROM grade WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, gradeId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Grade successfully removed.");
        }
    }


    public boolean gradeExists(String gradeId) throws SQLException, ParseException {
        Grade grade = getGrades(gradeId);

        if(grade == null) {
            return false;
        }
        return true;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }

//    From Etapa_II
//
//    private Grade getRecordFromLine(String line) {
//        List<String> values = new ArrayList<>();
//        try (Scanner rowScanner = new Scanner(line)) {
//            rowScanner.useDelimiter(COMMA_DELIMITER);
//            while (rowScanner.hasNext()) {
//                values.add(rowScanner.next());
//            }
//        }
//
//        try {
//            Date date = DATE_FORMAT.parse(values.get(2));
//            int value = parseInt(values.get(1));
//            return new Grade(values.get(0), value, date, values.get(4), values.get(3));
//        } catch (ParseException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    private String recordToLine(Grade grade) {
//        return String.format("%s,%s,%s,%s,%s",
//                grade.getId(), grade.getValue(), dateToString(grade.getDate()), grade.getStudentId(), grade.getSubjectId());
//    }
}