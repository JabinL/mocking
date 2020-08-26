package parking;

import mocking.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})

public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */
        mockStatic(ParkingLot.class);
        mockStatic(Calendar.class);
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SUNDAY);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(50);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        assertEquals(50,(int)inOrderParkingStrategy.calculateHourlyPrice());
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */

        mockStatic(ParkingLot.class);
        mockStatic(Calendar.class);
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.MONDAY);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(40);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        assertEquals(40,(int)inOrderParkingStrategy.calculateHourlyPrice());
    }
}
