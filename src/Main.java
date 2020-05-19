import java.util.HashMap;
import java.util.Map;

/**
 * test class
 */
class Main {

    private static final int tree = 0b11;
    private static final int X = 0xFF;

    public static void main(String[] args) {
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                ans = Math.max(ans, i - map.getOrDefault(arr[i], cur));
                cur = i;
            }
            //ans = Math.max(ans, i - map.getOrDefault(arr[i], cur));

            map.put(arr[i], i);
        }
        return ans;
    }


}
