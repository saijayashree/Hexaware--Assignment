package Day2.Insuranceclaim;


public class SlidingWindowClaims {

    static int longestRun(int[] claims) {

        int count = 0;
        int max = 0;

        for (int i = 0; i < claims.length; i++) {

            if (claims[i] > 100) {

                count++;

                if (count > max) {
                    max = count;
                }

            } else {

                count = 0;
            }
        }

        return max;
    }

    public static void main(String[] args) {

        int[] claims = {120, 150, 130, 80, 140, 160};

        System.out.println("SLIDING WINDOW CLAIM ANALYSIS");

        System.out.println(
            "Longest consecutive days: " + longestRun(claims)
        );

        int[] claims2 = {110, 120, 130, 140};

        System.out.println(
            "Longest consecutive days: " + longestRun(claims2)
        );

        int[] claims3 = {50, 60, 70};

        System.out.println(
            "Longest consecutive days: " + longestRun(claims3)
        );
    }
}
