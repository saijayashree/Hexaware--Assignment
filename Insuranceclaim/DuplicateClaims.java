package Day2.Insuranceclaim;


import java.util.*;

public class DuplicateClaims {

    static class Claim {

        String policy;
        String date;

        Claim(String policy, String date) {

            this.policy = policy.replaceAll("\\s+", "")
                                .toLowerCase(Locale.ROOT);

            this.date = date.replaceAll("\\s+", "")
                            .toLowerCase(Locale.ROOT);
        }

        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Claim)) {
                return false;
            }

            Claim other = (Claim) obj;

            return policy.equals(other.policy)
                    && date.equals(other.date);
        }

        public int hashCode() {

            return Objects.hash(policy, date);
        }
    }

    static void detectDuplicates(String[] policies, String[] dates) {

        if (policies.length != dates.length) {
            System.out.println("Invalid input");
            return;
        }

        HashMap<Claim, Integer> map = new HashMap<>();

        if (policies.length == 0) {
            System.out.println("No claims available");
            return;
        }

        for (int i = 0; i < policies.length; i++) {

            Claim c = new Claim(policies[i], dates[i]);

            if (map.containsKey(c)) {

                System.out.println("Duplicate claim: " + policies[i]);

            } else {

                map.put(c, i);

                System.out.println("New claim: " + policies[i]);
            }
        }
    }

    public static void main(String[] args) {

        String[] policies = {
            "POL123",
            "pol123",
            "POL456",
            "POL 123",
            "POL789"
        };

        String[] dates = {
            "10-11-2026",
            "10-11-2026",
            "11-11-2026",
            "10-11-2026",
            "12-11-2026"
        };

        System.out.println("DUPLICATE CLAIM DETECTION");

        detectDuplicates(policies, dates);
    }
}
