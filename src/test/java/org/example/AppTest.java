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
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAddStudentValid() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "aaa", 111);
        assertEquals(result, 0);

        Student added = new Student("11", "aaa", 111);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }

    public void testAddStudentInvalid() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "aaa", 2);
        assertEquals(result, 1);
    }

    public void testAddStudentInvalidGroup() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "aaa", 10);
        assertEquals(result, 1);

        result = service.saveStudent("11", "aaa", 999);
        assertEquals(result, 1);

        result = service.saveStudent("11", "aaa", 111-1);
        assertEquals(result, 1);

        result = service.saveStudent("11", "aaa", 937+1);
        assertEquals(result, 1);

        result = service.saveStudent("11", "aaa", 0);
        assertEquals(result, 1);

        result = service.saveStudent("11", "aaa", -1);
        assertEquals(result, 1);
    }

    public void testAddStudentValidName() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));

        result = service.saveStudent("11", "" + "a", 933);
        assertEquals(result, 0);

        added = new Student("11", "" + "a", 933);
        list = new ArrayList<Student>();
        students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }

    public void testAddStudentInvalidName() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "", 933);
        assertEquals(result, 1);

        result = service.saveStudent("11", null, 933);
        assertEquals(result, 1);
    }

    public void testAddStudentValidId() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        Student added = new Student("11", "asdf", 933);
        List<Student> list = new ArrayList<Student>();
        Iterable<Student> students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));

        result = service.saveStudent("11", "asdf", 933);
        assertEquals(result, 0);

        added = new Student("" + "11", "asdf", 933);
        list = new ArrayList<Student>();
        students = service.findAllStudents();
        students.forEach(list::add);
        assertTrue(list.contains(added));
    }

    public void testAddStudentInvalidId() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

        int result = service.saveStudent("", "asdf", 933);
        assertEquals(result, 1);

        result = service.saveStudent(null, "asdf", 933);
        assertEquals(result, 1);
    }
}
