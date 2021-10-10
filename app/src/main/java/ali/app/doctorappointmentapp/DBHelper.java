package ali.app.doctorappointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(userId INTEGER primary key AUTOINCREMENT ,username TEXT ,isAdmin INTEGER ,password TEXT ,email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB);
    }

    public Boolean insertData(String username,String email,String password,String isAdmin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("isAdmin", isAdmin);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1) return false;
        else
            return true;
    }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username} );
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public int checkusernamepasswordrole(String username, String password) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});

        /*String isAdm = "";
        if(cursor.getColumnIndex("isAdmin") == 1) {
            Log.d("myTag", "checkusernamepasswordrole: User is ADMIN");
            return "Admin";
        }
        if(cursor.getColumnIndex("isAdmin") == 0) {
            Log.d("myTag", "checkusernamepasswordrole: User is not ADMIN");
            return "User";
        }*/

        return cursor.getColumnIndex("isAdmin");


        /*if(cursor.getCount() > 0)
            return true;
        else
            return false;*/
    }

}
