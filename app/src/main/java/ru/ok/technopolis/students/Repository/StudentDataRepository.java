package ru.ok.technopolis.students.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.ok.technopolis.students.Student;

public class StudentDataRepository implements StudentRepository
{
    private static final StudentDataRepository ourInstance = new StudentDataRepository();

    public static StudentDataRepository getInstance() {
        return ourInstance;
    }

    private List <Student> students;



    private StudentDataRepository()
    {
        students = new ArrayList<>();
    }

    @Override
    public List studentsOnRepository()
    {
        return students;
    }


    private boolean isAlreadyInRepository(UUID StudentId) {
        if(StudentId == null)
        {
            throw new NullPointerException();
        }
        for(int i = 0; i < students.size(); i ++){
            if(students.get(i).getId().equals(StudentId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Student student)
    {
        students.add(student);
    }


    @Override
    public void delete(Student student)
    {
        for(int i = 0; i < students.size(); i++){
            if (students.get(i).getId().equals(student.getId())){
                students.remove(i);
            }
        }
    }

    @Override
    public void edit(Student student) {
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId().equals(student.getId())){
                students.get(i).setFirstName(student.getFirstName());
                students.get(i).setSecondName(student.getSecondName());
            }
        }
    }
}
