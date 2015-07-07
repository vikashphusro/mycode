package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class used to feedback from user
 * 
 * @author rentongo
 *
 */
public class FeedbackFragment extends Fragment implements OnClickListener,
		OnTouchListener {
	private static final String TAG = "FeedbackFragment";
	private static final FeedbackFragment mFeedbackFragment = new FeedbackFragment();
	private OnMessageListner mOnMessageListner = null;
	private TextView mTxtUser, mTextFeedback1;
	private Button mBtnSubmit;
	private EditText mEdtEmailAdd, mEdtFeedback1, mEdtFeedback2, mEdtFeedback3,
			mEdtFeedback4;

	private FeedbackFragment() {
		// TODO Auto-generated constructor stub
	}

	public static FeedbackFragment getInstance() {
		return mFeedbackFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mOnMessageListner = (OnMessageListner) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMessageListner");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_feedback, container,
				false);
		mBtnSubmit = (Button) view.findViewById(R.id.btnFeedback);
		mTxtUser = (TextView) view.findViewById(R.id.txtFeedbackUser);
		mTextFeedback1 = (TextView) view.findViewById(R.id.txtFeedback1);
		mEdtEmailAdd = (EditText) view.findViewById(R.id.edtFeedBackEmail);
		mEdtFeedback1 = (EditText) view.findViewById(R.id.edtFeedback1);
		mEdtFeedback2 = (EditText) view.findViewById(R.id.edtFeedback2);
		mEdtFeedback3 = (EditText) view.findViewById(R.id.edtFeedback3);
		mEdtFeedback4 = (EditText) view.findViewById(R.id.edtFeedback4);

		mBtnSubmit.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtUser.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTextFeedback1.setTypeface(Constant.getFontSemiBold(getActivity()));
		mEdtEmailAdd.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtFeedback1.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtFeedback2.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtFeedback3.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtFeedback4.setTypeface(Constant.getFontNormal(getActivity()));

		mBtnSubmit.setOnClickListener(this);
		mBtnSubmit.setOnTouchListener(this);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setTitle("Feedback");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFeedback:
			String email = mEdtEmailAdd.getText().toString().trim();
			StringBuilder feedback = new StringBuilder();
			String feedbackText1 = mEdtFeedback1.getText().toString().trim();
			String feedbackText2 = mEdtFeedback2.getText().toString().trim();
			String feedbackText3 = mEdtFeedback3.getText().toString().trim();
			String feedbackText4 = mEdtFeedback4.getText().toString().trim();

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			v.getBackground().setColorFilter(0xf0f47521, Mode.SRC_ATOP);
			v.invalidate();
			break;
		}
		case MotionEvent.ACTION_UP: {
			v.getBackground().clearColorFilter();
			v.invalidate();
			break;
		}
		}
		return false;
	}

}
