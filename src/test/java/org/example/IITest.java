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
import validation.*;

import java.util.ArrayList;
import java.util.List;

public class IITest extends TestCase {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    public IITest( String testName ) {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( IITest.class );
    }

    public void testAddStudent() {
        int result = service.saveStudent("20", "II", 111);
        assertEquals(result, 1);

        Student added = new Student("20", "II", 111);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }

    public void testAddAssignment() {
        int result = service.saveStudent("20", "II", 111);
        assertEquals(result, 1);

        Student addedStud = new Student("20", "II", 111);
        List<Student> listStud = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(listStud::add);
        assertTrue(listStud.contains(addedStud));

        result = service.saveTema("22", "temaII", 3, 2);
        assertEquals(result, 1);

        Tema addedAssign = new Tema("22", "temaII", 3, 2);
        List<Tema> listAssign = new ArrayList<Tema>();
        Iterable<Tema> assignments = service.findAllTeme();
        assignments.forEach(listAssign::add);
        assertTrue(listAssign.contains(addedAssign));
    }

    public void testAddGrade() {
        int result = service.saveStudent("20", "II", 111);
        assertEquals(result, 1);

        Student addedStud = new Student("20", "II", 111);
        List<Student> listStud = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(listStud::add);
        assertTrue(listStud.contains(addedStud));

        result = service.saveTema("22", "temaII", 3, 2);
        assertEquals(result, 1);

        Tema addedAssign = new Tema("22", "temaII", 3, 2);
        List<Tema> listAssign = new ArrayList<Tema>();
        Iterable<Tema> assignments = service.findAllTeme();
        assignments.forEach(listAssign::add);
        assertTrue(listAssign.contains(addedAssign));

        result = service.saveNota("20", "22", 10, 3, "good");
        assertEquals(result, 1);

//        Nota addedGrade = new Nota("20", "22", 10, 3, "good");
//        List<Nota> listGrade = new ArrayList<Nota>();
//        Iterable<Nota> grades = service.findAllTeme();
//        grades.forEach(listGrade::add);
//        assertTrue(listGrade.contains(addedGrade));
    }
}
