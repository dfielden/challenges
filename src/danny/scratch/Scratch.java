package danny.scratch;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.Collectors;

public class Scratch {

    public static void main(String[] args) {
        System.out.println(multiply(4));
        Queue q = new LinkedList();
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, 2)));

    }


    public static double multiply(double a) {
        return a * 2;
    }

    public static long add (long a) {
        return a + 2;
    }


    public Patient lookupPatientById(String id) {
        return null;
    }

    public void savePatient(Patient p) {
        // ...
    }

    // ID FirstName LastName DoB
    // ________________________________
    // 1  Gub       Worm     1986-05-22
    // 2  Dan       Worm     1986-05-22


    public final class Patient {
        private final String id;
        private final String dob;

        public Patient(String id, String dob) {
            this.id = id;
            this.dob = dob;
        }

        public String getId() {
            return id;
        }

        public String getDob() {
            return dob;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Patient)) {
                return false;
            }
            Patient p = (Patient) o;
            return p.id.equals(this.id) && p.dob.equals(this.id);
        }

        @Override
        public int hashCode() {
            return 31 * id.hashCode() + dob.hashCode();
        }
    }


    public final class WaitingPatient implements Comparable<WaitingPatient> {
        private final Patient patient;
        private final Date time;
        private final int priority;

        public WaitingPatient(Patient patient, int priority) {
            this.patient = patient;
            this.time = new Date();
            this.priority = priority;
        }

        public Patient getPatient() {
            return patient;
        }

        public Date getTime() {
            return time;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof WaitingPatient)) {
                return false;
            }
            WaitingPatient p = (WaitingPatient) o;
            return p.patient.equals(this.patient) && p.time.equals(this.time) && p.priority == this.priority;
        }

        @Override
        public int hashCode() {
            return 31 * patient.hashCode() + time.hashCode() + Integer.hashCode(priority);
        }


        @Override
        public int compareTo(WaitingPatient p) {
            int pri = Integer.compare(this.priority, p.priority);
            if (pri != 0) {
                return pri;
            }

            int t = time.compareTo(p.time);
            if (t != 0) {
                return t;
            }

            return 0;  // Equal.


        }

    }
}

