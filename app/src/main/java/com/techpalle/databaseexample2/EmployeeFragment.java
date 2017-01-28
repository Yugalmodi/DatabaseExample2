package com.techpalle.databaseexample2;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment {
    Button buttonSubmit;
    EditText editTextName, editTextSalary, editTextDepartment;
    MyDB db;
    TextView textViewDepartment, textViewDatabase;
    public EmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MyDB(getActivity());
        db.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_employee, container, false);
        editTextName = (EditText) v.findViewById(R.id.edit_text_name);
        editTextSalary = (EditText) v.findViewById(R.id.edit_text_salary);
        editTextDepartment = (EditText) v.findViewById(R.id.edit_text_department);
        buttonSubmit = (Button) v.findViewById(R.id.button_submit);
        textViewDepartment = (TextView) v.findViewById(R.id.text_view_department_page);
        textViewDatabase = (TextView) v.findViewById(R.id.text_view_database);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String salary = editTextSalary.getText().toString().trim();
                String department = editTextDepartment.getText().toString().trim();
                if(name.isEmpty() || salary.isEmpty() || department.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor c = db.queryDep(department);
                    if(c!=null){
                        c.moveToNext();
                        db.insertValues(name, salary, c.getInt(0));
                        editTextName.setText("");
                        editTextDepartment.setText("");
                        editTextSalary.setText("");
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.gotoDatabasePage();
                    }
                }
            }
        });

        textViewDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoDepartmentPage();
            }
        });

        textViewDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoDatabasePage();
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
