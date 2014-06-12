package com.bitoola.apps.mobile.android.activity.base;

import java.util.concurrent.Callable;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bitoola.apps.mobile.android.R;

public abstract class TwoFragmentsActivity extends AbstractActivity {

	protected abstract Fragment getFirstFragment();
	protected abstract Fragment getSecondFragment();
	
	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		addFragment(getFirstFragmentContainer(), new Callable<Fragment>() {
			@Override
			public Fragment call() throws Exception {
				return getFirstFragment();
			}
		});
		
		addFragment(getSecondFragmentContainer(), new Callable<Fragment>() {
			@Override
			public Fragment call() throws Exception {
				return getSecondFragment();
			}
		});
	}
	
	@Override
	protected int getActivityView() {
		return R.layout.activity_fragment_2;
	}
	
	protected int getFirstFragmentContainer() {
		return R.id.fragment_2_first;
	}
	
	protected int getSecondFragmentContainer() {
		return R.id.fragment_2_second;
	}
}
