package com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Columns;
import com.edwin.abreusoft.manualbiblicoparacreyentes.MainFragment;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.VersesText;

public class SectionsOTVersesAdapter extends FragmentStatePagerAdapter {

    private final String[] otLabels = {"Pentateuco", "Históricos", "Poéticos", "Proféticos mayores", "Proféticos menores"};

    public SectionsOTVersesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String[] books = null;
        String[] verses = null;
        String[] contents = null;

        switch (position) {
            case 0:
                books = Columns.getColumn(VersesText.OT_MOSES, 0);
                verses = Columns.getColumn(VersesText.OT_MOSES, 1);
                contents = Columns.getColumn(VersesText.OT_MOSES, 2);
                break;
            case 1:
                books = Columns.getColumn(VersesText.OT_HISTORICAL, 0);
                verses = Columns.getColumn(VersesText.OT_HISTORICAL, 1);
                contents = Columns.getColumn(VersesText.OT_HISTORICAL, 2);
                break;
            case 2:
                books = Columns.getColumn(VersesText.OT_POETRY, 0);
                verses = Columns.getColumn(VersesText.OT_POETRY, 1);
                contents = Columns.getColumn(VersesText.OT_POETRY, 2);
                break;
            case 3:
                books = Columns.getColumn(VersesText.OT_MAJ_PROPHETS, 0);
                verses = Columns.getColumn(VersesText.OT_MAJ_PROPHETS, 1);
                contents = Columns.getColumn(VersesText.OT_MAJ_PROPHETS, 2);
                break;
            case 4:
                books = Columns.getColumn(VersesText.OT_MIN_PROPHETS, 0);
                verses = Columns.getColumn(VersesText.OT_MIN_PROPHETS, 1);
                contents = Columns.getColumn(VersesText.OT_MIN_PROPHETS, 2);
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
        return otLabels[position];
    }
}
