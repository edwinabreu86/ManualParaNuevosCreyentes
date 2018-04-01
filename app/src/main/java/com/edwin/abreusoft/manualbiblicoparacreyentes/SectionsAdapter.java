package com.edwin.abreusoft.manualbiblicoparacreyentes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.Books;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.Questions;
import com.edwin.abreusoft.manualbiblicoparacreyentes.Texts.Verses;

class SectionsAdapter extends FragmentStatePagerAdapter {

    private final String[] otLabels = {"Pentateuco", "Históricos", "Poéticos", "Proféticos mayores", "Proféticos menores"};
    private final String[] ntLabels = {"Evangelios", "Históricos", "Cartas paulinas", "Cartas generales", "Proféticos"};

    private final int titleId;
    private final int subtitleId;

    private String[] text0, text1, text2, text3;
    private final String[][] texts = new String[][]{text0, text1, text2, text3};

    private String[] getColumn(String[][] items, int index) {
        String[] column = new String[items.length];

        for (int i = 0; i < column.length; i++) {
            column[i] = items[i][index];
        }
        return column;
    }

    SectionsAdapter(FragmentManager fm, int titleId, int subtitleId) {
        super(fm);
        this.titleId = titleId;
        this.subtitleId = subtitleId;
    }

    @Override
    public Fragment getItem(int position) {
        if (titleId == R.string.general_questions) {
            for (int n = 0; n < 2; n++) {
                texts[n] = getColumn(Questions.QUESTIONS, n);
            }
        } else if (titleId == R.string.bible_books) {
            for (int n = 0; n < 4; n++) {
                switch (subtitleId) {
                    case R.string.old_testament:
                        if(position == 0) {
                            texts[n] = getColumn(Books.OT_MOSES, n);
                        } else if(position == 1) {
                            texts[n] = getColumn(Books.OT_HISTORICAL, n);
                        } else if(position == 2) {
                            texts[n] = getColumn(Books.OT_POETRY, n);
                        } else if(position == 3) {
                            texts[n] = getColumn(Books.OT_MAJ_PROPHETS, n);
                        } else {
                            texts[n] = getColumn(Books.OT_MIN_PROPHETS, n);
                        }
                        break;
                    case R.string.new_testament:
                        if(position == 0) {
                            texts[n] = getColumn(Books.NT_GOSPELS, n);
                        } else if(position == 1) {
                            texts[n] = getColumn(Books.NT_HISTORICAL, n);
                        } else if(position == 2) {
                            texts[n] = getColumn(Books.NT_PAULINE, n);
                        } else if(position == 3) {
                            texts[n] = getColumn(Books.NT_GENERAL, n);
                        } else {
                            texts[n] = getColumn(Books.NT_PROPHECY, n);
                        }
                        break;
                }
            }
        }else if (titleId == R.string.verses_for_you) {
            for (int n = 0; n < 3; n++) {
                switch (subtitleId) {
                    case R.string.old_testament:
                        if(position == 0) {
                            texts[n] = getColumn(Verses.OT_MOSES, n);
                        } else if(position == 1) {
                            texts[n] = getColumn(Verses.OT_HISTORICAL, n);
                        } else if(position == 2) {
                            texts[n] = getColumn(Verses.OT_POETRY, n);
                        } else if(position == 3) {
                            texts[n] = getColumn(Verses.OT_MAJ_PROPHETS, n);
                        } else {
                            texts[n] = getColumn(Verses.OT_MIN_PROPHETS, n);
                        }
                        break;
                    case R.string.new_testament:
                        if(position == 0) {
                            texts[n] = getColumn(Verses.NT_GOSPELS, n);
                        } else if(position == 1) {
                            texts[n] = getColumn(Verses.NT_HISTORICAL, n);
                        } else if(position == 2) {
                            texts[n] = getColumn(Verses.NT_PAULINE, n);
                        } else if(position == 3) {
                            texts[n] = getColumn(Verses.NT_GENERAL, n);
                        } else {
                            texts[n] = getColumn(Verses.NT_PROPHECY, n);
                        }
                        break;
                }
            }
        }
        text0 = texts[0];
        text1 = texts[1];
        text2 = texts[2];
        text3 = texts[3];

        return MainFragment.newInstance(text0, text1, text2, text3);
    }

    @Override
    public int getCount() {
        return (titleId == R.string.general_questions) ? 1 : otLabels.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(subtitleId == R.string.old_testament) {
            return otLabels[position];
        } else if(subtitleId == R.string.new_testament) {
            return ntLabels[position];
        } else {
            return null;
        }
    }
}