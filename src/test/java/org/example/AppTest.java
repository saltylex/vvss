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

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    public void testAddStudentValidGroup1() {
        int result = service.saveStudent("11", "asdf", 111);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 111);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentValidGroup2() {
        int result = service.saveStudent("11", "asdf", 937);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 937);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentInvalidGroup1() {
        int result = service.saveStudent("11", "aaa", 10);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidGroup2() {
        int result = service.saveStudent("11", "aaa", 999);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidGroup3() {
        int result = service.saveStudent("11", "aaa", 111-1);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidGroup4() {
        int result = service.saveStudent("11", "aaa", 937+1);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidGroup5() {
        int result = service.saveStudent("11", "aaa", 0);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidGroup6() {
        int result = service.saveStudent("11", "aaa", -1);
        assertEquals(result, 1);
    }

    public void testAddStudentValidName1() {
        int result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentValidName2() {
        int result = service.saveStudent("11", "" + "a", 933);
        assertEquals(result, 0);

        Student added = new Student("11", "" + "a", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentInvalidName1() {
        int result = service.saveStudent("11", "", 933);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidName2() {
        int result = service.saveStudent("11", null, 933);
        assertEquals(result, 1);
    }

    public void testAddStudentValidId1() {
        int result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentValidId2() {
        int result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        Student added = new Student("" + "11", "asdf", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }
    public void testAddStudentInvalidId1() {
        int result = service.saveStudent("", "asdf", 933);
        assertEquals(result, 1);
    }
    public void testAddStudentInvalidId2() {
        int result = service.saveStudent(null, "asdf", 933);
        assertEquals(result, 1);
    }

    public void testAddTema1() {
        int result = service.saveTema(null, "test", 2, 4);
        assertEquals(result, 1);
    }
    public void testAddTema2() {
        int result = service.saveTema("3", "", 2, 4);
        assertEquals(result, 1);
    }
    public void testAddTema3() {
        int result = service.saveTema("3", "test", 15, 4);
        assertEquals(result, 1);
    }
    public void testAddTema4() {
        int result = service.saveTema("3", "test", 2, 15);
        assertEquals(result, 1);
    }
    public void testAddTema5() {
        int result = service.saveTema("3", "test", 2, 4);
        System.out.println(result);
        assertEquals(result, 0);

        Tema added = new Tema("3", "test", 2, 4);
        List<Tema> list = new ArrayList<>();
        Iterable<Tema> assignments = service.findAllTeme();
        assignments.forEach(list::add);
        assertTrue(list.contains(added));
    }

}
