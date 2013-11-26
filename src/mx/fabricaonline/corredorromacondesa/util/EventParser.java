package mx.fabricaonline.corredorromacondesa.util;

import java.io.InputStream;
import java.util.ArrayList;

import mx.fabricaonline.corredorromacondesa.view.EventCard;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;
import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;

public class EventParser {

	private InputStream stream;

	public EventParser(Context context, String resource) {
		AssetManager assetManager = context.getResources().getAssets();
		try {
			stream = assetManager.open(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<EventCard> jsonParserEvent(){
		final ArrayList<EventCard> cards = null;
		String uri = "https://script.google.com/macros/s/AKfycbwiR0UCk_w6YlyJnOGCI53SqTsUWHJZ12GeJIF6MAlDUmg-Y062/exec";
		JsonArrayRequest jsonAR = new JsonArrayRequest(uri, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				try {
					cards = new ArrayList<EventCard>();
					int sizeArray = response.length();
					for (int i = 0; i < sizeArray; i++) {
						JSONObject evento = response.getJSONObject(i);
						EventCard ec = new EventCard();
						ec.setName(evento.optString(""));
						ec.setBroker(evento.optString(""));
						ec.setColony(evento.optString(""));
						ec.setDescription(evento.optString(""));
//						ec.setId(evento.optString(""));
						ec.setLink(evento.optString(""));
						ec.setPhone(evento.optString(""));
						ec.setSchedule(evento.optString(""));
						cards.add(ec);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
			}
		});
		
		return cards;
	}

	public ArrayList<EventCard> xmlParserEvent() {

		XmlPullParserFactory factoryParser = null;
		ArrayList<EventCard> cards = null;
		EventCard eventCard = null;

		try {
			cards = new ArrayList<EventCard>();
			factoryParser = XmlPullParserFactory.newInstance();
			factoryParser.setNamespaceAware(true);

			XmlPullParser parser = factoryParser.newPullParser();
			parser.setInput(stream, null);

			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, null, "Eventos");

			while (parser.nextTag() == XmlPullParser.START_TAG) {

				parser.require(XmlPullParser.START_TAG, null, "Evento");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "id");
				Integer id = Integer.valueOf(parser.nextText());
				parser.require(XmlPullParser.END_TAG, null, "id");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "nombre");
				String name = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "nombre");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "colonia");
				String colony = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "colonia");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "direccion");
				String address = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "direccion");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "corredor");
				String broker = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "corredor");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "link");
				String link = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "link");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "telefono");
				String phone = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "telefono");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "horario");
				String schedule = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "horario");

				parser.nextTag();
				parser.require(XmlPullParser.START_TAG, null, "descripcion");
				String description = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "descripcion");

				parser.nextTag();
				parser.require(XmlPullParser.END_TAG, null, "Evento");
				eventCard = new EventCard(id, name, colony, address, broker,
						link, phone, schedule, description);
				cards.add(eventCard);
			}
			parser.require(XmlPullParser.END_TAG, null, "Eventos");
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return cards;
	}
}
