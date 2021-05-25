package com.stefanpascu.pao.service;

import com.DatabaseConnection;

import com.stefanpascu.pao.model.Subject;
import com.stefanpascu.pao.model.enums.SubjectName;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


class SubjectService {
    Connection conn = DatabaseConnection.getDataBaseConnection();

    private static SubjectService instance = null;

    private SubjectService() { }

    public static SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }

        return instance;
    }


    public Subject getSubjects(String subjectId) throws SQLException {
        String sql = "SELECT * FROM subject;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            if(subjectId.equals(result.getString(1)))
                return new Subject(result.getString(1), SubjectName.valueOf(result.getString(2)));
        }
        System.out.println("The subject with the given ID does not exist.");
        return null;
    }

    public List<Subject> getSubjects() throws SQLException {
        List<Subject> records = new ArrayList<>();

        String sql = "SELECT * FROM subject;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()) {
            Subject subject = new Subject(result.getString(1), SubjectName.valueOf(result.getString(2)));
            records.add(subject);
        }

        return records;
    }

    public boolean subjectExists(String subjectId) throws SQLException {
        Subject subjects = getSubjects(subjectId);

        if(subjects == null) {
            return false;
        }
        return true;
    }


}
