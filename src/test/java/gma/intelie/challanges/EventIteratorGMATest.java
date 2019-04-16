package gma.intelie.challanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import net.intelie.challenges.Event;

public class EventIteratorGMATest {

	private EventIteratorGMA eventIteratorGMA;

	// @Before
	void setup() {

	}

	@Test
	public void moveNext_iteratorHasReachedTheEnd_returnFalse() {
		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 1L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		eventIteratorGMA.moveNext();

		assertTrue(!eventIteratorGMA.moveNext());

	}

	@Test
	public void moveNext_emptyList_returnFalse() {
		List<Event> lstEvent = new ArrayList<>();

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		assertFalse(eventIteratorGMA.moveNext());
	}

	@Test
	public void moveNext_notEnd_returnTrue() {
		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 1L));
		lstEvent.add(new Event("EVE-ADD", 2L));
		lstEvent.add(new Event("EVE-ADD", 3L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		assertTrue(eventIteratorGMA.moveNext());
		assertTrue(eventIteratorGMA.moveNext());
		 

	}

	@Test(expected = IllegalStateException.class)
	public void current_moveNextWasNeverCalled_returnIllegalStateException() {

		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 1L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		Event e = eventIteratorGMA.current();

	}

	@Test(expected = IllegalStateException.class)
	public void current_emptyList_returnIllegalStateException() {

		List<Event> lstEvent = new ArrayList<>();

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		Event e = eventIteratorGMA.current();

	}

	@Test(expected = IllegalStateException.class)
	public void current_moveNextLastResultWasFalse_returnIllegalStateException() {
		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 196701L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		eventIteratorGMA.moveNext();
		eventIteratorGMA.current();

		eventIteratorGMA.moveNext();
		eventIteratorGMA.current();

	}

	@Test
	public void current_sucess_returnCurrentEvent() {

		boolean hasNext = false;

		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 196701L));
		lstEvent.add(new Event("EVE-ADD", 196702L));
		lstEvent.add(new Event("EVE-ADD", 196703L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		hasNext = eventIteratorGMA.moveNext();
		assertTrue((eventIteratorGMA.current().timestamp() == 196701L) && (hasNext));

		hasNext = eventIteratorGMA.moveNext();
		assertTrue((eventIteratorGMA.current().timestamp() == 196702L) && (hasNext));

		hasNext = eventIteratorGMA.moveNext();
		assertTrue((eventIteratorGMA.current().timestamp() == 196703L) && (hasNext));

		hasNext = eventIteratorGMA.moveNext();
		assertTrue(!hasNext);

	}

	@Test(expected = IllegalStateException.class)
	public void remove_notCallNext_returnIllegalStateException() {
		List<Event> lstEvent = new ArrayList<>();

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());

		eventIteratorGMA.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void remove_withEnd_returnIllegalStateException() {
		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 196701L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());
		eventIteratorGMA.moveNext();
		eventIteratorGMA.moveNext();

		eventIteratorGMA.remove();
	}

	@Test
	public void remove_sucesso_() {
		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 196701L));

		eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator());
		eventIteratorGMA.moveNext();
		eventIteratorGMA.remove();

		assertTrue(lstEvent.size() == 0);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void close_sucesso() throws Exception {

		List<Event> lstEvent = new ArrayList<>();
		lstEvent.add(new Event("EVE-ADD", 196701L));
		lstEvent.add(new Event("EVE-ADD", 196702L));
		lstEvent.add(new Event("EVE-ADD", 1967031L));

		try (EventIteratorGMA eventIteratorGMA = new EventIteratorGMA(lstEvent.iterator())) {

			eventIteratorGMA.moveNext();

		}

	}

	 

}
