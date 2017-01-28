package com.techpalle.databaseexample2;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {
    TextView textViewEmpTable, textViewDepTable, textViewSecondFloorEmployee, textViewGyaneshFloor, textViewHighSal, textViewSalAndID, textViewoEmpNameOnly,
            textViewDetailsLokeshAndNitesh, textViewDoubleSalary;
    Button buttonEmp, buttonDep;
    MyDB db;
    StringBuilder stringBuilder1, stringBuilder2, stringBuilder3, stringBuilder4, stringBuilder5, stringBuilder6, stringBuilder7, stringBuilder8, stringBuilder9, stringBuilder10;

    public DatabaseFragment() {
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
        View v = inflater.inflate(R.layout.fragment_database, container, false);
        textViewEmpTable = (TextView) v.findViewById(R.id.text_emp_table);
        textViewDepTable = (TextView) v.findViewById(R.id.text_dep_name);
        textViewSecondFloorEmployee = (TextView) v.findViewById(R.id.text_2_floor_emp_name);
        textViewGyaneshFloor = (TextView) v.findViewById(R.id.text_gyanesh_floor);
        textViewHighSal = (TextView) v.findViewById(R.id.text_emp_high_sal);
        textViewSalAndID = (TextView) v.findViewById(R.id.text_emp_sal_ID);
        textViewoEmpNameOnly = (TextView) v.findViewById(R.id.text_emp_name_only);
        textViewDetailsLokeshAndNitesh = (TextView) v.findViewById(R.id.text_emp_lokesh_nitesh);
        textViewDoubleSalary = (TextView) v.findViewById(R.id.text_emp_sal_double);

        buttonEmp = (Button) v.findViewById(R.id.emp);
        buttonDep = (Button) v.findViewById(R.id.dep);
        stringBuilder1 = new StringBuilder();
        stringBuilder2 = new StringBuilder();
        stringBuilder3 = new StringBuilder();
        stringBuilder4 = new StringBuilder();
        stringBuilder5 = new StringBuilder();
        stringBuilder6 = new StringBuilder();
        stringBuilder7 = new StringBuilder();
        stringBuilder8 = new StringBuilder();
        stringBuilder9 = new StringBuilder();
        stringBuilder10 = new StringBuilder();

        buttonEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoEmployeePage();
            }
        });
        buttonDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.gotoDepartmentPage();
            }
        });


//PRINT TABLE OF EMPLOYEES
        Cursor c = db.queryEmployee();
        if(c!=null){
            while(c.moveToNext()){
                stringBuilder1.append(c.getInt(0)+"  "+c.getString(1)+"  "+c.getString(2)+"  "+c.getString(3)+"\n");
            }
        }
        textViewEmpTable.setText(stringBuilder1);

//PRINT TABLE OF DEPARTMENTS
        Cursor c1 = db.queryDepartment();
        if(c1!=null){
            while(c1.moveToNext()){
                stringBuilder2.append(c1.getInt(0)+"  "+c1.getString(1)+"  "+c1.getString(2)+"\n");
            }
        }
        textViewDepTable.setText(stringBuilder2);

//PRINT EMPLOYEES OF 2ND FLOOR
        Cursor c2 = db.queryDep2ndFloor("IInd Floor");
        if (c2!=null){
            while(c2.moveToNext()){
                int i = c2.getInt(0);
                Cursor c3 = db.queryEmp2ndFloor(i);
                if (c3!=null){
                    while(c3.moveToNext()){
                        stringBuilder3.append(c3.getString(1)+"\n");
                    }
                }
            }
            textViewSecondFloorEmployee.setText(stringBuilder3);
        }

//PRINT GYANESH LOCATION
        Cursor c4 = db.queryEmpGyanesh("Gyanesh");
        if (c4!=null){
            c4.moveToNext();
            int i = c4.getInt(c4.getColumnIndex("dno"));
            Cursor c5 = db.queryDepGyanesh(i);
            if (c5!=null){
                c5.moveToNext();
                textViewGyaneshFloor.setText(c5.getString(2));
            }
        }
//EMPLOYEE HIGHEST SALARY
        Cursor c5 = db.queryEmpHighSal();
        if(c5!=null){
            while (c5.moveToNext()){
                stringBuilder4.append(c5.getString(1)+"  "+c5.getString(2)+"\n");
            }
        }
        textViewHighSal.setText(stringBuilder4);

//FIND EMPLOYEE, SALARY>20000 AND EID>2
        Cursor c6 = db.querySalAndID("20000","2");
        if(c6!=null){
            while (c6.moveToNext()){
                stringBuilder6.append(c6.getInt(0)+" "+c6.getString(1)+"  "+c6.getString(2)+"\n");
            }
        }
        textViewSalAndID.setText(stringBuilder6);

//PRINT ONLY EMPLOYEE NAMES
        Cursor c7 = db.queryEmpNameOnly();
        if(c7!=null){
            while (c7.moveToNext()){
                stringBuilder7.append(c7.getString(0)+"\n");
            }
        }
        textViewoEmpNameOnly.setText(stringBuilder7);

//GET EMPLOYEE DETAILS OF LOKESH AND NITESH
        Cursor c8 = db.queryLokeshAndNitesh();
        if(c8!=null){
            while (c8.moveToNext()){
                stringBuilder8.append(c8.getInt(0)+" "+c8.getString(1)+" "+c8.getString(2)+"\n");
            }
        }
        textViewDetailsLokeshAndNitesh.setText(stringBuilder8);

//UPDATE ALL EMPLOYEES SALARY TO DOUBLE, CONDITION: WE DON'T KNOW NO. OF EMPLOYEES AND THEIR SALARY
        Cursor c9 = db.queryEmployee();
        if(c9!=null){
            while(c9.moveToNext()){
                int salary = Integer.parseInt(c9.getString(c9.getColumnIndex("salary")));
                String name = c9.getString(c9.getColumnIndex("name"));
                int doubleSalary = salary*2;
                db.updateSalary(doubleSalary, name);
            }
        }

        Cursor c10 = db.queryEmployee();
        if(c10!=null){
            while(c10.moveToNext()){
                stringBuilder5.append(c10.getInt(0)+"  "+c10.getString(1)+"  "+c10.getString(2)+"\n");
            }
        }
        textViewDoubleSalary.setText(stringBuilder5);
        return v;
    }

}
