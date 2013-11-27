package mx.fabricaonline.corredorromacondesa.view;

import mx.fabricaonline.corredorromacondesa.R;

import com.fima.cardsui.objects.Card;

public abstract class CustomCard extends Card {

	private int id;
	private String name;
	private String colony;
	private String address;
	private String broker;
	private String link;
	private String phone;
	private String schedule;
	private String description;
	private String category;

	public CustomCard() {
	}

	public CustomCard(int id, String name, String colony, String address,
			String broker, String link, String phone, String schedule,
			String description) {
		super(name, description, R.drawable.ic_launcher);
		this.id = id;
		this.name = name;
		this.colony = colony;
		this.address = address;
		this.broker = broker;
		this.link = link;
		this.phone = phone;
		this.schedule = schedule;
		this.description = description;
	}
	
	public CustomCard(int id, String name, String colony, String address,
			String broker, String link, String phone, String schedule,
			String description, String category) {
		super(name, description, R.drawable.ic_launcher);
		this.id = id;
		this.name = name;
		this.colony = colony;
		this.address = address;
		this.broker = broker;
		this.link = link;
		this.phone = phone;
		this.schedule = schedule;
		this.description = description;
		this.setCategory(category);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
