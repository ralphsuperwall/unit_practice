import java.util.*;

public class Practice {
    public static void main(String[] args) {

        Parenthesis parenthesis = new Parenthesis();
        System.out.println(parenthesis.solution("(()()))()()((((()"));
    }
}

/*2023-02-07*/
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies){
        return solution(today, terms, privacies, " ");
    }

    public int[] solution(String today, String[] terms, String[] privacies, String splitter) {
        //오늘 날짜 환산
        int todayDate = dateCalculator(today.trim());


        //전체 약관 map 만들기
        Map<String, Integer> termMap = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].trim().split(splitter);
            termMap.put(term[0], Integer.parseInt(term[1]));
        }

        //파기 대상 약관 리스트
        List<Integer> expList = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].trim().split(splitter);
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

/*2023-02-07*/
class Calculator{

    public int cal(String formula) {
        //연산자가 2개 연속하는 패턴 처리
        formula = formula.replace("--", "+").replace("+-", "-");
        //최초 음수 확인 변수
        boolean minusCheck = false;

        if (formula.startsWith("-")) {
            formula = formula.substring(1);
            minusCheck = true;
        }

        //숫자 추출
        String[] numArr = formula.split("[+\\-*/]");

        //첫번째 정수가 음수인 경우 별도 처리
        if(minusCheck) {
            numArr[0] = "-" + numArr[0];
        }

        //연산자 추출
        String[] formArr = formula.split("[0-9]+");

        //정수 - 연산자 - 정수 - 연산자 - 정수 패턴으로 반복 : 연산자의 인덱스와 정수의 인덱스를 활용하여 연산할 수 있음

        //리스트로 변환
        //정수 리스트
        List<String> numList = new ArrayList<>(Arrays.asList(numArr));
        //연산자 리스트
        List<String> formList = new ArrayList<>(Arrays.asList(formArr));

        //연산자 리스트에서 곱셈, 나눗셈 연산자를 찾아서 연산 후 리스트에서 제거
        for(int i = 1; i < formList.size(); i++) {
            if(formList.get(i).equals("*") || formList.get(i).equals("/")) {
                //연산자 index 1은 정수 index 0, 1을 연산하는데 사용됨
                int num0 = Integer.parseInt(numList.get(i - 1));
                int num1 = Integer.parseInt(numList.get(i));
                int result = 0;

                if(formList.get(i).equals("*")) {
                    result = num0 * num1;
                } else {
                    result = num0 / num1;
                }

                //연산 결과를 정수 리스트에 저장(기존 값을 제거 후 연산 결과 저장)하고 연산자는 제거
                numList.set(i - 1, String.valueOf(result));
                numList.remove(i);
                formList.remove(i);
                //연산자가 하나 제거되었기 때문에 기존 연산자 리스트의 인덱스에서 1씩 줄어들었음
                i--;
            }
        }

        //연산자 리스트에서 덧셈, 뺄셈 연산자를 찾아서 순서대로 연산 후 리스트에서 제거
        for(int i = 1; i < formList.size(); i++) {
            if(formList.get(i).equals("+") || formList.get(i).equals("-")) {
                //연산자 index 1은 정수 index 0, 1을 연산하는데 사용됨
                int num0 = Integer.parseInt(numList.get(i - 1));
                int num1 = Integer.parseInt(numList.get(i));
                int result = 0;

                if(formList.get(i).equals("+")) {
                    result = num0 + num1;
                } else {
                    result = num0 - num1;
                }

                numList.set(i - 1, String.valueOf(result));
                numList.remove(i);
                formList.remove(i);
                i--;
            }
        }

        return Integer.parseInt(numList.get(0));
    }
}

class Parenthesis {

    boolean solution(String s) {
        boolean answer = true;

        //문자열의 길이가 100000을 초과하거나 (또는 )가 아닌 문자가 포함되어 있으면 false
        if(s.length() > 100000 || !s.matches("^[\\(\\)]*$")) {
            return false;
        }

        //괄호는 반드시 (로 시작
        if(!s.startsWith("(")) {
            return false;
        }

        int left = 0;
        int right = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                left++;

            }else {
                right++;
            }
        }

        if(left != right) {
            answer = false;
        }

        return answer;
    }
}