package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class VersesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "versesDb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favorite";
    private static final String BOOK = "book";
    private static final String VERSE = "verse";
    private static final String CONTENT = "content";

    VersesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY, "
                + BOOK + " TEXT, " + VERSE + " TEXT, " + CONTENT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addVerse(Item verse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK, verse.getText1());
        values.put(VERSE, verse.getText2());
        values.put(CONTENT, verse.getText3());

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + BOOK + "=? AND " + VERSE + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{verse.getText1(), verse.getText2()});
        if (cursor.getCount() == 0) {
            db.insert(TABLE_NAME, null, values);
        }
        cursor.close();
        db.close();
    }

    void removeVerse(Item verse) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, BOOK + "=? AND " + VERSE + "=?", new String[]{verse.getText1(), verse.getText2()});
        db.close();
    }

    List<Item> getVersesList() {
        List<Item> verses = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query(TABLE_NAME, new String[]{BOOK, VERSE, CONTENT}, null, null, null, null, null)) {
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                String book = cursor.getString(cursor.getColumnIndex(BOOK));
                String verse = cursor.getString(cursor.getColumnIndex(VERSE));
                String content = cursor.getString(cursor.getColumnIndex(CONTENT));

                verses.add(new Item(book, verse, content, true));
            }
        }
        db.close();
        return verses;
    }
}
