package slf.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import slf.test.bo.Channel;
import slf.test.bo.Vat;

public class VatBuild {

	private static List<Vat> vats = new LinkedList<Vat>();
	
	public static void build(Vat vat,List<Channel> channels)
	{
		//TODO
		for(Channel channel : channels)
		{
			if(channel.getFromVat().getVatName().equals(vat.getVatName()))
			{	
				/**
				 * 起始管道不做处理
				 */
				continue;
			}
			for(Vat oldVat : vats)
			{
				for(Channel oldChannel : oldVat.getChannels())
				{
					if(oldChannel.getFromVat().getVatName().equals(channel.getFromVat().getVatName()) &&
							oldChannel.getFromDirection() == channel.getFromDirection() && 
							oldChannel.getFromHeight() == channel.getFromHeight())
					{
						if(oldChannel.getToVats() != null)
						{
							for(Vat toVat : oldChannel.getToVats())
							{
								if(!toVat.getVatName().equals(vat.getVatName()))
								{
									oldChannel.getToVats().add(vat);
									System.out.println("装配管道["+ oldVat.getVatName() + "-" + vat.getVatName() + "]");
									break;
								}
							}
						}else {
							List<Vat> toVats = new ArrayList<Vat>();
							toVats.add(vat);
							oldChannel.setToVats(toVats);
							System.out.println("装配管道["+ oldVat.getVatName() + "-" + vat.getVatName() + "]");
						}
					}
				}
			}
		}
		vat.setChannels(channels);
		System.out.println("增加水槽-" + vat.getVatName() + ":");
		if(vat.getChannels() != null)
		{
			for(Channel channel : vat.getChannels())
			{
				System.out.println("up:" + channel.getFromVat().getVatName());
				if(channel.getToVats() != null)
				{
					for(Vat toVat : channel.getToVats())
					{
						System.out.println("down:" + toVat.getVatName());
					}
				}
			}
		}
		vats.add(vat);
	}
	
	/**
	 * 获取队列中第一个水槽
	 * @return
	 */
	public static Vat getFirstVat()
	{
		for(Vat vat : vats)
		{
			if(vat.getVatName().equals("A"))
			{
				return vat;
			}
		}
		return null;
	}
}
