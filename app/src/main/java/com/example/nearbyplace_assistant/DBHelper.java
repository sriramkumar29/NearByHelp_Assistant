package com.example.nearbyplace_assistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    //Database data
    String[] police_station_name = {"Pavoorchatram Police Station","police station near, Elathur"};

    String[] hospital_name = {"K.AYURVEDA CLINIC","Rithika hospital","Latha Hospital"};

    String[] hospital_phone = {"09842289801","07548862864","04633251428"};

    String[] police_station_phone = {"04633250244","04633250244"};

    double[] hospital_latitude  = {8.913956976636017,8.908969150331805,8.9090};

    double[] hospital_longitude= {77.38568975890614,77.378219907999,77.3785};

    double[] police_station_latitude = {8.9151,9.011582351516221};
    double[] police_station_longitude = {77.3843,77.28630718821213};

    String[] fire_station_name = {"Fire and Rescue Station"};

    String[] railway_name = {"Pavoorchatram RailwayStation"};

    String[] fire_station_phone = {" "};

    String[] railway_phone = {" "};

    double[] fire_station_latitude  = {8.9713};

    double[] fire_station_longitude= {77.4202};

    double[] railway_latitude = {8.9152};
    double[] railway_longitude = {77.3759};

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_HOS = "hos_table";
    private static final String TABLE_POL = "pol_table";
    private static final String TABLE_FIRE = "fire_table";
    private static final String TABLE_RAILWAY = "railway_table";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PHONE_NO = "user_phone_no";
    private static final String COLUMN_USER_PHONE_NO_1 = "user_phone_no1";
    private static final String COLUMN_USER_PHONE_NO_2 = "user_phone_no2";
    private static final String COLUMN_USER_PHONE_NO_3 = "user_phone_no3";

    private static final String COLUMN_HOS_ID = "hos_id";
    private static final String COLUMN_HOS_NAME = "hos_name";
    private static final String COLUMN_HOS_NUM = "hos_num";
    private static final String COLUMN_HOS_LATITUDE = "hos_latitude";
    private static final String COLUMN_HOS_LONGITUDE = "hos_longitude";

    private static final String COLUMN_POL_ID = "pol_id";
    private static final String COLUMN_POL_NAME = "pol_name";
    private static final String COLUMN_POL_NUM = "pol_num";
    private static final String COLUMN_POL_LATITUDE = "pol_latitude";
    private static final String COLUMN_POL_LONGITUDE = "pol_longitude";

    private static final String COLUMN_FIRE_ID = "fire_id";
    private static final String COLUMN_FIRE_NAME = "fire_name";
    private static final String COLUMN_FIRE_NUM = "fire_num";
    private static final String COLUMN_FIRE_LATITUDE = "fire_latitude";
    private static final String COLUMN_FIRE_LONGITUDE = "fire_longitude";

    private static final String COLUMN_RAILWAY_ID = "railway_id";
    private static final String COLUMN_RAILWAY_NAME = "railway_name";
    private static final String COLUMN_RAILWAY_NUM = "railway_num";
    private static final String COLUMN_RAILWAY_LATITUDE = "railway_latitude";
    private static final String COLUMN_RAILWAY_LONGITUDE = "railway_longitude";

    // user table query
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PHONE_NO + " TEXT," + COLUMN_USER_PHONE_NO_1 + " TEXT," +
            COLUMN_USER_PHONE_NO_2 + " TEXT," +
            COLUMN_USER_PHONE_NO_3 + " TEXT)";

    // hospital table query
    private static final String CREATE_HOS_PLACES_TABLE = "CREATE TABLE " + TABLE_HOS + "("
            + COLUMN_HOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_HOS_NAME + " TEXT,"+COLUMN_HOS_NUM + " TEXT,"+
            COLUMN_HOS_LATITUDE + " DOUBLE," +COLUMN_HOS_LONGITUDE + " DOUBLE)";

    private static final String CREATE_POL_PLACES_TABLE = "CREATE TABLE " + TABLE_POL + "("
            + COLUMN_POL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_POL_NAME + " TEXT," + COLUMN_POL_NUM + " BIGINT,"+
            COLUMN_POL_LATITUDE + " DOUBLE," +COLUMN_POL_LONGITUDE + " DOUBLE)";

    private static final String CREATE_FIRE_PLACES_TABLE = "CREATE TABLE " + TABLE_FIRE + "("
            + COLUMN_FIRE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRE_NAME + " TEXT," + COLUMN_FIRE_NUM + " BIGINT,"+
            COLUMN_FIRE_LATITUDE + " DOUBLE," +COLUMN_FIRE_LONGITUDE + " DOUBLE)";

    private static final String CREATE_RAILWAY_PLACES_TABLE = "CREATE TABLE " + TABLE_RAILWAY + "("
            + COLUMN_RAILWAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_RAILWAY_NAME + " TEXT," + COLUMN_RAILWAY_NUM + " BIGINT,"+
            COLUMN_RAILWAY_LATITUDE + " DOUBLE," +COLUMN_RAILWAY_LONGITUDE + " DOUBLE)";



    // drop table sql query
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private static final String DROP_HOS_TABLE = "DROP TABLE IF EXISTS " + TABLE_HOS;
    private static final String DROP_POL_TABLE = "DROP TABLE IF EXISTS " + TABLE_POL;
    private static final String DROP_FIRE_TABLE = "DROP TABLE IF EXISTS " + TABLE_FIRE;
    private static final String DROP_RAILWAY_TABLE = "DROP TABLE IF EXISTS " + TABLE_RAILWAY;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_HOS_PLACES_TABLE);
        db.execSQL(CREATE_POL_PLACES_TABLE);
        db.execSQL(CREATE_FIRE_PLACES_TABLE);
        db.execSQL(CREATE_RAILWAY_PLACES_TABLE);

        //insert hospital data
        for(int i = 0;i<hospital_latitude.length;i++)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_HOS_ID,i);
            values.put(COLUMN_HOS_NAME,hospital_name[i]);
            values.put(COLUMN_HOS_NUM,hospital_phone[i]);
            values.put(COLUMN_HOS_LATITUDE,hospital_latitude[i]);
            values.put(COLUMN_HOS_LONGITUDE,hospital_longitude[i]);

            long insert = db.insert(TABLE_HOS,null,values);
        }


        //insert police station data

        for(int i = 0;i<police_station_latitude.length;i++)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_POL_ID,i);
            values.put(COLUMN_POL_NAME,police_station_name[i]);
            values.put(COLUMN_POL_NUM,police_station_phone[i]);
            values.put(COLUMN_POL_LATITUDE,police_station_latitude[i]);
            values.put(COLUMN_POL_LONGITUDE,police_station_longitude[i]);

            long insert = db.insert(TABLE_POL,null,values);
        }

        for(int i = 0;i<fire_station_latitude.length;i++)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FIRE_ID,i);
            values.put(COLUMN_FIRE_NAME,fire_station_name[i]);
            values.put(COLUMN_FIRE_NUM,fire_station_phone[i]);
            values.put(COLUMN_FIRE_LATITUDE,fire_station_latitude[i]);
            values.put(COLUMN_FIRE_LONGITUDE,fire_station_longitude[i]);

            long insert = db.insert(TABLE_FIRE,null,values);
        }

        for(int i = 0;i<railway_longitude.length;i++)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_RAILWAY_ID,i);
            values.put(COLUMN_RAILWAY_NAME,railway_name[i]);
            values.put(COLUMN_RAILWAY_NUM,railway_phone[i]);
            values.put(COLUMN_RAILWAY_LATITUDE,railway_latitude[i]);
            values.put(COLUMN_RAILWAY_LONGITUDE,railway_longitude[i]);

            long insert = db.insert(TABLE_RAILWAY,null,values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_HOS_TABLE);
        db.execSQL(DROP_POL_TABLE);
        db.execSQL(DROP_FIRE_TABLE);
        db.execSQL(DROP_RAILWAY_TABLE);



        // Create tables again
        onCreate(db);

    }

    //To add users
    public boolean addUser(UserDetail user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE_NO, user.getPhone_no());
        values.put(COLUMN_USER_PHONE_NO_1, user.getPhone_no1());
        values.put(COLUMN_USER_PHONE_NO_2, user.getPhone_no2());
        values.put(COLUMN_USER_PHONE_NO_3, user.getPhone_no3());

        // Inserting Row
        long insert = db.insert(TABLE_USER, null, values);
        db.close();

        if (insert == -1)
            return false;
        else
            return true;
    }

    public boolean updateUser(UserDetail user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PHONE_NO, user.getPhone_no());
        values.put(COLUMN_USER_PHONE_NO_1, user.getPhone_no1());
        values.put(COLUMN_USER_PHONE_NO_2, user.getPhone_no2());
        values.put(COLUMN_USER_PHONE_NO_3, user.getPhone_no3());

        // updating row
        int result = db.update(TABLE_USER, values, COLUMN_USER_ID + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();

        UserDetail u1 = getValue();
        System.out.println(u1.toString());
        System.out.println(result);


        if (result == 0)
            return false;
        else
            return true;
    }

    public UserDetail getValue() {
        UserDetail user = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from " + TABLE_USER;

        Cursor curs = db.rawQuery(query, null);

        if (curs.moveToFirst()) {
            do {
                int userId = curs.getInt(0);
                String userName = curs.getString(1);
                String userEmail = curs.getString(2);
                String userPhno = curs.getString(3);
                String userPhno1 = curs.getString(4);
                String userPhno2 = curs.getString(5);
                String userPhno3 = curs.getString(6);

                user = new UserDetail(userId, userName, userEmail, userPhno, userPhno1, userPhno2, userPhno3);
                System.out.println(user.toString());
            } while (curs.moveToNext());
        }
        curs.close();
        db.close();
        return user;
    }

    public HosInfo getHosValue(int id){
        HosInfo hosInfo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+ TABLE_HOS+" where "+COLUMN_HOS_ID +" = ?" ;
        String[] selectionArgs = {Integer.toString(id)};

        Cursor curs = db.rawQuery(query,selectionArgs);

        if(curs.moveToFirst()){
            do{
                int hosId = curs.getInt(0);
                String hosName = curs.getString(1);
                String hosNum = curs.getString(2);
                double hosLat = curs.getDouble(3);
                double hosLng = curs.getDouble(4);

                hosInfo = new HosInfo(hosId,hosName,hosNum,hosLat,hosLng);
                System.out.println(hosInfo.toString());
            }while (curs.moveToNext());
        }
        curs.close();
        db.close();
        return hosInfo;
    }

    public FireStInfo getFireValue(int id){
        FireStInfo fireInfo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+ TABLE_FIRE+" where "+COLUMN_FIRE_ID +" = ?" ;
        String[] selectionArgs = {Integer.toString(id)};

        Cursor curs = db.rawQuery(query,selectionArgs);

        if(curs.moveToFirst()){
            do{
                int fireId = curs.getInt(0);
                String fireName = curs.getString(1);
                String fireNum = curs.getString(2);
                double fireLat = curs.getDouble(3);
                double fireLng = curs.getDouble(4);

                fireInfo = new FireStInfo(fireId,fireName,fireNum,fireLat,fireLng);
                System.out.println(fireInfo.toString());
            }while (curs.moveToNext());
        }
        curs.close();
        db.close();
        return fireInfo;
    }

    public PolInfo getPolValue(int id){
        PolInfo polInfo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+ TABLE_POL+" where "+COLUMN_POL_ID +" = ?" ;
        String[] selectionArgs = {Integer.toString(id)};

        Cursor curs = db.rawQuery(query,selectionArgs);

        if(curs.moveToFirst()){
            do{
                int polId = curs.getInt(0);
                String polName = curs.getString(1);
                String polNum = curs.getString(2);
                double polLat = curs.getDouble(3);
                double polLng = curs.getDouble(4);

                polInfo = new PolInfo(polId,polName,polNum,polLat,polLng);
                System.out.println(polInfo.toString());
            }while (curs.moveToNext());
        }
        curs.close();
        db.close();
        return polInfo;
    }

    public RailwayInfo getRailwayValue(int id){
        RailwayInfo railwayInfo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * from "+ TABLE_RAILWAY+" where "+COLUMN_RAILWAY_ID +" = ?" ;
        String[] selectionArgs = {Integer.toString(id)};

        Cursor curs = db.rawQuery(query,selectionArgs);

        if(curs.moveToFirst()){
            do{
                int railwayId = curs.getInt(0);
                String railwayName = curs.getString(1);
                String railwayNum = curs.getString(2);
                double railwayLat = curs.getDouble(3);
                double railwayLng = curs.getDouble(4);

                railwayInfo = new RailwayInfo(railwayId,railwayName,railwayNum,railwayLat,railwayLng);
                System.out.println(railwayInfo.toString());
            }while (curs.moveToNext());
        }
        curs.close();
        db.close();
        return railwayInfo;
    }


}

