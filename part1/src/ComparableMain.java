package part1;

public class ComparableMain {

    public static class DateCustom implements Comparable<DateCustom> {
        private final int year;
        private final int month;
        private final int day;

        public DateCustom(int year, int month, int day) {
            if(!isValid(year,month, day)) throw new IllegalArgumentException("Invalid date");
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(DateCustom o1) {
            if (this.year < o1.year) return -1;
            if (this.year > o1.year) return +1;
            if (this.month < o1.month) return -1;
            if (this.month > o1.month) return +1;
            if (this.day < o1.day) return -1;
            if (this.day > o1.day) return +1;
            return 0;
        }

        public static boolean isValid(int year, int month, int day) {
            Integer date = null;

            if(month < 1 || month > 12) return false;

            //윤년 체크
            if(month == 2) {
                if(isLeapYear(year))
                    date = 29;
                else
                    date = 28;
            }
            //일수 체크 -> 해당 코드는 JAVA 12에서부터 허용됨 https://www.baeldung.com/spring-boot-3-spring-6-new
            date = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                default -> 0;
            };
            ;

            if (date == 0 || day != date)
                return false;

            return true;
        }

        public static boolean isLeapYear(int year) {
            if(year % 4 == 0) {
                if (year % 400 == 0) return true;
                else if (year % 100 == 0) return false;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}

