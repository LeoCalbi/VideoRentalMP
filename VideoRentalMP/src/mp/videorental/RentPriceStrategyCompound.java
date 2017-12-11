package mp.videorental;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import mp.videorental.exception.AddToLeafCompositeException;

public class RentPriceStrategyCompound implements RentPriceStrategy {
	
	private List<RentPriceStrategy> strategies;
	
	public RentPriceStrategyCompound() {
		strategies = new LinkedList<RentPriceStrategy>();
	}
	
	public Iterator<RentPriceStrategy> getIterator() {
		return strategies.iterator();
	}
	
	@Override
	public void add(RentPriceStrategy r) throws AddToLeafCompositeException {
		strategies.add(r);
	}

	@Override
	public Double getPrice(Double initialPrice) {
		Iterator<RentPriceStrategy> it = getIterator();
		Double variance = 0.0;
		while(it.hasNext()) variance += it.next().getPrice(initialPrice) - initialPrice;
		return initialPrice + variance;
	}
	
}
