package cyclechronicles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopTest {

	private Shop shop;
	
	@BeforeEach
	void setUp() {
		shop = new Shop();
	}
	
	private Order createMockOrder(Type type, String customer) {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(type);
        when(order.getCustomer()).thenReturn(customer);
        return order;
    }
	
	@Test
	void testAcceptTrueI() {
		assertTrue(shop.accept(createMockOrder(Type.RACE, "Kunde 1")));
	}
	
	@Test
	void testAcceptTrueII() {
		shop.accept(createMockOrder(Type.RACE, "Kunde 1"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 2"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 3"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 4"));
		assertTrue(shop.accept(createMockOrder(Type.FIXIE, "Kunde 5")));
	}
	
	@Test
	void testAcceptGravel() {
		assertFalse(shop.accept(createMockOrder(Type.GRAVEL, "Kunde 1")));
	}
	
	@Test
	void testAcceptEbike() {
		assertFalse(shop.accept(createMockOrder(Type.EBIKE, "Kunde 1")));
	}
	
	@Test
	void testAcceptFivePendingOrders() {
		shop.accept(createMockOrder(Type.RACE, "Kunde 1"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 2"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 3"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 4"));
		shop.accept(createMockOrder(Type.RACE, "Kunde 5"));
		assertFalse(shop.accept(createMockOrder(Type.RACE, "Kunde 6")));
	}
	
	@Test
	void testAcceptTwoOrdersCustomer() {
		shop.accept(createMockOrder(Type.RACE, "Kunde 1"));
		assertFalse(shop.accept(createMockOrder(Type.RACE, "Kunde 1")));
	}
}
