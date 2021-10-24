package ali.app.doctorappointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "App.db";
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "App.db", null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(userId INTEGER primary key AUTOINCREMENT ,username TEXT ,role TEXT ,password TEXT ,email TEXT)");
        MyDB.execSQL("create Table services(servicesId INTEGER primary key AUTOINCREMENT ,serviceName TEXT ,description TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists services");
        onCreate(MyDB);
    }

    public Boolean insertData(User user) {
        Boolean res = true;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //  contentValues.put("userId", user.getUserId());
            contentValues.put("username", user.getName());
            contentValues.put("role", user.getRole());
            contentValues.put("password", user.getPassword());
            contentValues.put("email", user.getEmail());

            res = MyDB.insert("users", null, contentValues) > 0;
        } catch (Exception e) {
            res = false;
        }
        return res;

    }

    public Boolean insertServices(String serviceName,String description) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("serviceName", serviceName);
        contentValues.put("description", description);
        long result = MyDB.insert("services", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public User checkusernamepassword(String username, String password) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
            if (cursor.moveToFirst()) {
                user = new User();
                //        user.setUserId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setRole(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setEmail(cursor.getString(4));
            }
        } catch (Exception e) {
            user = null;
        }

        return user;

    }

    public String checkusernamepasswordrole(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        cursor.moveToFirst();
        String role = cursor.getString(2);

        Log.d("myTag", "checkusernamepasswordrole: User is: " + role);

        return role;
    }


    public List<User> getuserList() {
        String sql = "SELECT * FROM users ORDER BY username DESC";
        sqLiteDatabase = this.getReadableDatabase();
        List<User> storeusers = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //String id=cursor.getString(0);
                String name = cursor.getString(0);
                String email = cursor.getString(1);
                storeusers.add(new User(name, email));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeusers;
    }

    public List<Services> getServiceList(){
        String sql="select * from services";
        sqLiteDatabase=this.getReadableDatabase();
        List<Services> storeServices=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                //String id=cursor.getString(0);
                String serviceName=cursor.getString(0);
                String description=cursor.getString(1);
                storeServices.add(new Services(serviceName,description));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  storeServices;
    }
}
