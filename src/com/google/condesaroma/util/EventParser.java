package com.google.condesaroma.util;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.google.condesaroma.view.EventCard;

public class EventParser {

	private InputStream stream;
	private Context myContext;

	public EventParser(Context context, String resource) {
		AssetManager assetManager = context.getResources().getAssets();
		try {
			stream = assetManager.open(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		myContext = context;
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
			//Toast.makeText(myContext, "Error ", Toast.LENGTH_SHORT).show();
		}

		return cards;
	}
}
