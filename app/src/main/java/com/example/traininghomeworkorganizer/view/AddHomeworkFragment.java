package com.example.traininghomeworkorganizer.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    @BindView(R.id.radio_group_fragment)
    RadioGroup radioGroupFragment;

    @BindView(R.id.radioButton_true_fragment)
    RadioButton trueRadioButton;

    @BindView(R.id.radioButton_false_fragment)
    RadioButton falseRadioButton;

    @BindView(R.id.save_button_fragment)
    Button saveButton;

    private ItemFragmentListener fragmentListener;

    private boolean completed;

    public interface ItemFragmentListener {
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
        weekEditText.setText("");
        dayEditText.setText("");
        subjectEditText.setText("");

        falseRadioButton.setChecked(true);

        radioGroupFragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radioButton_true_fragment:
                        completed = true;
                        //Log.d("TAG_X", checkedId+" all");
                        break;
                    case R.id.radioButton_false_fragment:
                        completed = false;
                        //Log.d("TAG_X", checkedId+" day1");
                        break;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG_X", "clicking save");
                if (!weekEditText.getText().toString().equals("") || !dayEditText.getText().toString().equals("") ||
                        !subjectEditText.getText().toString().equals("")) {


                    int weekTemp = Integer.parseInt(weekEditText.getText().toString());
                    int dayTemp = Integer.parseInt(dayEditText.getText().toString());

                    boolean completedTemp;

                    if (dayTemp >= 1 && dayTemp <= 5) {
                        fragmentListener.insertHomeworkFromFragment(weekTemp, dayTemp, subjectEditText.getText().toString(), completed);
                    } else
                        Toast.makeText(getActivity(), "day must be within 1-5", Toast.LENGTH_SHORT).show();

                    weekEditText.setText("");
                    dayEditText.setText("");
                    subjectEditText.setText("");
                }
                else
                    Toast.makeText(getActivity(), "Must Enter All Data", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity)
            this.fragmentListener = (MainActivity) context;
    }
}
