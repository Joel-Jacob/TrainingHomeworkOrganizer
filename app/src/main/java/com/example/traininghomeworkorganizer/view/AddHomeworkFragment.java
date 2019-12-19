package com.example.traininghomeworkorganizer.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.traininghomeworkorganizer.R;
import com.example.traininghomeworkorganizer.database.HomeworkEntity;
import com.example.traininghomeworkorganizer.presenter.Contract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddHomeworkFragment extends Fragment {

    @BindView(R.id.week_et_fragment)
    EditText weekEditText;

    @BindView(R.id.day_et_fragment)
    EditText dayEditText;

    @BindView(R.id.subject_et_fragment)
    EditText subjectEditText;

    @BindView(R.id.completed_et_fragment)
    EditText completedEditText;

    @BindView(R.id.save_button_fragment)
    Button saveButton;

    private ItemFragmentListener fragmentListener;

    public interface ItemFragmentListener{
        void insertHomeworkFromFragment(int week, int day, String subject, boolean completed);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_homework_fragment_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG_X", "clicking save");
                int weekTemp = Integer.parseInt(weekEditText.getText().toString());
                int dayTemp = Integer.parseInt(dayEditText.getText().toString());
                String completedText = completedEditText.getText().toString().trim();

                boolean completedTemp;

                if (dayTemp >= 1 && dayTemp <= 5) {

                    if (completedText.equals("true")) {
                        completedTemp = true;
                        fragmentListener.insertHomeworkFromFragment(weekTemp, dayTemp, subjectEditText.getText().toString(), completedTemp);
                    } else if (completedText.equals("false")) {
                        completedTemp = false;
                        fragmentListener.insertHomeworkFromFragment(weekTemp, dayTemp, subjectEditText.getText().toString(), completedTemp);
                    } else
                        Toast.makeText(getActivity(), "completed must be true or false", Toast.LENGTH_SHORT).show();

                    //Log.d("TAG_X", completedEditText.getText().toString() + " " + dayTemp);

                } else
                    Toast.makeText(getActivity(), "day must be within 1-5", Toast.LENGTH_SHORT).show();

                weekEditText.setText("");
                dayEditText.setText("");
                subjectEditText.setText("");
                completedEditText.setText("");

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof MainActivity)
            this.fragmentListener = (MainActivity)context;
    }
}
