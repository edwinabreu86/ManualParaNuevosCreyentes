package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Objects.Verse;

import java.util.ArrayList;
import java.util.List;

public class VersesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "versesDb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favorite";
    private static final String BOOK = "book";
    private static final String VERSE = "verse";
    private static final String CONTENT = "content";

    public VersesDbHelper(Context context) {
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

    public void addVerse(Verse verse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK, verse.getBook());
        values.put(VERSE, verse.getVerse());
        values.put(CONTENT, verse.getContent());

        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + BOOK + "=? AND " + VERSE + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{verse.getBook(), verse.getVerse()});
        if (cursor.getCount() == 0) {
            db.insert(TABLE_NAME, null, values);
        }
        cursor.close();
        db.close();
    }

    void removeVerse(Verse verse) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, BOOK + "=? AND " + VERSE + "=?", new String[]{verse.getBook(), verse.getVerse()});
        db.close();
    }

    public List<Verse> getVersesList() {
        List<Verse> verses = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query(TABLE_NAME, new String[]{BOOK, VERSE, CONTENT}, null, null, null, null, BOOK + " ASC, " + VERSE + " ASC")) {
            cursor.moveToFirst();
            do {
                String book = cursor.getString(cursor.getColumnIndex(BOOK));
                String verse = cursor.getString(cursor.getColumnIndex(VERSE));
                String content = cursor.getString(cursor.getColumnIndex(CONTENT));
                verses.add(new Verse(book, verse, content, true));
            } while (cursor.moveToNext());
        } catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        db.close();
        return verses;
    }

    public boolean ifVerseFavorite(String content) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT "+ CONTENT +" FROM " + TABLE_NAME + " WHERE " + VersesDbHelper.CONTENT + "='" + content +"';", null);

        boolean favorite = cursor.getCount() == 0;
        cursor.close();

        return favorite;
    }
}
