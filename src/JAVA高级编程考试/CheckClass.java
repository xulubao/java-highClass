package JAVA高级编程考试;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 检验班级是否合法
 * @author XuLuBao
 * @version V1.0
 * @Package JAVA高级编程考试
 * @date 2020/9/2 10:15
 */
public class CheckClass {
    public static void main(String[] args) {
        CheckClass demo = new CheckClass();
        String className = demo.inputName();
        if (demo.checkName(className)){
            System.out.println("合法");
        }else {
            System.out.println("不合法");
        }
    }

    /**
     * 从控制台输入班级
     * @return 班级
     */
    public String inputName(){
        System.out.print("请输入要查询的班级：");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.next();
        return className;
    }

    /**
     * 检验班级是否合法
     * @param className 班级名称
     * @return 合法返回true，不合法返回false
     */
    public boolean checkName(String className){
        String regx = "^((YF)|(TR))\\d{4}$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(className);
        if (matcher.find()){
            return true;
        }else {
            return false;
        }
    }
}
