package parking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createReceipt(new ParkingLot("A",10),new Car("Jay"));
        assertEquals("Jay",receipt.getCarName());
        assertEquals("A",receipt.getParkingLotName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(new Car("Jay"));
        assertEquals("Jay",receipt.getCarName());
        assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot parkingLotA = mock(ParkingLot.class);
        ParkingLot parkingLotB = mock(ParkingLot.class);
        when(parkingLotA.isFull()).thenReturn(true);
        when(parkingLotB.isFull()).thenReturn(true);
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        Car car = new Car("che");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);
        assertEquals("che",receipt.getCarName());
        assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());

        verify(parkingLotA,times(1)).isFull();
        verify(parkingLotB,times(1)).isFull();

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot parkingLotA = mock(ParkingLot.class);
        ParkingLot parkingLotB = mock(ParkingLot.class);
        ParkingLot parkingLotC = new ParkingLot("C",10);

        when(parkingLotA.isFull()).thenReturn(true);
        when(parkingLotB.isFull()).thenReturn(true);
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        parkingLots.add(parkingLotC);

        Car car = new Car("che");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);
        assertEquals("che",receipt.getCarName());
        assertEquals("C",receipt.getParkingLotName());
        verify(parkingLotA,times(1)).isFull();
        verify(parkingLotB,times(1)).isFull();
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot parkingLotA = spy(new ParkingLot("A",10));
        doReturn(true).when(parkingLotA).isFull();
        parkingLots.add(parkingLotA);

        Car car = new Car("che");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);

        verify(parkingLotA,times(1)).isFull();

        assertEquals("che",receipt.getCarName());
        assertEquals(ParkingStrategy.NO_PARKING_LOT,receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        List<ParkingLot> parkingLots = new ArrayList<>();

        ParkingLot parkingLotA = spy(new ParkingLot("A",10));
        ParkingLot parkingLotB = spy(new ParkingLot("B",10));
        ParkingLot parkingLotC = spy(new ParkingLot("C",10));
        doReturn(true).when(parkingLotA).isFull();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        parkingLots.add(parkingLotC);

        Car car = new Car("che");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);

        verify(parkingLotA,times(1)).isFull();
        verify(parkingLotB,times(1)).isFull();
        verify(parkingLotC,times(0)).isFull();


        assertEquals("che",receipt.getCarName());
        assertEquals("B",receipt.getParkingLotName());
    }


}
