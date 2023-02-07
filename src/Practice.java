import java.util.*;

public class Practice {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //날짜, 약관 배열, 사용자의 약관 동의 히스토리 배열
        String today = "2022.05.19";
        String[] terms = {"A\t6", "B\t12", "C\t3"};
        String[] privacies = {"2021.05.02\tA", "2021.07.01\tB", "2022.02.19\tC", "2022.02.20\tC"};

        //결과
        System.out.println("result : " + Arrays.toString(solution.solution(today, terms, privacies)));
    }
}

/*2023-02-06*/
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        //오늘 날짜 환산
        int todayDate = dateCalculator(today.trim());

        //전체 약관 map 만들기
        Map<String, Integer> termMap = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].trim().split("\t");
            termMap.put(term[0], Integer.parseInt(term[1]));
        }

        //파기 대상 약관 리스트
        List<Integer> expList = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].trim().split("\t");
            if(termMap.get(privacy[1]) * 28 <= todayDate - dateCalculator(privacy[0])) {
                expList.add(i + 1);
            }
        }

        //결과 배열 생성 후 반환
        int[] resultArr = new int[expList.size()];
        for(int i = 0; i < expList.size(); i++) {
            resultArr[i] = expList.get(i);
        }
        return resultArr;
    }

    //날짜 환산기(전체 날짜를 일 수로 환산)
    private int dateCalculator(String today) {
        String[] date = today.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);

        return (year * 12 * 28) + (month * 28) + day;
    }
}