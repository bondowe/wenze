package com.bitoola.apps.mobile.android.activity.base;

import java.util.concurrent.Callable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public abstract class AbstractActivity extends ActionBarActivity {

	private static final String LOG_TAG = "com.bitoola.apps.mobile.android.activity.base.AbstractFragmentActivity";

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		setContentView(getActivityView());
	}
	
	protected void addFragment(int container, Callable<Fragment> fragmentCallable) {
		
		Fragment fragment = findFragment(container, fragmentCallable);
		
		if(fragment != null) {
			getSupportFragmentManager().beginTransaction()
									   .add(container, fragment)
									   .commit();
		}
	}
	
	protected void replaceFragment(int container, Callable<Fragment> fragmentCallable) {
		
		Fragment fragment = findFragment(container, fragmentCallable);
		
		replaceFragment(container, fragment);
	}
	
	protected void replaceFragment(int container, Fragment fragment) {
		
		if(fragment != null) {
			getSupportFragmentManager().beginTransaction()
			   						   .replace(container, fragment)
			                           .commit();
		}
	}
	
	private Fragment findFragment(int container, Callable<Fragment> fragmentCallable) {
		
		Fragment fragment = getSupportFragmentManager().findFragmentById(container);
		
		if(fragment == null) {
			try {
				fragment = fragmentCallable.call();
			} catch (Exception e) {
				Log.e(LOG_TAG, "Failed to get the fragment", e);
			}
			return fragment;
		}
		
		return null;
	}
	
	protected abstract int getActivityView();
}
