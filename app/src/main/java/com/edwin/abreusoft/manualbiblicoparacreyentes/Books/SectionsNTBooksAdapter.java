package com.edwin.abreusoft.manualbiblicoparacreyentes.Books;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Columns;
import com.edwin.abreusoft.manualbiblicoparacreyentes.MainFragment;

public class SectionsNTBooksAdapter extends FragmentStatePagerAdapter {

    public SectionsNTBooksAdapter(FragmentManager fm) {
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
                books = Columns.getColumn(BooksText.NT_GOSPELS, 0);
                authors = Columns.getColumn(BooksText.NT_GOSPELS, 1);
                chapters = Columns.getColumn(BooksText.NT_GOSPELS, 2);
                descriptions = Columns.getColumn(BooksText.NT_GOSPELS, 3);
                break;
            case 1:
                books = Columns.getColumn(BooksText.NT_HISTORICAL, 0);
                authors = Columns.getColumn(BooksText.NT_HISTORICAL, 1);
                chapters = Columns.getColumn(BooksText.NT_HISTORICAL, 2);
                descriptions = Columns.getColumn(BooksText.NT_HISTORICAL, 3);
                break;
            case 2:
                books = Columns.getColumn(BooksText.NT_PAULINE, 0);
                authors = Columns.getColumn(BooksText.NT_PAULINE, 1);
                chapters = Columns.getColumn(BooksText.NT_PAULINE, 2);
                descriptions = Columns.getColumn(BooksText.NT_PAULINE, 3);
                break;
            case 3:
                books = Columns.getColumn(BooksText.NT_GENERAL, 0);
                authors = Columns.getColumn(BooksText.NT_GENERAL, 1);
                chapters = Columns.getColumn(BooksText.NT_GENERAL, 2);
                descriptions = Columns.getColumn(BooksText.NT_GENERAL, 3);
                break;
            case 4:
                books = Columns.getColumn(BooksText.NT_PROPHECY, 0);
                authors = Columns.getColumn(BooksText.NT_PROPHECY, 1);
                chapters = Columns.getColumn(BooksText.NT_PROPHECY, 2);
                descriptions = Columns.getColumn(BooksText.NT_PROPHECY, 3);
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
