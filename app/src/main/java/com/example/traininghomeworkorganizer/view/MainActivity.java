package com.example.traininghomeworkorganizer.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.traininghomeworkorganizer.R;
import com.example.traininghomeworkorganizer.adapter.HomeworkAdapter;
import com.example.traininghomeworkorganizer.database.HomeworkEntity;
import com.example.traininghomeworkorganizer.presenter.Contract;
import com.example.traininghomeworkorganizer.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HomeworkAdapter.HomeworkDelegate, Contract.HomeworkView, AddHomeworkFragment.ItemFragmentListener {

    @BindView(R.id.homework_rv_main)
    RecyclerView homeworkRecyclerView;

    @BindView(R.id.add_button_main)
    Button addButton;

    private Contract.HomeworkPresenter homeworkPresenter;
    private AddHomeworkFragment addHomeworkFragment = new AddHomeworkFragment();

    private static String return_key = "return_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        homeworkPresenter = new MainPresenter(this);

        //homeworkPresenter.insertHomework(new HomeworkEntity(1, 1, "Age Clicker", true));
        homeworkPresenter.getHomework();

        RecyclerView.ItemDecoration decorator = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        homeworkRecyclerView.addItemDecoration(decorator);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle receiptBundle = new Bundle();
                receiptBundle.putString(return_key, "");

                addHomeworkFragment.setArguments(receiptBundle);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frame_layout_main, addHomeworkFragment)
                        .addToBackStack(addHomeworkFragment.getTag())
                        .commit();
            }
        });
    }

    @Override
    public void displayHomework(List<HomeworkEntity> homework) {
        HomeworkAdapter adapter = new HomeworkAdapter(homework, this);
        homeworkRecyclerView.setAdapter(adapter);
        homeworkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void homeworkIsEmpty() {
        Toast.makeText(this, "No Homework Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String errorString) {
        Toast.makeText(this, "Error: "+ errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HomeworkSelected(HomeworkAdapter.HomeworkDelegate homeworkDelegate) {
        //TODO implement modify on the object
    }


    @Override
    public void insertHomeworkFromFragment(int week, int day, String subject, boolean completed) {
        getSupportFragmentManager().popBackStack();

        homeworkPresenter.insertHomework(new HomeworkEntity(week, day, subject, completed));
        homeworkPresenter.getHomework();

    }
}
