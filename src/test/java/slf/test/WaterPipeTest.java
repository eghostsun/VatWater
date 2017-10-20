package slf.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import slf.test.bo.Channel;
import slf.test.bo.Vat;
import slf.test.service.WaterPipe;

public class WaterPipeTest extends TestCase {

	private WaterPipe waterPipe = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		waterPipe = new WaterPipe();
	}

	public void testPourWater() {
		Class<?>[] arg1 = new Class<?>[1];
		arg1[0] = Vat.class;
		try {
			Method method = waterPipe.getClass().getDeclaredMethod("selectNearestChannel", arg1);
			method.setAccessible(true);
			
			Vat vatA = new Vat();
			vatA.setVatName("A");
			List<Channel> channels = new ArrayList<Channel>();
			Vat vatB = new Vat();
			vatB.setVatName("B");
			Channel channel = new Channel(VatContext.VAT_BOTTOM,0,vatA,VatContext.VAT_RIGHT, 2,vatB);
			channels.add(channel);
			Vat vatC = new Vat();
			vatB.setVatName("C");
			channel = new Channel(VatContext.VAT_BOTTOM,0,vatA,VatContext.VAT_BOTTOM, -2,vatC);
			channels.add(channel);
			vatA.setChannels(channels);
			Object[] objs = new Object[1];
			objs[0] = vatA;
			Object back = method.invoke(waterPipe,objs);
			channel = (Channel)back;
			Assert.assertNotNull(channel);
			
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
