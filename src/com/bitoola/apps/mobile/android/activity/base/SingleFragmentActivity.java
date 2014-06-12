package com.bitoola.apps.mobile.android.activity.base;

import java.util.concurrent.Callable;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bitoola.apps.mobile.android.R;

public abstract class SingleFragmentActivity extends AbstractActivity {

	protected abstract Fragment getFragment();
	
	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		addFragment(getFragmentContainer(), new Callable<Fragment>() {
			@Override
			public Fragment call() throws Exception {
				return getFragment();
			}
		});
	}
	
	@Override
	protected int getActivityView() {
		return R.layout.activity_fragment_1;
	}
	
	protected int getFragmentContainer() {
		return R.id.fragment_1;
	}
}
