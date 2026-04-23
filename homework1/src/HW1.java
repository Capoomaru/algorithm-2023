import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class User {
    TreeMap<String, Double> contentList;
    int userId;
    int totalScore;

    public User(int userId) {
        this.userId = userId;
        this.contentList = new TreeMap<>((o1, o2) -> {
            if(o1.charAt(0) != o2.charAt(0))
                return o1.compareTo(o2);
            Integer o1Value = Integer.parseInt(o1.substring(1));
            Integer o2Value = Integer.parseInt(o2.substring(1));

            return o1Value.compareTo(o2Value);
        });
        this.totalScore = 0;
    }

    public void addContent(String contentId, double score) {
        this.totalScore += score;
        contentList.put(contentId, score);
    }
}

public class HW1 {

    public static void main(String[] args) {
        System.out.println("파일 이름, target 사용자, 참고인 수, 항목 수?");
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String fileName = input[0];
        int target = Integer.parseInt(input[1]); //target 사용자
        int n = Integer.parseInt(input[2]); //참고인 수
        int k = Integer.parseInt(input[3]); //추천 항목 수
        ArrayList<User> userList = new ArrayList<>();

        int userSize = 0;
        try {
            sc = new Scanner(new File(fileName));
            userSize = sc.nextInt();
            for(int i=0;i<userSize;i++) {
                userList.add(new User(i));
            }

            while(sc.hasNext()) {
                int userId = sc.nextInt();
                String contentId = sc.next();
                int score = sc.nextInt();
                userList.get(userId).addContent(contentId, score);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sc.close();
        }

        for(int i=0;i<userSize;i++) {
            Map<String, Double> map = userList.get(i).contentList;
            double avg = userList.get(i).totalScore / (double)map.size();
            for(String key : map.keySet()) {
                map.put(key, map.get(key) - avg);
            }
        }

        TreeMap<String, Double> targetContentList = userList.get(target).contentList;
        System.out.print("[ ");
        for(String key : targetContentList.navigableKeySet()) {
            System.out.printf("(%s, %.3f), ", key, targetContentList.get(key));
        }
        System.out.print("]");
    }
}