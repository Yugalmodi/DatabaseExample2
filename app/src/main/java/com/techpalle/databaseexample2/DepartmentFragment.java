package com.techpalle.databaseexample2;

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
public class DepartmentFragment extends Fragment {
    EditText editTextdepart, editTextloc;
    TextView textViewEmployee, textViewDatabase;
    Button button;
    MyDB db;

    public DepartmentFragment() {
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
        View v = inflater.inflate(R.layout.fragment_department, container, false);
        textViewEmployee = (TextView) v.findViewById(R.id.text_view_employee_page);
        textViewDatabase = (TextView) v.findViewById(R.id.text_view_database);
        editTextdepart = (EditText) v.findViewById(R.id.edit_text_department_name);
        editTextloc = (EditText) v.findViewById(R.id.edit_text_department_floor);
        button = (Button) v.findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dep = editTextdepart.getText().toString().trim();
                String loc = editTextloc.getText().toString().trim();
                if (loc.isEmpty() || dep.isEmpty()){
                    editTextdepart.requestFocus();
                    Toast.makeText(getActivity(), "Please fill all details", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.insertDepartmentValues(dep, loc);
                editTextdepart.setText("");
                editTextloc.setText("");
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoDatabasePage();
            }
        });

        textViewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoEmployeePage();
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
