package gma.intelie.challanges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.intelie.challenges.Event;

public class EventIteratorGMATest {
	
	private EventIteratorGMA eventIteratorGMA;
	
	//@Before
	void setup() {
 
	
	}
	
	
	@Test
	public void  moveNext_semMaisItens() {
		List<Event> lstEvent=new ArrayList<>(); 
		lstEvent.add( new Event("EVE-ADD",1L));
		
	   
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
				
		assertTrue(!eventIteratorGMA.moveNext());
		
		
	}
	
	@Test
	public void  moveNext_comMaisItens() {
		List<Event> lstEvent=new ArrayList<>(); 
		lstEvent.add( new Event("EVE-ADD",1L));
		lstEvent.add( new Event("EVE-ADD",2L));
	   
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
				
		assertTrue(eventIteratorGMA.moveNext());
		assertFalse(eventIteratorGMA.moveNext());
		
	}
	@Test
	public void  moveNext_semItens() {
		List<Event> lstEvent=new ArrayList<>(); 
	   
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
				
		
		assertFalse(eventIteratorGMA.moveNext());
	}

	@Test(expected=IllegalStateException.class)
	public void current_notCallNext_should_illegalStateException() {
		
		List<Event> lstEvent=new ArrayList<>(); 
		lstEvent.add( new Event("EVE-ADD",1L));
		
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
		
		Event e=eventIteratorGMA.current();
			
	}
	
	@Test(expected=IllegalStateException.class)
	public void current_empty_should_illegalStateException() {
		
		List<Event> lstEvent=new ArrayList<>(); 
		
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
		
		Event e=eventIteratorGMA.current();
			
	}
	
	@Test
	public void current_() {
		
		boolean hasNext=false;

		List<Event> lstEvent=new ArrayList<>(); 
		lstEvent.add( new Event("EVE-ADD",196701L));
		lstEvent.add( new Event("EVE-ADD",196702L));
		lstEvent.add( new Event("EVE-ADD",196703L));
		
		eventIteratorGMA= new EventIteratorGMA(lstEvent.iterator());
			
		
		hasNext=eventIteratorGMA.moveNext();
		assertTrue(( eventIteratorGMA.current().timestamp()==196701L) && (hasNext) );
		
		hasNext=eventIteratorGMA.moveNext();
		assertTrue(( eventIteratorGMA.current().timestamp()==196702L) && (hasNext) );
		
		hasNext=eventIteratorGMA.moveNext();
		assertTrue(( eventIteratorGMA.current().timestamp()==196703L) && (!hasNext) );
		
		
			
	}


}