/**
 * This method is to fetch all user and return the list of user records
 *
 * @return list
 */
//    public List<UserDetail> getAllUser() {
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID,
//                COLUMN_USER_EMAIL,
//                COLUMN_USER_NAME,
//                COLUMN_USER_PHONE_NO
//        };
//        // sorting orders
//        String sortOrder =
//                COLUMN_USER_NAME + " ASC";
//        List<UserDetail> userList = new ArrayList<UserDetail>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // query the user table
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,    //columns to return
//                null,        //columns for the WHERE clause
//                null,        //The values for the WHERE clause
//                null,       //group the rows
//                null,       //filter by row groups
//                sortOrder); //The sort order
//
//
//        // Traversing through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                UserDetail user = new UserDetail();
//                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
//                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
//                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
//                user.setPhone_no(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE_NO)));
//                // Adding user record to list
//                userList.add(user);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//        // return user list
//        return userList;
//    }

//    /**
//     * This method to update user record
//     *
//     * @param user
//     */
//
//    /**
//     * This method is to delete user record
//     *
//     * @param user
//     */
//    public void deleteUser(UserDetail user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @return true/false
//     */
//    public boolean checkUser(String email) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?";
//
//        // selection argument
//        String[] selectionArgs = {email};
//
//        // query user table with condition
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                      //filter by row groups
//                null);                      //The sort order
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//
//        if (cursorCount > 0) {
//            return true;
//        }
//
//        return false;
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @param password
//     * @return true/false
//     */
//    public boolean checkUser(String email, String password) {
//
//        // array of columns to fetch
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        // selection criteria
//        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PHONE_NO + " = ?";
//
//        // selection arguments
//        String[] selectionArgs = {email, password};
//
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        Cursor cursor = db.query(TABLE_USER, //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//        int cursorCount = cursor.getCount();
//
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//
//        return false;
//    }

