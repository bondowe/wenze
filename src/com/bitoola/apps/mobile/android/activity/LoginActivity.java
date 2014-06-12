package com.bitoola.apps.mobile.android.activity;

import android.support.v4.app.Fragment;

import com.bitoola.apps.mobile.android.activity.base.SingleFragmentActivity;
import com.bitoola.apps.mobile.android.fragment.LoginFragment;

public class LoginActivity extends SingleFragmentActivity {

	@Override
	protected Fragment getFragment() {
		return LoginFragment.newInstance();
	}
}
