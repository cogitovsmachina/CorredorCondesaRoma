package com.google.condesaroma.util;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.fima.cardsui.objects.Card;
import com.fima.cardsui.objects.Card.OnClickCardListener;
import com.fima.cardsui.views.CardUI;
import com.google.condesaroma.EventInformationActivity;
import com.google.condesaroma.R;
import com.google.condesaroma.view.EventCard;

public class EventFragment extends SherlockFragment {

	private CardUI cardUI;
	// private ArrayList<EventCard> cards;
	private AsyncEventParser asyncEventParser;

	public static EventFragment newInstance(String name) {
		EventFragment eventFragment = new EventFragment();
		// Bundle bundle = new Bundle();
		// bundle.putInt("index", index);
		// bundle.putString("name", name);
		// eventFragment.setArguments(bundle);
		//
		eventFragment.fragmentName = name;

		return eventFragment;

	}

	public static EventFragment newInstance() {
		EventFragment eventFragment = new EventFragment();
		// Bundle bundle = new Bundle();
		// bundle.putInt("index", index);
		// bundle.putString("name", name);
		// eventFragment.setArguments(bundle);
		//

		return eventFragment;

	}

	private String fragmentName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey("name")) {
			fragmentName = savedInstanceState.getString("name");
		}
		setRetainInstance(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.layout_fragment_event,
				container, false);
		cardUI = (CardUI) layout.findViewById(R.id.container_cards);
		return layout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		asyncEventParser = new AsyncEventParser();
		asyncEventParser.execute(fragmentName);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("name", fragmentName);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (asyncEventParser != null) {
			asyncEventParser.cancel(true);
		}
	}

	private class AsyncEventParser extends
			AsyncTask<String, Void, ArrayList<EventCard>> {

		@Override
		protected ArrayList<EventCard> doInBackground(String... params) {
			EventParser parser = new EventParser(getActivity(), params[0]);
			return parser.xmlParserEvent();
		}

		@Override
		protected void onPostExecute(ArrayList<EventCard> result) {
			super.onPostExecute(result);
			if (result != null) {
				cardUI.addCardsArray(result, new OnClickCardListener() {

					@Override
					public void onClickCard(Card card, View view) {
						setCardInformation(card);
					}
				});
			}
		}

	}

	private void setCardInformation(Card card) {
		Bundle bundle = new Bundle();
		String[] cardContent = getCardContent(card);
		bundle.putStringArray("cc", cardContent);
		Intent eventInformation = new Intent(getActivity(),
				EventInformationActivity.class);
		eventInformation.putExtras(bundle);
		// eventInformation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(eventInformation);
	}	

	private String[] getCardContent(Card card) {
		EventCard eventCard = (EventCard) card;
		final String[] cardContent = { eventCard.getName(),
				eventCard.getColony(), eventCard.getAddress(),
				eventCard.getBroker(), eventCard.getLink(),
				eventCard.getPhone(), eventCard.getSchedule(),
				eventCard.getDescription() };
		return cardContent;
	}

}
