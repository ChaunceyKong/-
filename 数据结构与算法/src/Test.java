


//739. 每日温度

import java.util.*;


class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < 2 * n - 1; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i % n]) {
                int idx = stk.pop();
                res[idx] = nums[i % n];
            }
            stk.push(i % n);
        }

        return res;
    }

}
