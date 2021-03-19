package helloandroid.m2dl.gangd4_android_challenge_mobe.adapter;


import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Score;

public class ScoreListAdapter extends BaseAdapter {

    private List<Score> scoreList;
    private final Context context;

    public ScoreListAdapter(Context context, List<Score> scoreList) {
        this.scoreList = scoreList;
        this.context = context;
    }

    public void setListScore(List<Score> scores) {
        if(scores != null){
            scoreList = scores;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int pos;
        if (i % 2 == 0) {
            pos = i / 2;
        } else {
            pos = (i / 2) + 5;
        }
        Score currentScore = getItem(pos);
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.score_card, null);
            TextView position = view.findViewById(R.id.pos);
            TextView score = view.findViewById(R.id.score);
            score.setText(currentScore.getScore().toString());
            position.setText(pos + 1);
        }
        return view;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Score getItem(int i) {
        return scoreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
