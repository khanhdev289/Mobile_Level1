package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.model.SinhVien;

public class DAO {
    private final SQLiteDatabase sqLiteDatabase;

    public DAO(Context context) {
        DBHelper helper = new DBHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<SinhVien> getData(String sql, String... SelectAvgs) {
        ArrayList<SinhVien> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            SinhVien sv = new SinhVien();
            sv.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            sv.setName(cursor.getString(cursor.getColumnIndex("name")));
            sv.setHang(cursor.getString(cursor.getColumnIndex("hang")));
            sv.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndex("year"))));
            sv.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))));
            lst.add(sv);
        }
        return lst;
    }

    public SinhVien getById(int id) {
        Cursor cursor = sqLiteDatabase.query("tbl", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()) {
            return new SinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getDouble(4));
        } else {
            return null;
        }
    }

    public ArrayList<SinhVien> getAllData() {
        String sql = "SELECT * FROM tbl";
        return getData(sql);
    }

    public int insert(SinhVien sv) {
        ContentValues values = new ContentValues();
        values.put("name", sv.getName());
        values.put("hang", sv.getHang());
        values.put("year", sv.getYear());
        values.put("price", sv.getPrice());

        return (int) sqLiteDatabase.insert("tbl", null, values);
    }

    public ArrayList<String> name() {
        String name = "SELECT name FROM tbl";
        return getName(name);
    }


    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            lst.add(name);
        }
        return lst;

    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl", "id = ?", new String[]{String.valueOf(ID)});
    }
}
