package com.techpalle.databaseexample2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL on 28-Dec-16.
 */
public class MyDB {
    SQLiteDatabase sqLiteDatabase;
    MyHelper myHelper;

    public MyDB(Context c){
        myHelper = new MyHelper(c, "login.db", null, 1);
    }

    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    public void insertValues(String name, String salary, int dno){
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("salary", salary);
        cv.put("dno", dno);
        sqLiteDatabase.insert("employee", null, cv);
    }
    public void insertDepartmentValues(String department, String floor){
        ContentValues cv = new ContentValues();
        cv.put("department", department);
        cv.put("floor", floor);
        sqLiteDatabase.insert("department", null, cv);
    }

//UPDATE ALL EMPLOYEES SALARY TO DOUBLE, CONDITION: WE DON'T KNOW NO. OF EMPLOYEES AND THEIR SALARY
    public void updateSalary(int i,String j){
        ContentValues cv = new ContentValues();
        cv.put("salary", i);
        sqLiteDatabase.update("employee",cv, "name =?", new String[]{j});
    }

//ENTER EMPLOYEE DEPARTMENT ID
    public Cursor queryDep(String dep){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("department", null, "department=?", new String[]{dep}, null, null, null);
        return cursor;
    }

//PRINT TABLE OF EMPLOYEE
    public Cursor queryEmployee(){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, null, null, null, null, null);
        return cursor1;
    }

//PRINT TABLE OF DEPARTMENT
    public Cursor queryDepartment(){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("department", null, null, null, null, null, null);
        return cursor1;
    }

//PRINT EMPLOYEES OF 2ND FLOOR
    public Cursor queryDep2ndFloor(String floor){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("department", null, "floor=?", new String[]{floor}, null, null, null);
        return cursor;
    }

    public Cursor queryEmp2ndFloor(int i){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, "dno ="+i, null, null, null, null);
        return cursor1;
    }

//PRINT GYANESH LOCATION
    public Cursor queryEmpGyanesh(String name){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, "name =?", new String []{name}, null, null, null);
        return cursor1;
    }

    public Cursor queryDepGyanesh(int id){
        Cursor cursor = null;
        cursor = sqLiteDatabase.query("department", null, "_id ="+id,null, null, null, null);
        return cursor;
    }

//EMPLOYEE HIGHEST SALARY
    public Cursor queryEmpHighSal(){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, null,null, null, null, "salary DESC");
        return cursor1;
    }

//FIND EMPLOYEE, SALARY>20000 AND EID>2
    public Cursor querySalAndID(String salary, String id){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, "salary >? AND _id >?",new String[]{salary, id}, null, null, null);
        return cursor1;
    }
//PRINT ONLY EMPLOYEE NAMES
    public Cursor queryEmpNameOnly(){
    Cursor cursor1 = null;
    cursor1 = sqLiteDatabase.query("employee", new String[]{"name"}, null,null, null, null, null);
    return cursor1;
    }
//GET EMPLOYEE DETAILS OF LOKESH AND NITESH
    public Cursor queryLokeshAndNitesh(){
        Cursor cursor1 = null;
        cursor1 = sqLiteDatabase.query("employee", null, "name =? OR name =?",new String[]{"Lokesh", "Nitesh"}, null, null, null);
        return cursor1;
    }

    public void close(){
        sqLiteDatabase.close();
    }

    private class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table employee(_id integer primary key, name text, salary text, dno text);");
            sqLiteDatabase.execSQL("create table department(_id integer primary key, department text, floor text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
