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
            text0 = getColumn(Questions.QUESTIONS, 0);
            text1 = getColumn(Questions.QUESTIONS, 1);

            return MainFragment.newInstance(text0, text1);

        } else if (titleId == R.string.bible_books) {
            if (subtitleId == R.string.old_testament) {
                switch (position) {
                    case 0:
                        text0 = getColumn(Books.OT_MOSES, 0);
                        text1 = getColumn(Books.OT_MOSES, 1);
                        text2 = getColumn(Books.OT_MOSES, 2);
                        text3 = getColumn(Books.OT_MOSES, 3);
                        break;
                    case 1:
                        text0 = getColumn(Books.OT_HISTORICAL, 0);
                        text1 = getColumn(Books.OT_HISTORICAL, 1);
                        text2 = getColumn(Books.OT_HISTORICAL, 2);
                        text3 = getColumn(Books.OT_HISTORICAL, 3);
                        break;
                    case 2:
                        text0 = getColumn(Books.OT_POETRY, 0);
                        text1 = getColumn(Books.OT_POETRY, 1);
                        text2 = getColumn(Books.OT_POETRY, 2);
                        text3 = getColumn(Books.OT_POETRY, 3);
                        break;
                    case 3:
                        text0 = getColumn(Books.OT_MAJ_PROPHETS, 0);
                        text1 = getColumn(Books.OT_MAJ_PROPHETS, 1);
                        text2 = getColumn(Books.OT_MAJ_PROPHETS, 2);
                        text3 = getColumn(Books.OT_MAJ_PROPHETS, 3);
                        break;
                    case 4:
                        text0 = getColumn(Books.OT_MIN_PROPHETS, 0);
                        text1 = getColumn(Books.OT_MIN_PROPHETS, 1);
                        text2 = getColumn(Books.OT_MIN_PROPHETS, 2);
                        text3 = getColumn(Books.OT_MIN_PROPHETS, 3);
                        break;
                }
            } else if (subtitleId == R.string.new_testament) {
                switch (position) {
                    case 0:
                        text0 = getColumn(Books.NT_GOSPELS, 0);
                        text1 = getColumn(Books.NT_GOSPELS, 1);
                        text2 = getColumn(Books.NT_GOSPELS, 2);
                        text3 = getColumn(Books.NT_GOSPELS, 3);
                        break;
                    case 1:
                        text0 = getColumn(Books.NT_HISTORICAL, 0);
                        text1 = getColumn(Books.NT_HISTORICAL, 1);
                        text2 = getColumn(Books.NT_HISTORICAL, 2);
                        text3 = getColumn(Books.NT_HISTORICAL, 3);
                        break;
                    case 2:
                        text0 = getColumn(Books.NT_PAULINE, 0);
                        text1 = getColumn(Books.NT_PAULINE, 1);
                        text2 = getColumn(Books.NT_PAULINE, 2);
                        text3 = getColumn(Books.NT_PAULINE, 3);
                        break;
                    case 3:
                        text0 = getColumn(Books.NT_GENERAL, 0);
                        text1 = getColumn(Books.NT_GENERAL, 1);
                        text2 = getColumn(Books.NT_GENERAL, 2);
                        text3 = getColumn(Books.NT_GENERAL, 3);
                        break;
                    case 4:
                        text0 = getColumn(Books.NT_PROPHECY, 0);
                        text1 = getColumn(Books.NT_PROPHECY, 1);
                        text2 = getColumn(Books.NT_PROPHECY, 2);
                        text3 = getColumn(Books.NT_PROPHECY, 3);
                        break;
                }
            }
            return MainFragment.newInstance(text0, text1, text2, text3);

        }else if (titleId == R.string.verses_for_you) {
            if (subtitleId == R.string.old_testament) {
                switch (position) {
                    case 0:
                        text0 = getColumn(Verses.OT_MOSES, 0);
                        text1 = getColumn(Verses.OT_MOSES, 1);
                        text2 = getColumn(Verses.OT_MOSES, 2);
                        break;
                    case 1:
                        text0 = getColumn(Verses.OT_HISTORICAL, 0);
                        text1 = getColumn(Verses.OT_HISTORICAL, 1);
                        text2 = getColumn(Verses.OT_HISTORICAL, 2);
                        break;
                    case 2:
                        text0 = getColumn(Verses.OT_POETRY, 0);
                        text1 = getColumn(Verses.OT_POETRY, 1);
                        text2 = getColumn(Verses.OT_POETRY, 2);
                        break;
                    case 3:
                        text0 = getColumn(Verses.OT_MAJ_PROPHETS, 0);
                        text1 = getColumn(Verses.OT_MAJ_PROPHETS, 1);
                        text2 = getColumn(Verses.OT_MAJ_PROPHETS, 2);
                        break;
                    case 4:
                        text0 = getColumn(Verses.OT_MIN_PROPHETS, 0);
                        text1 = getColumn(Verses.OT_MIN_PROPHETS, 1);
                        text2 = getColumn(Verses.OT_MIN_PROPHETS, 2);
                        break;
                }
            } else if (subtitleId == R.string.new_testament) {
                switch (position) {
                    case 0:
                        text0 = getColumn(Verses.NT_GOSPELS, 0);
                        text1 = getColumn(Verses.NT_GOSPELS, 1);
                        text2 = getColumn(Verses.NT_GOSPELS, 2);
                        break;
                    case 1:
                        text0 = getColumn(Verses.NT_HISTORICAL, 0);
                        text1 = getColumn(Verses.NT_HISTORICAL, 1);
                        text2 = getColumn(Verses.NT_HISTORICAL, 2);
                        break;
                    case 2:
                        text0 = getColumn(Verses.NT_PAULINE, 0);
                        text1 = getColumn(Verses.NT_PAULINE, 1);
                        text2 = getColumn(Verses.NT_PAULINE, 2);
                        break;
                    case 3:
                        text0 = getColumn(Verses.NT_GENERAL, 0);
                        text1 = getColumn(Verses.NT_GENERAL, 1);
                        text2 = getColumn(Verses.NT_GENERAL, 2);
                        break;
                    case 4:
                        text0 = getColumn(Verses.NT_PROPHECY, 0);
                        text1 = getColumn(Verses.NT_PROPHECY, 1);
                        text2 = getColumn(Verses.NT_PROPHECY, 2);
                        break;
                }
            }
            return MainFragment.newInstance(text0, text1, text2);
        }
        return null;
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