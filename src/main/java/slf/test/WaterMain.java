package slf.test;

import java.util.ArrayList;
import java.util.List;

import slf.test.bo.Channel;
import slf.test.bo.Vat;
import slf.test.service.IWaterPipe;
import slf.test.service.WaterPipe;

/**
 * Hello world!
 *
 */
public class WaterMain 
{
	static
	{
		
		//TODO初始化地图
		Vat vatA = new Vat("A");
		List<Channel> channels = new ArrayList<Channel>();
		Channel channel = new Channel(VatContext.VAT_RIGHT,2,vatA);
		channels.add(channel);
		VatBuild.build(vatA, channels);
		
		Vat vatB = new Vat("B");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_RIGHT,2,vatA,VatContext.VAT_LEFT,5,vatB);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_RIGHT,2,vatB);
		channels.add(channel);
		VatBuild.build(vatB, channels);
		
		Vat vatC = new Vat("C");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_RIGHT,2,vatB,VatContext.VAT_RIGHT,5,vatC);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_RIGHT,2,vatC);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_LEFT,3,vatC);
		channels.add(channel);
		VatBuild.build(vatC, channels);
		
		Vat vatD = new Vat("D");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_RIGHT,2,vatC,VatContext.VAT_LEFT,6,vatD);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatD);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_LEFT,1,vatD);
		channels.add(channel);
		VatBuild.build(vatD, channels);
		
		Vat vatJ = new Vat("J");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_LEFT,3,vatC,VatContext.VAT_LEFT,6,vatJ);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_RIGHT,1,vatJ);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_LEFT,3,vatJ);
		channels.add(channel);
		VatBuild.build(vatJ, channels);
		
		Vat vatI = new Vat("I");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_RIGHT,1,vatJ,VatContext.VAT_LEFT,6,vatI);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatI);
		channels.add(channel);
		VatBuild.build(vatI, channels);
		
		Vat vatK = new Vat("K");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatI,VatContext.VAT_RIGHT,6,vatK);
		channels.add(channel);
		VatBuild.build(vatK, channels);
		
		Vat vatL = new Vat("L");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_LEFT,3,vatJ,VatContext.VAT_RIGHT,6,vatL);
		channels.add(channel);
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatL);
		channels.add(channel);
		VatBuild.build(vatL, channels);
		
		Vat vatE = new Vat("E");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_LEFT,1,vatD,VatContext.VAT_RIGHT,6,vatE);
		channels.add(channel);
		VatBuild.build(vatE, channels);
		
		Vat vatG = new Vat("G");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatD,VatContext.VAT_TOP,6,vatG);
		channels.add(channel);
		VatBuild.build(vatG, channels);
		
		Vat vatH = new Vat("H");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatL,VatContext.VAT_LEFT,2,vatH);
		channels.add(channel);
		VatBuild.build(vatH, channels);
		
		Vat vatF = new Vat("F");
		channels = new ArrayList<Channel>();
		channel = new Channel(VatContext.VAT_BOTTOM,0,vatL,VatContext.VAT_BOTTOM,-2,vatF);
		channels.add(channel);
		VatBuild.build(vatF, channels);
		
	}
	
	
    public static void main( String[] args )
    {
    	final int water = 1;
    	IWaterPipe waterPipe = new WaterPipe();
        while(!VatContext.isFull)
        {
        	Vat vat = VatBuild.getFirstVat();
        	try {
				waterPipe.pourWater(vat, water);
			} catch (WaterVatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try {
				Thread.sleep(1000);
				VatContext.isFull = true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    }
}
