package slf.test.bo;

import java.util.List;

public class Vat {

	
	private int waters; //当前水槽总水量
	private int waterHeight; //当前水位高度
	private List<Channel> channels; //管道
	private String vatName; //水槽名称
	
	public Vat() {}
	
	public Vat(String vatName)
	{
		this.vatName = vatName;
	}
	
	public int getWaters() {
		return waters;
	}
	public void setWaters(int waters) {
		this.waters = waters;
	}
	public int getWaterHeight() {
		return waterHeight;
	}
	public void setWaterHeight(int waterHeight) {
		this.waterHeight = waterHeight;
	}
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	public String getVatName() {
		return vatName;
	}
	public void setVatName(String vatName) {
		this.vatName = vatName;
	}
	
}
