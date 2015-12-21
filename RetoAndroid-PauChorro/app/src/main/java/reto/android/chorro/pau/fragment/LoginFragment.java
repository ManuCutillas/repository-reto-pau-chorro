package reto.android.chorro.pau.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;

import reto.android.chorro.pau.DropboxActivity;
import reto.android.chorro.pau.R;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment.class";

    private Button mBtnLogin;
    private boolean mLoggedIn = false;
    private DropboxActivity mActivity;

    private static final boolean USE_OAUTH1 = false;

    private DropboxAPI<AndroidAuthSession> mApi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof DropboxActivity) this.mActivity = (DropboxActivity) context;

        if(mActivity != null) mApi = this.mActivity.getmApi();
    }


    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflador.inflate(R.layout.fragment_login,
                container, false);

        Log.d(TAG, "NOT logged");

        mBtnLogin = (Button) view.findViewById(R.id.btnLogin);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // This logs you out if you're logged in, or vice versa
                if (mLoggedIn) {
                    //mActivity.logOut();

                    Log.d(TAG, "Logged");

                } else {
                    // Start the remote authentication
                    if (USE_OAUTH1) {
                        mApi.getSession().startAuthentication(getActivity());
                    } else {
                        mApi.getSession().startOAuth2Authentication(getActivity());
                    }
                }
            }
        });


        return view;
    }
}
