package com.example.studentapp.DB;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.studentapp.Module.Student_list_Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DBHelper extends SQLiteOpenHelper {
    public static final String COLUMN_STUDENT_NAME="student_name";
    private static final String COLUMN_IMAGE="image_data";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME1="VoiceNotesDB";
    private static final String TABLE_NOTES="voice_notes";
    private static final String COLUMN_TEXT="text";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ID="id";
    private static final String DATABASE_NAME="StudentAttendance.db";
    private static final String TABLE_NAME="attendance";
    public static final String COLUMN_STUDENT_ID="student_id";
    private static final String TABLE_CHAT="chat_history";
    private static final String COLUMN_QUERY="query";
    private static final String COLUMN_RESPONSE="response";

    public DBHelper(Context context) {
        super(context , "Student_App_V0.0.1" , null , 8);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        // User table
        DB.execSQL("Create Table Users(id INTEGER PRIMARY KEY AUTOINCREMENT, vUser_Name TEXT default NULL, vUser_Number TEXT default NULL, vUser_Password TEXT default NULL)");
        // List
        DB.execSQL("Create Table Student_List(Student_id INTEGER PRIMARY KEY AUTOINCREMENT,vStudent_Name TEXT default NULL, " +
                "vStudent_Reg_Number TEXT default NULL," +
                "vStudent_Number TEXT default NULL, vStudent_Email TEXT default NULL , vStudent_Aadher TEXT default NULL , " +
                "vStudent_Address TEXT default NULL ,vStudent_DOB TEXT default NULL, vStudent_Education TEXT default NULL, " +
                "vStudent_10_Marks TEXT default NULL, vStudent_10_Percentage TEXT default NULL, " +
                "vStudent_12_Marks TEXT default NULL, vStudent_12_Percentage TEXT default NULL, " +
                "vStudent_Sem_1 TEXT default NULL, vStudent_Sem_2 TEXT default NULL, " +
                "vStudent_Sem_3 TEXT default NULL, vStudent_Sem_4 TEXT default NULL, " +
                "vStudent_Sem_5 TEXT default NULL, vStudent_Sem_6 TEXT default NULL, " +
                "vStudent_Sem_OverAll TEXT default NULL, vUser_Id TEXT default NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    // Login  Page
    public boolean InsertUsers(String vUser_Name , String vUser_Number , String vUser_Password) {

        SQLiteDatabase DBInsert=this.getWritableDatabase( );
        ContentValues insertvalue=new ContentValues( );
        insertvalue.put("vUser_Name" , vUser_Name);
        insertvalue.put("vUser_Number" , vUser_Number);
        insertvalue.put("vUser_Password" , vUser_Password);

        Log.e(TAG , "InsertUsers: " + insertvalue);
        long insetResult=DBInsert.insert("Users" , null , insertvalue);
        Log.e(TAG , "InsertUsersData: " + insetResult);

        return false;
    }

    public Boolean check_username_UserTable(String vUser_Name) {
        Log.e(TAG , "check_username_UserTable: " + vUser_Name);
        try {
            SQLiteDatabase check=this.getReadableDatabase( );
            Cursor cursor=check.rawQuery("Select * from Users where vUser_Name=?" , new String[] { vUser_Name });
            int count=cursor.getCount( );
            Log.e(TAG , "check_username_UserTable: count : " + count);
            cursor.close( );
            if (count > 0) {
                Log.e("Username Already Exists" , "Already Exit");
                return true;
            } else {
                Log.e("Else : Username Already Exists" , "Already Exit");
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception occured" , "Exception occured " + e);
            return false;
        }
    }

    public Integer check_username_password_UserTable(String vUser_Name) {
        Log.e(TAG , "check_username_UserTable: " + vUser_Name + " pass : ");
        try {
            SQLiteDatabase check=this.getReadableDatabase( );
            Cursor cursor=check.rawQuery("Select * from Users where vUser_Name=? " , new String[] { vUser_Name });
            if (cursor.moveToFirst( )) {
                Log.e("Logged In" , "Success");
                int user_id=cursor.getInt(0);
                String vUser_Name1=cursor.getString(1);
                Log.e(TAG , "check_username_password_UserTable: user_id : " + user_id + "  " + vUser_Name + " ");

                return user_id;
            } else {
                Log.e("Else : Invalid Username and Password" , "Already Exit");
                return 0;
            }
        } catch (Exception e) {
            Log.e("Exception User_id" , "Exception occured " + e);
            return 0;
        }
    }

    public Cursor get_vUsername_Password_from_Usertable(int iUser_id) {
        SQLiteDatabase DB=this.getWritableDatabase( );
        Cursor cursor=DB.rawQuery("Select vUser_Name,vUser_Password from Users WHERE id = '" + iUser_id + "' " , null);
        Log.e(TAG , "get)vUsername_ : " + cursor);
        return cursor;
    }


    // Student List
    public boolean InsertStudent(String vStudent_Name , String vStudent_Reg_Number , String vStudent_Number ,
                                 String vStudent_Email , String vStudent_Aadher , String vStudent_Address ,
                                 String vStudent_DOB , String vStudent_Education , String vStudent_10_Marks ,
                                 String vStudent_10_Percentage , String vStudent_12_Marks , String vStudent_12_Percentage ,
                                 String vStudent_Sem_1 , String vStudent_Sem_2 , String vStudent_Sem_3 ,
                                 String vStudent_Sem_4 , String vStudent_Sem_5 , String vStudent_Sem_6 ,
                                 String vStudent_Sem_OverAll , String vUser_Id) {

        SQLiteDatabase DBInsert=this.getWritableDatabase( );
        ContentValues insertvalue=new ContentValues( );
        insertvalue.put("vStudent_Name" , vStudent_Name);
        insertvalue.put("vStudent_Reg_Number" , vStudent_Reg_Number);
        insertvalue.put("vStudent_Number" , vStudent_Number);
        insertvalue.put("vStudent_Email" , vStudent_Email);
        insertvalue.put("vStudent_Aadher" , vStudent_Aadher);
        insertvalue.put("vStudent_Address" , vStudent_Address);
        insertvalue.put("vStudent_DOB" , vStudent_DOB);
        insertvalue.put("vStudent_Education" , vStudent_Education);
        insertvalue.put("vStudent_10_Marks" , vStudent_10_Marks);
        insertvalue.put("vStudent_10_Percentage" , vStudent_10_Percentage);
        insertvalue.put("vStudent_12_Marks" , vStudent_12_Marks);
        insertvalue.put("vStudent_12_Percentage" , vStudent_12_Percentage);
        insertvalue.put("vStudent_Sem_1" , vStudent_Sem_1);
        insertvalue.put("vStudent_Sem_2" , vStudent_Sem_2);
        insertvalue.put("vStudent_Sem_3" , vStudent_Sem_3);
        insertvalue.put("vStudent_Sem_4" , vStudent_Sem_4);
        insertvalue.put("vStudent_Sem_5" , vStudent_Sem_5);
        insertvalue.put("vStudent_Sem_6" , vStudent_Sem_6);
        insertvalue.put("vStudent_Sem_OverAll" , vStudent_Sem_OverAll);
        insertvalue.put("vUser_Id" , vUser_Id);

        Log.e(TAG , "InsertUsers: " + insertvalue);
        long insetResult=DBInsert.insert("Student_List" , null , insertvalue);
        Log.e(TAG , "InsertUsersData: " + insetResult);

        return true;
    }

    public void Delete_Student_List(String vStudent_Name) {
        SQLiteDatabase DB=this.getWritableDatabase( );
        DB.execSQL("DELETE FROM Student_List WHERE vStudent_Name = '" + vStudent_Name + "'");
    }

    public ArrayList<Student_list_Items> Read_Student_List(int vUser_Id) {
        SQLiteDatabase DBread=this.getReadableDatabase( );

        Cursor readdata;
        readdata=DBread.rawQuery("Select * from Student_List WHERE vUser_Id = '" + vUser_Id + "'" , null);

        ArrayList<Student_list_Items> student_list_items=new ArrayList<>( );

        if (readdata.moveToFirst( )) {
            do {
                student_list_items.add(new Student_list_Items(
                        readdata.getString(0) ,
                        readdata.getString(1) ,
                        readdata.getString(2) ,
                        readdata.getString(3) ,
                        readdata.getString(4) ,
                        readdata.getString(5) ,
                        readdata.getString(6) ,
                        readdata.getString(7) ,
                        readdata.getString(8) ,
                        readdata.getString(9) ,
                        readdata.getString(10) ,
                        readdata.getString(11) ,
                        readdata.getString(12) ,
                        readdata.getString(13) ,
                        readdata.getString(14) ,
                        readdata.getString(15) ,
                        readdata.getString(16) ,
                        readdata.getString(17) ,
                        readdata.getString(18) ,
                        readdata.getString(19)
                ));

            } while (readdata.moveToNext( ));

            Log.e(TAG , "ReadCart_railcar__Get_ID: " + student_list_items);
        }
        return student_list_items;

    }

    public boolean UpdateStudent(String vStudent_ID, String vStudent_Name, String vStudent_Reg_Number, String vStudent_Number,
                                 String vStudent_Email, String vStudent_Aadher, String vStudent_Address,
                                 String vStudent_DOB, String vStudent_Education, String vStudent_10_Marks,
                                 String vStudent_10_Percentage, String vStudent_12_Marks, String vStudent_12_Percentage,
                                 String vStudent_Sem_1, String vStudent_Sem_2, String vStudent_Sem_3,
                                 String vStudent_Sem_4, String vStudent_Sem_5, String vStudent_Sem_6,
                                 String vStudent_Sem_OverAll, String vUser_Id) {
        SQLiteDatabase DBUpdate = this.getWritableDatabase();
        ContentValues updatevalue = new ContentValues();

        updatevalue.put("vStudent_Name", vStudent_Name);
        updatevalue.put("vStudent_Reg_Number", vStudent_Reg_Number);
        updatevalue.put("vStudent_Number", vStudent_Number);
        updatevalue.put("vStudent_Email", vStudent_Email);
        updatevalue.put("vStudent_Aadher", vStudent_Aadher);
        updatevalue.put("vStudent_Address", vStudent_Address);
        updatevalue.put("vStudent_DOB", vStudent_DOB);
        updatevalue.put("vStudent_Education", vStudent_Education);
        updatevalue.put("vStudent_10_Marks", vStudent_10_Marks);
        updatevalue.put("vStudent_10_Percentage", vStudent_10_Percentage);
        updatevalue.put("vStudent_12_Marks", vStudent_12_Marks);
        updatevalue.put("vStudent_12_Percentage", vStudent_12_Percentage);
        updatevalue.put("vStudent_Sem_1", vStudent_Sem_1);
        updatevalue.put("vStudent_Sem_2", vStudent_Sem_2);
        updatevalue.put("vStudent_Sem_3", vStudent_Sem_3);
        updatevalue.put("vStudent_Sem_4", vStudent_Sem_4);
        updatevalue.put("vStudent_Sem_5", vStudent_Sem_5);
        updatevalue.put("vStudent_Sem_6", vStudent_Sem_6);
        updatevalue.put("vStudent_Sem_OverAll", vStudent_Sem_OverAll);

        int updateResult = DBUpdate.update("Student_List", updatevalue, "Student_id=? AND vUser_Id=?", new String[]{vStudent_ID, vUser_Id});
        DBUpdate.close();

        return updateResult > 0;
    }
    public void insertImage(byte[] imageData) {
        SQLiteDatabase db=this.getWritableDatabase( );
        ContentValues values=new ContentValues( );
        values.put(COLUMN_IMAGE , imageData);
        db.insert(TABLE_NAME , null , values);
        db.close( );
    }
    
    public byte[] getLastImage() {
        SQLiteDatabase db=this.getReadableDatabase( );
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1" , null);
        if (cursor.moveToFirst( )) {
            byte[] imageData=cursor.getBlob(1);
            cursor.close( );
            return imageData;
        }
        cursor.close( );
        return null;
    }

    public void insertVoiceNote(String text) {
        SQLiteDatabase db=this.getWritableDatabase( );
        ContentValues values=new ContentValues( );
        values.put(COLUMN_TEXT , text);
        db.insert(TABLE_NOTES , null , values);
        db.close( );
    }

    public String getLastVoiceNote() {
        SQLiteDatabase db=this.getReadableDatabase( );
        Cursor cursor=db.rawQuery("SELECT " + COLUMN_TEXT + " FROM " + TABLE_NOTES + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1" , null);
        if (cursor.moveToFirst( )) {
            String note=cursor.getString(0);
            cursor.close( );
            return note;
        }
        cursor.close( );
        return null;
    }
}




