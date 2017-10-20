package slf.test.service;

import slf.test.WaterVatException;
import slf.test.bo.Vat;

public interface IWaterPipe {

	public void pourWater(Vat vat,int num) throws WaterVatException ;
}
