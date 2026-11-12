
package Day2.Insuranceclaim;

import java.util.*;

public class DuplicateClaims {

    static class Claim {
        String policy;
        String date;

        Claim(String policy, String date) {
            this.policy = policy.replace(" ", "").toLowerCase();
            this.date = date.replace(" ", "").toLowerCase();
        }

        public int hashCode() {
            return Objects.hash(policy, date);
        }

        public boolean equals(Object obj) {
            Claim c = (Claim) obj;

            return policy.equals(c.policy) &&
                   date.equals(c.date);
        }
    }

    static void detectDuplicates(String[] policies, String[] dates) {

        HashSet<Claim> set = new HashSet<>();

        for (int i = 0; i < policies.length; i++) {

            Claim c = new Claim(policies[i], dates[i]);

            if (set.contains(c)) {
                System.out.println("Duplicate claim: " + policies[i]);
            } 
            else {
                set.add(c);
                System.out.println("New claim: " + policies[i]);
            }
        }
    }

    public static void main(String[] args) {

        String[] policies = {
            "POL123", "pol123", "POL456", "POL 123", "POL789"
        };

        String[] dates = {
            "10-11-2026", "10-11-2026", "11-11-2026",
            "10-11-2026", "12-11-2026"
        };

        detectDuplicates(policies, dates);
    }
}
