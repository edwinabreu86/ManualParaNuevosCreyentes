package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.MainFragment;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Columns;

public class SectionsOTBooksAdapter extends FragmentStatePagerAdapter {

    public SectionsOTBooksAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public Fragment getItem(int position) {
        String[] books = null;
        String[] authors = null;
        String[] chapters = null;
        String[] descriptions = null;

        switch (position) {
            case 0:
                books = Columns.getColumn(BooksText.OT_MOSES, 0);
                authors = Columns.getColumn(BooksText.OT_MOSES, 1);
                chapters = Columns.getColumn(BooksText.OT_MOSES, 2);
                descriptions = Columns.getColumn(BooksText.OT_MOSES, 3);
                break;
            case 1:
                books = Columns.getColumn(BooksText.OT_HISTORICAL, 0);
                authors = Columns.getColumn(BooksText.OT_HISTORICAL, 1);
                chapters = Columns.getColumn(BooksText.OT_HISTORICAL, 2);
                descriptions = Columns.getColumn(BooksText.OT_HISTORICAL, 3);
                break;
            case 2:
                books = Columns.getColumn(BooksText.OT_POETRY, 0);
                authors = Columns.getColumn(BooksText.OT_POETRY, 1);
                chapters = Columns.getColumn(BooksText.OT_POETRY, 2);
                descriptions = Columns.getColumn(BooksText.OT_POETRY, 3);
                break;
            case 3:
                books = Columns.getColumn(BooksText.OT_MAJ_PROPHETS, 0);
                authors = Columns.getColumn(BooksText.OT_MAJ_PROPHETS, 1);
                chapters = Columns.getColumn(BooksText.OT_MAJ_PROPHETS, 2);
                descriptions = Columns.getColumn(BooksText.OT_MAJ_PROPHETS, 3);
                break;
            case 4:
                books = Columns.getColumn(BooksText.OT_MIN_PROPHETS, 0);
                authors = Columns.getColumn(BooksText.OT_MIN_PROPHETS, 1);
                chapters = Columns.getColumn(BooksText.OT_MIN_PROPHETS, 2);
                descriptions = Columns.getColumn(BooksText.OT_MIN_PROPHETS, 3);
                break;
        }
        return MainFragment.newInstance(books, authors, chapters, descriptions);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
