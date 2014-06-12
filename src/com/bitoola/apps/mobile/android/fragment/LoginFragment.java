package com.bitoola.apps.mobile.android.fragment;

import com.bitoola.apps.mobile.android.R;
import com.bitoola.apps.mobile.android.validation.Validation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginFragment extends Fragment {

	private EditText emailEditText;
	private EditText passwordEditText;
	private CheckBox displayPasswordCheckBox;
	private Button loginButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		
		emailEditText = (EditText)view.findViewById(R.id.login_email);
		passwordEditText = (EditText)view.findViewById(R.id.login_password);
		displayPasswordCheckBox = (CheckBox)view.findViewById(R.id.login_display_password);		
		loginButton = (Button)view.findViewById(R.id.login_button);
		
		displayPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				int inputType = InputType.TYPE_CLASS_TEXT;
				
				if(! buttonView.isChecked()) {
					inputType |= InputType.TYPE_TEXT_VARIATION_PASSWORD;
				}
				
				passwordEditText.setInputType(inputType);
			}
		});
		
		loginButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				String email = emailEditText.getText().toString();
				
				if(Validation.isNullOrWhiteSpace(email)) {
					emailEditText.setError(getString(R.string.required_email));
					return;
				}
				
				if(! Validation.isEmail(email)) {
					emailEditText.setError(getString(R.string.invalid_email));
					return;
				}
				
				String password = passwordEditText.getText().toString();
				
				if(Validation.isNullOrWhiteSpace(password)) {
					passwordEditText.setError(getString(R.string.required_password));
					return;
				}
			}
		});
		
		return view;
	}
	
	public static LoginFragment newInstance() {
		
		LoginFragment fragment = new LoginFragment();
		
		return fragment;
	}
}
