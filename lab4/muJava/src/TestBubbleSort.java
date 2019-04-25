import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class TestBubbleSort {
	@Test
	public void testBubble() {
		int[] arr = {1,4,2,1};
		int[] arr1 = {1,1,2,4};
		assertArrayEquals(BubbleSort.BubbleSort(arr),arr1);
	}

}
