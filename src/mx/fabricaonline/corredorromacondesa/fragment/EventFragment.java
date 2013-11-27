package mx.fabricaonline.corredorromacondesa.fragment;

import java.util.ArrayList;

import mx.fabricaonline.corredorromacondesa.R;
import mx.fabricaonline.corredorromacondesa.ui.EventInformationActivity;
import mx.fabricaonline.corredorromacondesa.util.EventParser;
import mx.fabricaonline.corredorromacondesa.view.EventCard;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fima.cardsui.objects.Card;
import com.fima.cardsui.objects.Card.OnClickCardListener;
import com.fima.cardsui.views.CardUI;

public class EventFragment extends Fragment {

	private CardUI cardUI;
	private AsyncEventParser asyncEventParser;

	public static EventFragment newInstance(String name) {
		EventFragment eventFragment = new EventFragment();
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
		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			dialog = new ProgressDialog(getActivity());
			dialog.setTitle("Eventos");
			dialog.setMessage("Cargando");
			dialog.show();

		}

		@Override
		protected ArrayList<EventCard> doInBackground(String... params) {
<<<<<<< HEAD
			EventParser parser = new EventParser(getActivity(), params[0]);	
=======
			EventParser parser = new EventParser(getActivity(), params[0]);
//			return parser.jsonParserEvent();
>>>>>>> 1e3c6bfcdbaca8b2863badb94e804ea65e7063cf
			return parser.xmlParserEvent();
		}

		@Override
		protected void onPostExecute(ArrayList<EventCard> result) {
			super.onPostExecute(result);
			if (result != null && dialog.isShowing()) {
				dialog.cancel();
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
