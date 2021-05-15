package com.stefanpascu.pao.service;

import com.stefanpascu.pao.model.Classroom;
import com.stefanpascu.pao.model.Student;
import com.stefanpascu.pao.model.Subject;
import com.stefanpascu.pao.model.enums.SubjectName;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.stefanpascu.pao.service.contants.Constants.CLASSROOMS_FILE;
import static com.stefanpascu.pao.service.contants.Constants.COMMA_DELIMITER;

class SubjectService {
    private static SubjectService instance = null;

    private SubjectService() { }

    public static SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }

        return instance;
    }
    
    private List<Subject> subjects = Arrays.asList(new Subject("ed89d64f-2765-4e8d-a9bb-77f0022411b7", SubjectName.COMPUTER_SCIENCE),
            new Subject("081ca444-606c-41ce-8b1c-e23265313b15", SubjectName.MATHEMATICS));

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Subject getSubjects(String subjectId) {
        List<Subject> subjects = getSubjects();
        return subjects.stream()
                .filter(subject -> subject.getId().equals(subjectId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("The subject with ID " + subjectId + " does not exist"));
    }

    public boolean subjectExists(String subjectId) {
        List<Subject> subjects = getSubjects();
        return subjects.stream().anyMatch(subject -> subject.getId().equals(subjectId));
    }


}
