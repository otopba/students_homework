package ru.ok.technopolis.students;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentHolder> {

    private final List<Student> students;
    private OnStudentClickListener listener;

    StudentsAdapter(List<Student> students, OnStudentClickListener listener) {
        this.listener = listener;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStudentClick((Student) view.getTag());
            }
        });
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder studentHolder, int i) {
        Student student = students.get(i);
        studentHolder.bind(student);
        studentHolder.itemView.setTag(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    class StudentHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textFirstName, textLastName;

        StudentHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_student__avatar);

            textFirstName = itemView.findViewById(R.id.item_student__first_name);
            textLastName = itemView.findViewById(R.id.item_student__last_name);
        }

        void bind(Student student) {
            imageView.setImageResource(student.getPhoto());
            textFirstName.setText(student.getFirstName());
            textLastName.setText(student.getSecondName());
        }
    }
}
