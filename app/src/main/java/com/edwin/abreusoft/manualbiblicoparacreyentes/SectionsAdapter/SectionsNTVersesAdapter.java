package com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Columns;
import com.edwin.abreusoft.manualbiblicoparacreyentes.MainFragment;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.VersesText;

public class SectionsNTVersesAdapter extends FragmentStatePagerAdapter {

    private final String[] ntLabels = {"Evangelios", "Históricos", "Cartas paulinas", "Cartas generales", "Proféticos"};

    public SectionsNTVersesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String[] books = null;
        String[] verses = null;
        String[] contents = null;

        switch (position) {
            case 0:
                books = Columns.getColumn(VersesText.NT_GOSPELS, 0);
                verses = Columns.getColumn(VersesText.NT_GOSPELS, 1);
                contents = Columns.getColumn(VersesText.NT_GOSPELS, 2);
                break;
            case 1:
                books = Columns.getColumn(VersesText.NT_HISTORICAL, 0);
                verses = Columns.getColumn(VersesText.NT_HISTORICAL, 1);
                contents = Columns.getColumn(VersesText.NT_HISTORICAL, 2);
                break;
            case 2:
                books = Columns.getColumn(VersesText.NT_PAULINE, 0);
                verses = Columns.getColumn(VersesText.NT_PAULINE, 1);
                contents = Columns.getColumn(VersesText.NT_PAULINE, 2);
                break;
            case 3:
                books = Columns.getColumn(VersesText.NT_GENERAL, 0);
                verses = Columns.getColumn(VersesText.NT_GENERAL, 1);
                contents = Columns.getColumn(VersesText.NT_GENERAL, 2);
                break;
            case 4:
                books = Columns.getColumn(VersesText.NT_PROPHECY, 0);
                verses = Columns.getColumn(VersesText.NT_PROPHECY, 1);
                contents = Columns.getColumn(VersesText.NT_PROPHECY, 2);
                break;
        }
        return MainFragment.newInstance(books, verses, contents);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ntLabels[position];
    }
}
