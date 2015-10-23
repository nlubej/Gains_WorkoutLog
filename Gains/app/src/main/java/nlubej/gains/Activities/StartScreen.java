package nlubej.gains.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nlubej.gains.R;

/**
 * Created by nlubej on 19.10.2015.
 */

public class StartScreen extends Fragment {

    public static StartScreen newInstance(String text){
        StartScreen mFragment = new StartScreen();
        Bundle mBundle = new Bundle();
        mBundle.putString("nejc", text);
        mFragment.setArguments(mBundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView mTxtTitle = (TextView) rootView.findViewById(R.id.teeest);
        mTxtTitle.setText("nejc");
        return rootView;
    }
}
