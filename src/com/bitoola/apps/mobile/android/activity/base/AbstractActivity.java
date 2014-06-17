package com.bitoola.apps.mobile.android.activity.base;

import java.util.concurrent.Callable;

import com.bitoola.apps.mobile.android.R;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public abstract class AbstractActivity extends ActionBarActivity {

	private static final String LOG_TAG = "com.bitoola.apps.mobile.android.activity.base.AbstractFragmentActivity";

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		
		setContentView(getActivityView());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		MenuItem item = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView)MenuItemCompat.getActionView(item);
		
		if(searchView != null) {
			SearchManager searchManager = (SearchManager)getSystemService(SEARCH_SERVICE);
			
			searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));	
			/*
			searchView.setOnQueryTextListener(new OnQueryTextListener() {
				
				@Override
				public boolean onQueryTextSubmit(String arg0) {
					// TODO Auto-generated method stub
					return false;
				}
				
				@Override
				public boolean onQueryTextChange(String arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			});*/
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
