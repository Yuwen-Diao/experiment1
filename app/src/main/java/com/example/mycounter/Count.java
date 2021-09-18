package com.example.mycounter;

import android.util.Log;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Count {
    /**
     * 大致思路是利用栈的出栈入栈压栈来处理一个运算式的先后级排序，进行相映的运算.
     */
    String TAG = "error";
    //数字栈：用于存储表达式中的各个数字
    private Stack<Double> numberStack = null;
    //符号栈：用于存储运算符和括号
    private Stack<Character> symbolStack = null;

    private List<Character> validChar;

    private int scale;//除法时出现循环小数保留精度

    public Count(int scale) {
        super();
        this.scale = scale;
        validChar = Arrays.asList('(', ')', 'F', 'Z', '+', '-', '×', '*', '÷', '/', '=', '^', '✔', 's', 'c');
    }

    public Count() {
        this(32);
    }

    /**
     * 从外面把字符串传递进来！！！
     */
    public Double caculate(String numStr) {
        //判断算数表示是否结束，也就是判断结尾有木有=号！没有给加上！
        //equals方法：值的比较
        //charAt方法：检索方法
        if (numStr.length() > 1 && numStr.charAt(numStr.length() - 1) != '=') {
            numStr += "=";
        }
        //检查表达式是否是正确的！利用Standard方法(自定义)
        if (!isStandard(numStr)) {
            String isstandardtext;
            isstandardtext = "出错";
            return null;
        }
        // 初始化栈
        if (numberStack == null) {
            numberStack = new Stack<Double>();
        }
        numberStack.clear();
        if (symbolStack == null) {
            symbolStack = new Stack<>();
        }
        symbolStack.clear();

        //System.out.println("输出==>" + numStr);

        /**！！！！！！！！！！核 心！！！！！！！！！！！！*/
        //创建一个StringBuffer，用来放多位的数字
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            // 获取一个字符
            char ch = numStr.charAt(i);

            // 若当前字符是数字
            if (isNumber(ch)) {
                // 加入到数字缓存中
                temp.append(ch);
            } else { // 非数字的情况
                // 将数字缓存转为字符串
                String tempStr = temp.toString();
                if (!tempStr.isEmpty()) {
                    double num = Double.parseDouble(tempStr);
                    // 将数字压栈
                    numberStack.push(num);
                    // 重置数字缓存
                    temp = new StringBuffer();
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!symbolStack.empty() && !comparePri(ch)) {
                    // 出栈，取出数字，后进先出
                    char t = symbolStack.pop();
                    if ('s' == t || 'c' == t || '✔' == t) {
                        double a = numberStack.pop();
                        // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                        switch (t) {
                            case 's':
                                numberStack.push(Math.sin(a));
                                break;
                            case 'c':
                                numberStack.push(Math.cos(a));
                                break;
                            case '✔':
                                numberStack.push(Math.sqrt(a));
                                break;
                            default:
                                break;
                        }
                    } else {
                        double b = numberStack.pop();
                        double a = numberStack.pop();
                        // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                        switch (t) {
                            case '+':
                                numberStack.push(a + b);
                                break;
                            case '-':
                                numberStack.push(a - b);
                                break;
                            case '*':
                            case '×':
                                numberStack.push(a * b);
                                break;
                            case '/':
                            case '÷':
                                numberStack.push(a / b);
                                break;
                            case '^':
                                numberStack.push(Math.pow(a, b));
                                break;
                            default:
                                break;
                        }
                    }

                } // while循环结束

                if (ch != '=') {
                    // 符号入栈
                    symbolStack.push(new Character(ch));
                    // 去括号
                    if (ch == ')') {
                        symbolStack.pop();
                        symbolStack.pop();
                    }
                }
            }
        } // for循环结束

        // 返回计算结果
        return numberStack.pop();
    }


    /**
     * =================================检查算数表达式是否合格======================================
     */
    private boolean isStandard(String numStr) {
        // 表达式不能为空
        if (numStr == null || numStr.isEmpty())
            return false;
        // 用来保存括号，检查左右括号是否匹配
        Stack<Character> stack = new Stack<Character>();
        // 用来标记'='符号是否存在多个
        boolean b = false;
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            if (!(isNumber(n) || validChar.contains(n))) {
                return false;
            }
            /*if ('s' == n || "c".equals(n)) {
                stack.push(n);
            }*/
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ('(' == n) {
                stack.push(n);
            }
            if (')' == n) { // 匹配括号
                if (stack.isEmpty() || '(' != stack.pop()) // 括号是否匹配
                    return false;
            }
            // 检查是否有多个'='号
            if ('=' == n) {
                if (b)
                    return false;
                b = true;
            }
        }
        // 可能会有缺少右括号的
        if (!stack.isEmpty())
            return false;
        // 检查'='号是否不在末尾
        if ('=' != numStr.charAt(numStr.length() - 1))
            return false;
        return true;
    }

    /**
     * =================================判断是否是0-9的数字========================================
     */
    private boolean isNumber(char num) {
        if ((num >= '0' && num <= '9') || num == '.')
            return true;
        return false;
    }

    /**
     * ==============比较优先级，如果当前运算符比栈顶元素运算符优先级高则返回true,否则返回false==========
     */
    private boolean comparePri(char symbol) {
        // 空栈返回ture
        /*if (symbolStack.empty()) {
            return true;
        }*/

        // 查看堆栈顶部的对象
        char top = symbolStack.peek();
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            // 优先级比'+'和'-'高
            case '×':
            case '^':
            case '*':
            case '/':
            case '÷':
            case 's':
            case '✔':
            case 'c':
                if (top == '+' || top == '-')
                    return true;
                else
                    return false;
            case '+':
            case '-':
                return false;
            // 优先级最低
            case ')':
                return false;
            // 结束符
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }
}