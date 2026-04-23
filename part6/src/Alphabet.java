import java.util.Scanner;

/*
 * 문자열의 범위를 제한하기 위한 클래스
 */
public class Alphabet {
    public static Alphabet BINARY = new Alphabet("01");
    public static Alphabet OCTAL = new Alphabet("01234567");
    public static Alphabet DECIMAL = new Alphabet("0123456789");
    public static Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static Alphabet DNA = new Alphabet("ACTG");
    public static Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    public static Alphabet ASCII = new Alphabet(128);
    public static Alphabet EXTENDED_ASCII = new Alphabet(256);
    public static Alphabet UNICODE16 = new Alphabet(65536);

    private final char[] chars;

    public Alphabet() {
        this(256);
    }

    public Alphabet(String s) {
        this.chars = s.toCharArray();
    }

    public Alphabet(int radix) {
        chars = new char[radix];
        for(int i=0;i<radix;i++)
            chars[i] = (char) i;
    }

    public char toChar(int index) {
        return chars[index];
    }

    public int toIndex(char c)  {
        for(int i=0;i<chars.length;i++)
            if(chars[i] == c)
                return i;
        return -1;
    }

    public boolean contains(char c) {
        return toIndex(c) != -1;
    }

    public int R() {
        return chars.length;
    }

    public int IgR() {
        return (int) Math.log(chars.length);
    }

    // indices : index 의 복수형
    public int[] toIndices(String s) {
        int[] result = new int[s.length()];

        for(int i=0;i<s.length();i++) {
            result[i] = toIndex(s.charAt(i));
        }
        return result;
    }

    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder(indices.length);
        for(int i : indices) {
            sb.append(toChar(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        double a = 123.56789;
        System.out.println(Math.round(a));

        Alphabet alphabet = new Alphabet("ABCDR");
        int R = alphabet.R();
        int[] count = new int[R];
        Scanner sc = new Scanner(System.in);
        String s= sc.next();
        int N = s.length();
        for(int i=0;i<N;i++)
            if(alphabet.contains(s.charAt(i)))
                count[alphabet.toIndex(s.charAt(i))]++;
        for(int c = 0;c<R;c++)
            System.out.println(alphabet.toChar(c)+" "+count[c]);
    }

}
