package Phase2;

public class Phase2 {

    public static void h(int x, int y) {
        // pre-cond: x, y >= 0
        if (x <= 6 * 31) {
            System.out.println((x / 31 + 1) + " " + (x % 31 + 1));
        } else {
            x -= 6 * 31;
            if (x <= 5 * 30) {
                System.out.println((7 + x / 30) + " " + (1 + x % 30));
            } else {
                x -= 5 * 30;
                boolean leap = isLeapYear(y);
                if (leap) {
                    if (x <= 30) {
                        System.out.println(12 + " " + x);
                    } else {
                        throw new RuntimeException();
                    }
                } else {
                    if (x <= 29) {
                        System.out.println(12 + " " + x);
                    } else {
                        throw new RuntimeException();
                    }
                }
            }
        }
    }

    public static boolean isLeapYear(int y) {
        return y == 2024;
    }
}
