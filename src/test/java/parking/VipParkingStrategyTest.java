package parking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        ParkingLot parkingLot = spy(new ParkingLot("Jay",10));
        doReturn(true).when(parkingLot).isFull();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao carDao = spy(new CarDaoImpl());
        doReturn(true).when(carDao).isVip(anyString());

        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("AJ1");

        Receipt receipt = vipParkingStrategy.park(parkingLots,car);
        assertEquals("Jay",receipt.getParkingLotName());
        assertEquals("AJ1",receipt.getCarName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        ParkingLot parkingLot = spy(new ParkingLot("Jay",10));
        doReturn(true).when(parkingLot).isFull();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao carDao = spy(new CarDaoImpl());
        doReturn(false).when(carDao).isVip(anyString());

        vipParkingStrategy.carDao = carDao;

        Car car = createMockCar("AJ1");

        Receipt receipt = vipParkingStrategy.park(parkingLots,car);
        assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
        assertEquals("AJ1",receipt.getCarName());

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao newCarDao = spy(new CarDaoImpl());
        doReturn(true).when(newCarDao).isVip(anyString());
        vipParkingStrategy.carDao = newCarDao;

        Car car = createMockCar("AJ1");
        assertTrue(vipParkingStrategy.isAllowOverPark(car));

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //refactor the private to public
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao newCarDao = spy(new CarDaoImpl());
        doReturn(true).when(newCarDao).isVip(anyString());
        vipParkingStrategy.carDao = newCarDao;

        Car car = createMockCar("QQ1");
        assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao newCarDao = spy(new CarDaoImpl());
        doReturn(false).when(newCarDao).isVip(anyString());
        vipParkingStrategy.carDao = newCarDao;

        Car car = createMockCar("AQ1");
        assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        CarDao newCarDao = spy(new CarDaoImpl());
        doReturn(false).when(newCarDao).isVip(anyString());
        vipParkingStrategy.carDao = newCarDao;

        Car car = createMockCar("QJ1");
        assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
