package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class BBITest

        extends TestCase {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    public BBITest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BBITest.class);
    }

    public void testAddStudent() {
        int result = service.saveStudent("12", "stud1", 111);
        assertEquals(result, 1);

        Student added = new Student("12", "stud1", 111);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }

    public void testAddAssignment() {
        int result = service.saveTema("4", "desc", 3, 2);
        assertEquals(result, 1);
    }

    public void testAddGrade() {
        int result1 = service.saveTema("4", "desc", 3, 2);
        int result2 = service.saveStudent("12", "stud1", 111);

        int result3 = service.saveNota("12", "4", 10, 3, "none");
        assertEquals(result3, 1);
    }

    public void testAllThree() {
        testAddStudent();
        testAddAssignment();
        testAddGrade();
    }
}
