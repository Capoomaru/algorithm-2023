import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class HW2_2 {

    static class Document {
        HashMap<Integer, Integer> wordMap;
        int cnt;
        HashMap<Integer, Double> TFMap;

        Document() {
            this.wordMap = new HashMap<>();
            this.cnt = 0;
            this.TFMap = new HashMap<>();
        }
    }

    static class Node {
        String title;
        Double similarity;

        public Node(String title, Double similarity) {
            this.title = title;
            this.similarity = similarity;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(this.title);
            sb.append(String.format(" (유사도=%.5f)", this.similarity));
            return sb.toString();
        }


    }

    public static void main(String[] args) {
        BufferedReader br;
        HashSet<Integer> stopSet = new HashSet<>();
        try {
            br = new BufferedReader(new FileReader("./stopwords.txt"));

            String word;
            while((word = br.readLine()) != null) {
                stopSet.add(word.hashCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] input = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in, "MS949"));

            System.out.println("파일 이름, k, 문서 제목: ");
            input = br.readLine().split(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fName = input[0];
        int k = Integer.parseInt(input[1]);
        StringBuilder titleBuilder = new StringBuilder();
        for(int i=2;i<input.length;i++)
            titleBuilder.append(input[i]+" ");
        String targetTitle = titleBuilder.toString().trim();

        long startTime = System.currentTimeMillis();

        /*
        String[] input = sc.nextLine().split(" ");

        String fName = input[0];
        int k = Integer.parseInt(input[1]);
        String targetTitle = input[2];
        */

        //입력 받은 문서에 대한 Map(title, document)
        HashMap<String, Document> documentMap = new HashMap<>();
        //각 단어의 document 빈도수
        HashMap<Integer, Integer> cntMap = new HashMap<>();

        try {
            br = new BufferedReader(new FileReader("./"+fName));

            String title;
            while ((title = br.readLine()) != null) {
                Document document = new Document();
                for (String s : br.readLine().split(("[,.?!:\"\\s]+"))) {
                    int hash = s.toLowerCase().hashCode();
                    if(stopSet.contains(hash)) continue;
                    document.wordMap.put(hash, document.wordMap.getOrDefault(hash, 0) + 1);
                    document.cnt++;
                }
                // 문서 내 IDF 계산 & 문서별 키워드 빈도수 증가
                document.wordMap.forEach((key, value) -> {
                    cntMap.put(key, cntMap.getOrDefault(key, 0) + 1);
                    document.TFMap.put(key, (double) value / document.cnt);
                });
                documentMap.put(title, document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int wordCnt = documentMap.size();

        HashMap<Integer, Double> doubleIDFMap = new HashMap<>();
        cntMap.forEach((key, value) -> doubleIDFMap.put(key, Math.log((double)wordCnt/value)));

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.similarity.compareTo(o1.similarity));

        Document targetDocument = documentMap.get(targetTitle);

        System.out.printf("결과 1. \"%s\"의 TF-IDF 벡터\n[ ", targetTitle);
        List<Double> targetTFIDFSet = targetDocument.TFMap.entrySet()
                .stream().sorted((Map.Entry.comparingByKey())).map((entry) -> {
                    double d = doubleIDFMap.get(entry.getKey()) * entry.getValue();
                    System.out.printf("(%d, %.3f) ",entry.getKey(), d);
                    return d;
                }).collect(Collectors.toList());

        double targetSum = Math.pow(
                targetTFIDFSet.stream().mapToDouble(value -> Math.pow(value, 2)).sum()
                ,0.5);
        System.out.println("]\n");

        documentMap.forEach((title, document) -> {
            if(title.equals(targetTitle)) return;

            Set<Integer> set = new HashSet<>(targetDocument.TFMap.keySet());
            set.retainAll(document.TFMap.keySet());

            double intersectionSum = set.stream().mapToDouble((key) -> doubleIDFMap.get(key) * document.TFMap.get(key) *
                    doubleIDFMap.get(key) * targetDocument.TFMap.get(key)).sum();

            double otherSum = Math.pow(document.TFMap.entrySet()
                            .stream().mapToDouble((entry) -> {
                                double d = doubleIDFMap.get(entry.getKey()) * entry.getValue();
                                return Math.pow(d,2);
                            }).sum()
                    ,0.5);

            if(otherSum != 0 && targetSum != 0)
                pq.add(new Node(title, intersectionSum / (otherSum * targetSum)));
        });

        System.out.printf("결과 2. \"%s\"과(와) 유사한 %d개의 문서\n", targetTitle, k);

        for(int i=0;i<k;i++) {
            System.out.print((i+1)+". ");
            System.out.println(pq.poll());
        }

        System.out.print("\n 실행 시간 : ");
        System.out.println(System.currentTimeMillis() - startTime);

    }
}