package coursework2;
import java.util.ArrayList;

/**
 * The SotredArrayList class inherit ArrayList class, when add a new item into the list, it will sort the list automatically.
 *
 * @author Junyan Wang
 * @version 1.0
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {
	
	@Override
	public boolean add(E item) {
		E minItem;
		super.add(item);
		
		// insertion sort
		for (int i = 1; i < this.size(); i++) {
			minItem = this.get(i);
			int j;
			for(j = i; j > 0; j--) {
				if(this.get(j-1).compareTo(minItem) < 0) {
					break;
				}else {
					this.set(j, this.get(j-1));
				}
			}
			this.set(j, minItem);
		}
		
		return true;
	}

}
