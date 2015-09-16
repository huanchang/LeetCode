// Best time to sell and buy stock I, II, III and IV

public class BestTimeSellAndBuyStock{
	// Best Time to Sell and Buy Stock I 121
	public int maxProfitI(int[] prices) {
		// Only one transaction
        // Solution: one pass
        
        if ( prices == null || prices.length <= 1 ) {
            return 0;
        }
        
        int min = prices[0];
        int res = 0;
        
        for ( int i = 1; i < prices.length; ++i ) {
            res = Math.max( res, prices[i] - min);
            min = Math.min( min, prices[i]);
        }
        
        return res;
    }
    
    // Best Time to Sell and Buy Stock II 122
    public int maxProfit2(int[] prices) {
        // As many transactions as you like
        
        if ( prices == null || prices.length == 0 ) {
            return 0;
        }
        
        int min = prices[0], profit = 0, total = 0;
        
        for ( int i = 1; i < prices.length; ++i ) {
            if ( prices[i] < prices[i-1] ) {
                
                total += prices[i-1] - min;
                min = prices[i];
                profit = 0;
            }
        }
        
        return total + prices[prices.length-1] - min;
    }
    
    // Best Time to Sell and Buy Stock III 123
    public int maxProfit3(int[] prices) {
        // At most two transactions
        
        if ( prices == null || prices.length == 0 ) {
            return 0;
        }
        
        int n = prices.length;
        
        int[] profit = new int[n];
        profit[0] = 0;
        
        int min = prices[0], max = prices[n-1], rightProfit = 0;
        
        // First pass: calculate the maximum left profit for each position
        for ( int i = 1; i < n; ++i ) {
            min = Math.min( min, prices[i]);
            profit[i] = Math.max( profit[i-1], prices[i] - min);
        }
        
        int res = profit[n-1];
        
        // Second pass: calculate the maximum right profit for each position
        for ( int i = n-1; i >= 0; --i ) {
            max = Math.max( max, prices[i] );
            rightProfit = Math.max( rightProfit, max - prices[i]);
            profit[i] += rightProfit;
            res = Math.max( res, profit[i]);
        }
        
        return res;
    }
    
    // Best Time to Sell and Buy Stock IV 188
    public int maxProfit4(int k, int[] prices) {
        // At most k transactions
        
        // Create an local array to store the maximum profits on day i and must sell stock transaction j on day i
        if ( prices == null || prices.length == 0 || k <= 0 ) {
        	return 0;
        }
        
        int n = prices.length;
        
        int[] local = new int[n][k+1];
        int[] global = new int[n][k+1];
        
        for ( int i = 1; i < n; ++i ) {
        
        	int diff = prices[i] - prices[i-1];
        	
        	for ( int j = 1; j <= k; ++j ) {
        		local[i][j] = Math.max( global[i-1][j-1] + Math.max(diff,0), local[i-1][j] + diff );
        		global[i][j] = Math.max( global[i-1][j], local[i][j] );
        	}
        }
        
        return global[n-1][k];
    }
}