package ali.app.doctorappointmentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "App.db";
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "App.db", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //MyDB.execSQL("PRAGMA foreign_keys=ON;");
        MyDB.execSQL("create Table users(id INTEGER primary key AUTOINCREMENT ,username TEXT ,role TEXT ,password TEXT ,email TEXT)");
        MyDB.execSQL("create Table services(id INTEGER primary key AUTOINCREMENT, serviceName TEXT,description TEXT, user_id INTEGER,FOREIGN KEY (user_id) REFERENCES users (id))");
        MyDB.execSQL("CREATE TABLE appointments (id INTEGER primary key autoincrement NOT NULL,date TEXT,time TEXT,servicesId INTEGER,user_id INTEGER,FOREIGN KEY (servicesId) REFERENCES services (id),FOREIGN KEY (user_id) REFERENCES users (id))");


        // MyDB.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
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
            //  contentValues.put("userId", user.getId());
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

    /****
     *inset appointment
     *
     */
     public  void insertAppointment(Appointment appointment,int service,int user)
     {
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("HH:mm");
         ContentValues contentValues=new ContentValues();
         contentValues.put("date", simpleDateFormat.format(appointment.getDate()));
         contentValues.put("time",appointment.getTime());
         contentValues.put("servicesId",service);
         contentValues.put("user_id", user);
         SQLiteDatabase MyDB = this.getWritableDatabase();
         MyDB.insert("appointments", null, contentValues);

     }
    /**
     * insert new service
     */
    public void insertServices(Services services, int user) {
        //
        ContentValues contentValues = new ContentValues();


        contentValues.put("serviceName", services.getName());
        contentValues.put("description", services.getDescription());
        contentValues.put("user_id", user);
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.insert("services", null, contentValues);

    }
    ///get services belognTo doctor

    /**
     * get the services belogto specifies  doctor
     */
    public List<Services> getdoctorservice(int user) {
        String sql = "select id,serviceName,description from services where user_id =" + user;
        sqLiteDatabase = this.getReadableDatabase();
        List<Services> storeServices = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //String id=cursor.getString(0);
                int i = Integer.parseInt(cursor.getString(0));
                String serviceName = cursor.getString(1);
                String description = cursor.getString(2);

                storeServices.add(new Services(i, serviceName, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeServices;
    }

    /**
     * get service
     * */
    public List<Services> getService(int id) {
        String sql = "select id,serviceName,description,user_id from services where id =" + id;
        sqLiteDatabase = this.getReadableDatabase();
        List<Services> storeServices = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //String id=cursor.getString(0);
            //    int i = Integer.parseInt(cursor.getString(0));
                int service_id=cursor.getInt(0);
                String serviceName = cursor.getString(1);
                String description = cursor.getString(2);
                int user_id=cursor.getInt(3);

                storeServices.add(new Services(service_id,serviceName, description,user_id));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeServices;
    }
     /**
      * history service details
      *
      * @return*/
     public String getServicehistory(int id) {
         SQLiteDatabase MyDB = this.getWritableDatabase();
         Cursor cursor = MyDB.rawQuery("Select serviceName  from services where id = ?", new String[]{String.valueOf(id)});
         cursor.moveToFirst();
         String servicename = cursor.getString(0);
         return servicename;
     }


    /**
     * get appointment list
     * */
    public List<Appointment> getappointment(int id) {
        String sql = "select date,time,servicesId from appointments where user_id =" + id;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        sqLiteDatabase = this.getReadableDatabase();
        List<Appointment> storeServices = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //String id=cursor.getString(0);
                //    int i = Integer.parseInt(cursor.getString(0));

             try{
                 Date date = simpleDateFormat.parse(cursor.getString(0));
                String time = cursor.getString(1);
                int service = Integer.parseInt(cursor.getString(2));
                storeServices.add(new Appointment(date,time,service));
             }catch (Exception e){
                 return null;
             }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeServices;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    /**
     * method to delete servcie by id of service
     */

    public void deleteService(int id) {

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("services", "servicesId=?", new String[]{String.valueOf(id)});
    }

    /**
     * update the service data
     */
    public void updateService(Services services) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("serviceName", services.getName());
        contentValues.put("description", services.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update("services", contentValues, "servicesId" + " =?", new String[]{String.valueOf(services.getId())});
    }

    public User checkusernamepassword(String username, String password) {
        User user = null;
        try {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
            if (cursor.moveToFirst()) {

                user = new User();
                user.setUser_id(cursor.getInt(0));

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

    /**
     * method to get list of all users in desc order
     */
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

    /**
     * Create search method by name of service
     */
    public List<Services> search(String name) {
        List<Services> services = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from services  where serviceName  like ?",
                    new String[]{"%" + name + "%"});
            if (cursor.moveToFirst()) {
                services = new ArrayList<Services>();
                do {
                    Services contact = new Services();
                    contact.setId(cursor.getInt(0));
                    contact.setName(cursor.getString(1));
                    contact.setDescription(cursor.getString(2));


                    services.add(contact);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            services = null;
        }
        return services;
    }

    /***
     * get lisf of all services
     */

    public List<Services> getServiceList() {
        String sql = "select * from services";
        sqLiteDatabase = this.getReadableDatabase();
        List<Services> storeServices = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //String id=cursor.getString(0);
                int i=cursor.getInt(0);
                String serviceName = cursor.getString(1);
                String description = cursor.getString(2);

                storeServices.add(new Services(i,serviceName, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeServices;
    }
}
