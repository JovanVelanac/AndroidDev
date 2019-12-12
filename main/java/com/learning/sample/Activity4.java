package com.learning.sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.sample.act4.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity4 extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        File studentsFile = new File(getFilesDir(),"students.txt");
        new StudentsTask().execute(studentsFile);

    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private int position;
        private ImageView photoView;
        private TextView fullNameView;
        private TextView emailView;
        private TextView phoneView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photo);
            fullNameView = itemView.findViewById(R.id.fullName);
            emailView = itemView.findViewById(R.id.email);
            phoneView = itemView.findViewById(R.id.phone);
        }

        public void bind(int position, Student student) {
            this.position = position;
            fullNameView.setText(student.getFullName());
            emailView.setText(student.getEmail());
            phoneView.setText(student.getPhoneNumber());
            photoView.setImageBitmap(student.getPhoto());
        }
    }

    static class StudentsAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        private List<Student> students;

        StudentsAdapter(List<Student> students) {
            this.students = students;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.student, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            holder.bind(position, students.get(position));
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }

    class StudentsTask extends AsyncTask<File, Void, List<Student>> {

        // UI THREAD
        @Override
        protected void onPostExecute(List<Student> students) {

            recyclerView.setAdapter(new StudentsAdapter(students));
        }

        // BACKGROUND THREAD
        @Override
        protected List<Student> doInBackground(File... files) {
            File studentsFile = files[0];
            try {
                List<Student> studentsFromFile;
                if (!studentsFile.exists()) {
                    studentsFromFile = Student.generate();
                    studentsFile.createNewFile();
                    Student.saveStudents(studentsFromFile, studentsFile);
                } else {
                    studentsFromFile = Student.loadStudents(studentsFile);
                }
                return studentsFromFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
