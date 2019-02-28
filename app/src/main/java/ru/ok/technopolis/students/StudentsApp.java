package ru.ok.technopolis.students;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class StudentsApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students_app);

        final StudentsRepository repository = new StudentsRepository(studentsStub());
        final RecyclerView studentListRv = findViewById(R.id.students_app__student_list);
        final StudentView studentView = findViewById(R.id.students_app__student_view);
        final Button createStudentBtn = findViewById(R.id.students_app__create);
        final StudentsAdapter adapter = new StudentsAdapter(repository, new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                studentView.setStudent(repository.getActiveStudent());
            }
        });
        studentListRv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        studentListRv.setLayoutManager(layoutManager);
        createStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentView.setStudent(null);
            }
        });
        setupStudentViewListeners(studentView, repository, adapter);
    }

    private void setupStudentViewListeners(StudentView studentView, final StudentsRepository repository, final StudentsAdapter adapter) {
        studentView.setOnRemove(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.removeActive();
                adapter.notifyDataSetChanged();
                hideKeyboard();
            }
        });
        studentView.setOnSave(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                hideKeyboard();
            }
        });
        studentView.setOnCreate(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = (Student) v.getTag();
                repository.addStudent(student);
                adapter.notifyDataSetChanged();
                hideKeyboard();
            }
        });
        studentView.setOnHide(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.setActive(null);
                adapter.notifyDataSetChanged();
                hideKeyboard();
            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    static List<Student> studentsStub() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван", "Иванов", true, R.drawable.male_1));
        students.add(new Student("Петр", "Петров", true, R.drawable.male_2));
        students.add(new Student("Анастасия", "Медведева", false, R.drawable.female_1));
        return students;
    }

}
