import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;



@RunWith(Parameterized.class)
public class TestBackPack {
    private int res;
    private int m;
    private int n;
    private int[] w;
    private int[] p;
    
    /**
     * @param m 表示背包的最大容量
     * @param n 表示商品个数
     * @param w 表示商品重量数组
     * @param p 表示商品价值数组
     */
    public TestBackPack(int m,int n, int[] w, int[] p, int res){
    	this.m = m;
    	this.n = n;
    	this.w = w;
    	this.p = p;
        this.res = res;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData(){
    	int[] w = {3,4,5};
    	int[] p = {4,5,6};
        return Arrays.asList(new Object[][]{ 
        		{10,3,w,p,11},
        		{14,3,w,p,15}
        });
    }

    @Test
    public void testBack(){
    	assertEquals(BackPack.BackPack_Solution(m, n, w, p)[n][m], res);
    }
}