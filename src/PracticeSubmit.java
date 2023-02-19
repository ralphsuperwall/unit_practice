import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PracticeSubmit {

    public static void main(String[] args) {

        ParenthesisCheck parenthesis = new ParenthesisCheck();
        System.out.println(parenthesis.solution("((()())()))"));
    }
}

class ParenthesisCheck {

    boolean solution(String s) {
        boolean answer = true;

        //문자열의 길이가 100000을 초과하거나 (또는 )가 아닌 문자가 포함되어 있으면 false
        if(s.length() > 100000 || !s.matches("^[\\(\\)]*$")) {
            answer = false;
        }

        //괄호는 반드시 (로 시작
        if(!s.startsWith("(")) {
            answer = false;
        }

        //)가 (보다 많아지는 순간 규칙 위반, (는 아무리 많아져도 )와 마지막에 (와 )의 개수가 같으면 잘 닫힌 괄호
        int check = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') {
                check++;

            }else {
                check--;
            }

            if(check < 0) {
                answer = false;
            }
        }

        if(check != 0) {
            answer = false;
        }

        return answer;
    }
}
