package com.stefanpascu.pao.service;

import com.DatabaseConnection;
import com.stefanpascu.pao.model.Classroom;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

class ClassroomService {

    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static ClassroomService instance = null;

    private ClassroomService() { }

    public static ClassroomService getInstance() {
        if (instance == null) {
            instance = new ClassroomService();
        }

        return instance;
    }

    public Classroom getClassrooms(String classroomId) throws SQLException, ParseException {
        String sql = "SELECT * FROM classroom;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(classroomId.equals(result.getString(1)))
                return new Classroom(result.getString(1), result.getString(2));
        }
        System.out.println("The classroom with the given ID does not exist.");
        return null;
    }

    public List<Classroom> getClassrooms() throws SQLException {
        List<Classroom> records = new ArrayList<>();

        String sql = "SELECT * FROM classroom;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            Classroom classroom = new Classroom(result.getString(1),result.getString(2));
            records.add(classroom);
        }

        return records;
    }

    public void saveClassroom(Classroom classroom) throws IOException, SQLException {
        String sql = "INSERT INTO classroom (name)\n" +
                "VALUES (?);";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            stmt.setString(1, classroom.getName());
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
            System.out.println("Classroom successfully added.");
        }
    }

    public void updateName(String classroomId, String name) throws IOException, SQLException {
        String sql = "UPDATE classroom SET name=? WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, classroomId);

        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (rows > 0) {
            System.out.println("Classroom name successfully updated.");
        }
    }

    public void deleteClassroom(String classroomId) throws SQLException {
        String sql = "DELETE FROM classroom WHERE id=?;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, classroomId);
        int rows = 0;
        try {
            rows = stmt.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Unable to delete classroom - students are part of it:\n" + throwables.getMessage());
        }
        if (rows > 0) {
            System.out.println("Classroom successfully removed.");
        }
    }


    public boolean classroomExists(String classroomId) throws SQLException, ParseException {
        Classroom classroom = getClassrooms(classroomId);
        if(classroom == null) {
            return false;
        }
        return true;
    }
//    From Etapa_II
//
//    private Classroom getRecordFromLine(String line) {
//        List<String> values = new ArrayList<>();
//        try (Scanner rowScanner = new Scanner(line)) {
//            rowScanner.useDelimiter(COMMA_DELIMITER);
//            while (rowScanner.hasNext()) {
//                values.add(rowScanner.next());
//            }
//        }
//
//        return new Classroom(values.get(0), values.get(1));
//    }
}
