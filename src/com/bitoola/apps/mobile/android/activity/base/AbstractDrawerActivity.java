package com.bitoola.apps.mobile.android.activity.base;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bitoola.apps.mobile.android.R;
import com.bitoola.apps.mobile.android.model.adapter.NavigationDrawerListAdapter;
import com.bitoola.apps.mobile.android.model.navigation.NavigationDrawerItem;

public abstract class AbstractDrawerActivity extends AbstractActivity {

	private static final String LOG_TAG = "com.bitoola.apps.mobile.android.activity.base.AbstractDrawerActivity";
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavigationDrawerItem> navDrawerItems;
	private NavigationDrawerListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);

		mTitle = mDrawerTitle = getTitle();
		
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout)findViewById(R.id.navigation_drawer);
		mDrawerList = (ListView) findViewById(R.id.navigation_drawer_slider_list);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		navDrawerItems = new ArrayList<NavigationDrawerItem>();

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[0],
				navMenuIcons.getResourceId(0, -1)));

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[1],
				navMenuIcons.getResourceId(1, -1)));

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[2],
				navMenuIcons.getResourceId(2, -1)));

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[3],
				navMenuIcons.getResourceId(3, -1), true, "22"));

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[4],
				navMenuIcons.getResourceId(4, -1)));

		navDrawerItems.add(new NavigationDrawerItem(navMenuTitles[5],
				navMenuIcons.getResourceId(5, -1), true, "50+"));

		navMenuIcons.recycle();

		adapter = new NavigationDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		final ActionBar actionBar = getSupportActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(
				this, mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.app_name,
				R.string.app_name
		) {
			public void onDrawerClosed(View view) {
				actionBar.setTitle(mTitle);
				ActivityCompat.invalidateOptionsMenu(AbstractDrawerActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				actionBar.setTitle(mDrawerTitle);
				ActivityCompat.invalidateOptionsMenu(AbstractDrawerActivity.this);
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (state == null) {

			Fragment defaultFragment = getDefaultFragment();
			Integer defaultFragmentPosition = getDefaultFragmentPosition();
			
			if(defaultFragment != null || defaultFragmentPosition != null) {
				displayView(defaultFragment, defaultFragmentPosition);
			}
			else {
				displayView(0);
			}
		}
		
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	protected Fragment getDefaultFragment() {
		return null;
	}
	
	protected Integer getDefaultFragmentPosition() {
		return null;
	}
	
    private class SlideMenuClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	displayView(position);
	    }
    }

    private void displayView(int position) {

        Fragment fragment = null;
        
        switch (position) {
        case 0: fragment = getDefaultFragment(); break;
        case 1: fragment = getDefaultFragment(); break;
        case 2: fragment = getDefaultFragment(); break;
        case 3: fragment = getDefaultFragment(); break;
        case 4: fragment = getDefaultFragment(); break;
        case 5: fragment = getDefaultFragment(); break;
        default: break;
        }

        displayView(fragment, position);
	}
    
    private void displayView(Fragment fragment, Integer position) {
    	
        if (fragment == null) {
            Log.e(LOG_TAG, "Error in creating fragment");
            return;
        }
        
        super.replaceFragment(R.id.navigation_drawer_container, fragment);
        
        if(position != null) {
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);            	
        }
        
        if(mDrawerLayout.isDrawerOpen(mDrawerList)) {
        	mDrawerLayout.closeDrawer(mDrawerList);
        }
	}
}
