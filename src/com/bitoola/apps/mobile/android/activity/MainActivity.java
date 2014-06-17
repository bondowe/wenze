package com.bitoola.apps.mobile.android.activity;

import android.support.v4.app.Fragment;

import com.bitoola.apps.mobile.android.activity.base.SingleFragmentDrawerActivity;
import com.bitoola.apps.mobile.android.fragment.LoginFragment;

public class MainActivity extends SingleFragmentDrawerActivity {

	@Override
	protected Fragment getDefaultFragment() {
		return LoginFragment.newInstance();
	}
	
	@Override 
	protected Integer getDefaultFragmentPosition() {
		return 0;
	}
}
