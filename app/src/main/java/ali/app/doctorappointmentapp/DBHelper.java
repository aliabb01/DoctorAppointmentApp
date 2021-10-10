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

    public static final String DBName = "Login.db";
private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(userId INTEGER primary key AUTOINCREMENT ,username TEXT ,isAdmin TEXT ,password TEXT ,email TEXT)");

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
    public List<Users> getuserList(){
        String sql="select * from  users";
        sqLiteDatabase=this.getReadableDatabase();
        List<Users> storeusers=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                //String id=cursor.getString(0);
                String name=cursor.getString(0);
                String email=cursor.getString(1);
                storeusers.add(new Users(name,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  storeusers;
    }



    public Boolean checkusernamepasswordrole(String isAdmin) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where  isAdmin = ?", new String[] {isAdmin});

        /*String isAdm = "";
        if(cursor.getColumnIndex("isAdmin") == 1) {
            Log.d("myTag", "checkusernamepasswordrole: User is ADMIN");
            return "Admin";
        }
        if(cursor.getColumnIndex("isAdmin") == 0) {
            Log.d("myTag", "checkusernamepasswordrole: User is not ADMIN");
            return "User";
        }*/

       // return cursor.getColumnIndex("isAdmin");


        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}
