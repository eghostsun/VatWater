package slf.test.service;

import java.util.List;
import slf.test.VatContext;
import slf.test.WaterVatException;
import slf.test.bo.Channel;
import slf.test.bo.Vat;

public class WaterPipe implements IWaterPipe {

	private final int increase_water = 20; //满20滴水 height+1
	@Override
	public void pourWater(Vat vat,int water) throws WaterVatException {
		if(vat.getWaterHeight() == VatContext.VAT_MAX_HEIGHT) //水槽是否水满
		{
			System.out.println("当前水槽" + vat.getVatName() + "已满");
			VatContext.isFull = true;
			return;
		}
		try {
			//获取一个水位线以上最近的水管
			Channel channel = selectNearestChannel(vat);
			//是否底部水管
			Vat toVat = null;
			if(channel.getFromDirection() == VatContext.VAT_BOTTOM)
			{
				toVat = getToVat(channel);
			}else {
				incrementWater(vat,water);
				if(vat.getWaterHeight() <= channel.getToHeight())
				{
					toVat = getToVat(channel);
				}
			}
			if(toVat != null)
			{
				pourWater(toVat,water);
			}
		} catch (Exception e) {
			throw new WaterVatException("水槽选择异常",e);
		}
		
	}
	/**
	 * 获取一个水位线以上最近的水管
	 * @param vat
	 * @return
	 */
	private Channel selectNearestChannel(Vat vat)
	{
		List<Channel> channels = vat.getChannels();
		Channel temp = null;
		for(Channel channel : channels)
		{
			if(channel.getFromVat().getVatName().equals(vat.getVatName()) &&
					channel.getFromHeight() > vat.getWaterHeight()) //出水口水管, 水管水位线高于当前水槽的水位线
			{
				if(temp == null)
				{
					temp = channel;
				}else {
					//当上一个水管的水位线高度大于当前水管的水位线高度，保存以当前水管为准
					if(temp.getFromHeight() > channel.getFromHeight())
					{
						temp = channel;
					}
				}
			}
		}
		return temp;
	}

	/**
	 * 增加水位
	 * @param vat
	 * @param water
	 */
	private void incrementWater(Vat vat, int water)
	{
		int total = vat.getWaters() + water; //总水量
		int num = total / increase_water;
		if(num > 0)
		{
			vat.setWaterHeight(num);
		}else {
			vat.setWaterHeight(1);
		}
		vat.setWaters(total);
	}
	
	/**
	 * 获取此水管的出水口水槽
	 * @param channel
	 * @return
	 */
	private Vat getToVat(Channel channel)
	{
		List<Vat> toVats = channel.getToVats();
		Vat temp = null;
		for(Vat toVat : toVats)
		{
			if(temp == null)
			{
				temp = toVat;
			}else{
				Channel tempOut = selectUsefulIn(temp);
				int tempwh = temp.getWaterHeight();
				int $tempwh = tempwh - tempOut.getFromHeight();
				
				Channel outChannel = selectUsefulIn(toVat);
				int wh = toVat.getWaterHeight();
				int $wh = wh - outChannel.getFromHeight();
				if($tempwh > $wh)
				{
					temp = toVat;
				}
			}
		}
		return temp;
	}
	
	/**
	 * 获取一个水槽的可用入水口
	 * @param vat
	 * @return
	 */
	private Channel selectUsefulIn(Vat vat)
	{
		Channel temp = null;
		for(Channel channel : vat.getChannels())
		{
			if(vat.getVatName().equals(channel.getFromVat().getVatName()))
			{
				if(temp == null)
				{
					temp = channel;
				}else {
					//水槽水位高于当前的水管高度，之前的水管高度低于当前水管高度
					if(vat.getWaterHeight() >= channel.getFromHeight() && 
							temp.getFromHeight() < channel.getFromHeight())
					{
						temp = channel;
					}
				}
			}
		}
		
		return temp;
	}
	
}
