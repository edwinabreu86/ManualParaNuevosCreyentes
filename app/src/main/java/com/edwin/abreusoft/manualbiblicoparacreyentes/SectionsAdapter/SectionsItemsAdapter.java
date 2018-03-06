package com.edwin.abreusoft.manualbiblicoparacreyentes.SectionsAdapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Columns;
import com.edwin.abreusoft.manualbiblicoparacreyentes.MainFragment;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.ItemsText;

public class SectionsItemsAdapter extends FragmentStatePagerAdapter {

    public SectionsItemsAdapter(FragmentManager fm) {
        super(fm);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Fragment getItem(int position) {
        String[] questions = Columns.getColumn(ItemsText.ITEMS, 0);
        String[] answers = Columns.getColumn(ItemsText.ITEMS, 1);

        return MainFragment.newInstance(questions, answers);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
