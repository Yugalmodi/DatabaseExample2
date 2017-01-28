package com.techpalle.databaseexample2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DepartmentFragment departmentFragment = new DepartmentFragment();
    EmployeeFragment employeeFragment = new EmployeeFragment();
    DatabaseFragment databaseFragment = new DatabaseFragment();
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, departmentFragment);
        transaction.commit();
    }
    public void gotoEmployeePage(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, employeeFragment);
        transaction.commit();
    }
    public void gotoDepartmentPage(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, departmentFragment);
        transaction.commit();
    }
    public void gotoDatabasePage(){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, databaseFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
