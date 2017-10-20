package slf.test.bo;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private int fromDirection;
	private int toDirection;
	private int fromHeight;
	private int toHeight;
	private Vat fromVat;
	private List<Vat> toVats;
	
	public Channel() {}
	
	public Channel(int fromDirection,int fromHeight, Vat fromVat)
	{
		this.fromDirection = fromDirection;
		this.fromHeight = fromHeight;
		this.fromVat = fromVat;
	}
	
	public Channel(int fromDirection,int fromHeight, Vat fromVat,int toDirection, int toHeight,Vat toVat)
	{
		this.fromDirection = fromDirection;
		this.fromHeight = fromHeight;
		this.fromVat = fromVat;
		this.toDirection = toDirection;
		this.toHeight = toHeight;
		if(toVats == null)
		{
			toVats = new ArrayList<Vat>();
		}
		toVats.add(toVat);
	}
	
	public int getFromDirection() {
		return fromDirection;
	}
	public void setFromDirection(int fromDirection) {
		this.fromDirection = fromDirection;
	}
	public int getToDirection() {
		return toDirection;
	}
	public void setToDirection(int toDirection) {
		this.toDirection = toDirection;
	}
	public int getFromHeight() {
		return fromHeight;
	}
	public void setFromHeight(int fromHeight) {
		this.fromHeight = fromHeight;
	}
	public int getToHeight() {
		return toHeight;
	}
	public void setToHeight(int toHeight) {
		this.toHeight = toHeight;
	}
	public Vat getFromVat() {
		return fromVat;
	}
	public void setFromVat(Vat fromVat) {
		this.fromVat = fromVat;
	}

	public List<Vat> getToVats() {
		return toVats;
	}

	public void setToVats(List<Vat> toVats) {
		this.toVats = toVats;
	}
}
