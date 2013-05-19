package com.google.condesaroma.view;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.condesaroma.R;

public class EventCard extends CustomCard implements OnClickListener {

	public EventCard() {
	}

	public EventCard(int id, String name, String colony, String address,
			String broker, String link, String phone, String schedule,
			String description) {
		super(id, name, colony, address, broker, link, phone, schedule,
				description);
	}

	@Override
	public View getCardContent(Context context) {
		View container = LayoutInflater.from(context).inflate(
				R.layout.layout_event_card, null);
		((TextView) container.findViewById(R.id.title)).setText(title);
		((TextView) container.findViewById(R.id.broker)).setText(getBroker());
		((TextView) container.findViewById(R.id.card_informacion))
				.setText("Tel: " + getPhone());
		
		((TextView) container.findViewById(R.id.address)).setText(getAddress());
		((ImageView) container.findViewById(R.id.imageView1))
				.setImageResource(R.drawable.logochico);

		container.setOnClickListener(this);

		return container;
	}

	@Override
	public void onClick(View arg0) {
		if (getOnClickCardListener() != null) {
			getOnClickCardListener().onClickCard(EventCard.this, arg0);
		}
	}

}
