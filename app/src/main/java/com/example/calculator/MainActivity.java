package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.format.DateUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TextView tv_result;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private Button btn_five;
    private Button btn_six;
    private Button btn_seven;
    private Button btn_eight;
    private Button btn_nine;
    private Button btn_zero;
    private Button btn_result;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_multiply;
    private Button btn_divide;
    private Button btn_clear;
    private Button btn_backspace;
    private Button btn_percent;
    private Button btn_dot;
    private Button btn_negative;
    // 震动服务
    private Vibrator vibrator;

    // 全局状态 第一次输入/第二次输入/第三次输入/待输出结果
    private int inputStatus = 1;
    private int target;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        tv_result = findViewById(R.id.tv_result);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_zero = findViewById(R.id.btn_zero);
        btn_result = findViewById(R.id.btn_result);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_clear = findViewById(R.id.btn_clear);
        btn_backspace = findViewById(R.id.btn_backspace);
        btn_percent = findViewById(R.id.btn_percent);
        btn_dot = findViewById(R.id.btn_dot);
        btn_negative = findViewById(R.id.btn_negative);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // clear
        btn_clear.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            tv_result.setText("");
        });
        // calculate
        btn_result.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String expr = (String) tv_result.getText();
            String ans = calculateResult(expr);
            if (ans.equals("0不能作为除数")) {
                Toast.makeText(this, ans, Toast.LENGTH_SHORT).show();
                return;
            }
            tv_result.setText(ans);
        });
        // numbers
        btn_one.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "1");
            tv_result.setText(expr);
        });
        btn_two.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "2");
            tv_result.setText(expr);
        });
        btn_three.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "3");
            tv_result.setText(expr);
        });
        btn_four.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "4");
            tv_result.setText(expr);
        });
        btn_five.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "5");
            tv_result.setText(expr);
        });
        btn_six.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "6");
            tv_result.setText(expr);
        });
        btn_seven.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "7");
            tv_result.setText(expr);
        });
        btn_eight.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "8");
            tv_result.setText(expr);
        });
        btn_nine.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "9");
            tv_result.setText(expr);
        });
        btn_zero.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (!originText.isEmpty()) {
                if (originText.charAt(originText.length() - 1) == ')' || originText.charAt(originText.length() - 1) == '%') {
                    Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            String expr = String.format("%s%s", tv_result.getText(), "0");
            tv_result.setText(expr);
        });
        // dot
        btn_dot.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0' || lastContainsDot(originText)) {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, ".");
            tv_result.setText(expr);
        });
        //backspace
        btn_backspace.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty()) {
                return;
            }
            String expr = (String) originText.subSequence(0, originText.length() - 1);
            tv_result.setText(expr);
        });
        // plus
        btn_plus.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || ((originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') && originText.charAt(originText.length() - 1) != '%' && originText.charAt(originText.length() - 1) != ')')) {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, "+");
            tv_result.setText(expr);
        });
        // minus
        btn_minus.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty()) {
                tv_result.setText("-");
                return;
            }
            if ((originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') && originText.charAt(originText.length() - 1) != '%' && originText.charAt(originText.length() - 1) != ')') {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, "-");
            tv_result.setText(expr);
        });
        // multiply
        btn_multiply.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || ((originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') && originText.charAt(originText.length() - 1) != '%' && originText.charAt(originText.length() - 1) != ')')) {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, "×");
            tv_result.setText(expr);
        });
        // divide
        btn_divide.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || ((originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') && originText.charAt(originText.length() - 1) != '%' && originText.charAt(originText.length() - 1) != ')')) {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, "÷");
            tv_result.setText(expr);
        });
        // percent
        btn_percent.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = String.format("%s%s", originText, "%");
            tv_result.setText(expr);
        });
        btn_negative.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            String originText = (String) tv_result.getText();
            if (originText.isEmpty() || ((originText.charAt(originText.length() - 1) > '9' || originText.charAt(originText.length() - 1) < '0') && originText.charAt(originText.length() - 1) != '%')) {
                Toast.makeText(this, "语法错误", Toast.LENGTH_SHORT).show();
                return;
            }
            String expr = wrapByNegative(originText);
            tv_result.setText(expr);
        });
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        tv_result = findViewById(R.id.tv_result);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_zero = findViewById(R.id.btn_zero);
        btn_result = findViewById(R.id.btn_result);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_clear = findViewById(R.id.btn_clear);
        btn_backspace = findViewById(R.id.btn_backspace);
        btn_percent = findViewById(R.id.btn_percent);
        btn_dot = findViewById(R.id.btn_dot);
        btn_negative = findViewById(R.id.btn_negative);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // clear
        btn_clear.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            inputStatus = 1;
            tv_result.setText("");
        });
        btn_zero.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s",tv_result.getText(), "0"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_one.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s",tv_result.getText(), "1"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_two.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s",tv_result.getText(), "2"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_three.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s",tv_result.getText(), "3"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_four.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "4"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_five.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "5"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_six.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "6"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_seven.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "7"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_eight.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "8"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_nine.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            if (inputStatus == 1 || inputStatus == 2) {
                tv_result.setText(String.format("%s%s", tv_result.getText(), "9"));
            }else if (inputStatus == 3) {
                int param1 = Integer.parseInt(tv_result.getText().toString().substring(0, 4));
                int param2 = Integer.parseInt(tv_result.getText().toString().substring(5, 10));
                int param3 = Integer.parseInt(compactTimestampWithPadding(LocalDateTime.now()));
                tv_result.setText(String.format("%s%s", tv_result.getText(), param3 - param1 - param2));
                inputStatus++;
            }
        });
        btn_plus.setOnClickListener(v -> {
            // 执行震动
            performVibrate();
            inputStatus++;
            tv_result.setText(String.format("%s%s", tv_result.getText(), "+"));
        });
        btn_result.setOnClickListener(v-> {
            // 执行震动
            performVibrate();
            String expr = (String) tv_result.getText();
            String ans = calculateResult(expr);
            tv_result.setText(ans);
        });

    }
    public static String compactTimestampWithPadding(LocalDateTime dt) {
        // 1. 定义东八区时区（推荐使用 Asia/Shanghai，而非 UTC+8，兼容性更好）
        ZoneId east8Zone = ZoneId.of("Asia/Shanghai");
        // 2. 定义原始 LocalDateTime 所属的时区（这里假设原始时间是系统默认时区，你可根据实际修改）
        ZoneId defaultZone = ZoneId.systemDefault();

        // 3. 将 LocalDateTime 转换为默认时区的 ZonedDateTime，再转换为东八区时间
        ZonedDateTime zonedDt = dt.atZone(defaultZone);
        ZonedDateTime east8Dt = zonedDt.withZoneSameInstant(east8Zone);

        // 4. 从东八区时间中提取字段，按原有格式补零格式化
        return String.format("%02d%02d%02d%02d",
                east8Dt.getMonthValue(),    // 月份补零（%02d）
                east8Dt.getDayOfMonth(),    // 日期补零
                east8Dt.getHour(),          // 小时补零
                east8Dt.getMinute());       // 分钟补零
    }

// 示例（2026-02-07 02:05） -> "20270205" 或者如果改成 %02d %02d ... -> "02070205"

    /**
     * 执行单次震动（API≥26）
     * 时长30ms：按钮点击的舒适震感
     */
    private void performVibrate() {
        if (vibrator == null) return;
        // 判断设备是否支持震动
        if (vibrator.hasVibrator()) {
            // 创建单次震动效果
            VibrationEffect vibrationEffect = VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE);
            // 触发震动
            vibrator.vibrate(vibrationEffect);
        }
    }

    /**
     * 判断多个点的情况
     * @param expr
     * @return boolean
     */
    public static boolean lastContainsDot(String expr) {
        for(int i = expr.length() - 1; i >= 0; i--) {
            if (expr.charAt(i) == '.') {
                return true;
            }
            if (expr.charAt(i) < '0' || expr.charAt(i) > '9') {
                break;
            }
        }
        return false;
    }

    /**
     * 将最近的数字取反，如 1*2->1*(-2)
     * @param expr
     * @return negative last expr
     */
    public static String wrapByNegative(String expr) {
        int lastIndex = -1;
        for(int i = expr.length() - 1; i >= 0; i--) {
            if ((expr.charAt(i) < '0' || expr.charAt(i) > '9') && expr.charAt(i) != '.' && expr.charAt(i) != '%') {
                lastIndex = i;
                break;
            }
        }
        if (lastIndex == -1){
            return String.format("(-%s)", expr);
        }
        return String.format("%s(-%s)",expr.substring(0, lastIndex + 1), expr.substring(lastIndex + 1));
    }

    /**
     * 计算最终结果
     * @param expr
     * @return ans
     */
    public static String calculateResult(String expr) {
        // 处理非对称括号
        expr = processParentheses(expr);
        // 处理百分号
        expr = processPercent(expr);
        // 处理前导零, bigDecimal无需处理
        Stack<BigDecimal>operand = new Stack<>();
        Stack<Character>operator = new Stack<>();
        int lastOpIndex = -1;
        boolean lastIsParentheses = false;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+' ||expr.charAt(i) == '-' ||expr.charAt(i) == '×' ||expr.charAt(i) == '÷'){
                if (lastOpIndex + 1 == i) {
                    continue;
                }
                if (!lastIsParentheses) {
                    BigDecimal num = new BigDecimal(expr.substring(lastOpIndex + 1, i));
                    operand.push(num);
                }
                lastIsParentheses = false;
                if (!operator.isEmpty() && operator.peek() == '×') {
                    operator.pop();
                    BigDecimal arg1 = operand.pop();
                    BigDecimal arg2 = operand.pop();
                    BigDecimal res = arg1.multiply(arg2);
                    operand.push(res);
                }
                if (!operator.isEmpty() && operator.peek() == '÷') {
                    operator.pop();
                    BigDecimal arg1 = operand.pop();
                    BigDecimal arg2 = operand.pop();
                    if (arg1.toString().equals("0")) {
                        return "0不能作为除数";
                    }
                    BigDecimal res = arg2.divide(arg1, 10, RoundingMode.HALF_EVEN).stripTrailingZeros();
                    operand.push(res);
                }
                operator.push(expr.charAt(i));
                lastOpIndex = i;
            }else if(expr.charAt(i) == '(') {
                lastOpIndex = i;
            }else if(expr.charAt(i) == ')') {
                lastIsParentheses = true;
                BigDecimal num = new BigDecimal(expr.substring(lastOpIndex + 1, i));
                operand.push(num);
            }
        }
        if (!lastIsParentheses){
            operand.push(new BigDecimal(expr.substring(lastOpIndex + 1)));
        }


        if (!operator.isEmpty() && operator.peek() == '×') {
            operator.pop();
            BigDecimal arg1 = operand.pop();
            BigDecimal arg2 = operand.pop();
            BigDecimal res = arg1.multiply(arg2);
            operand.push(res);
        }
        if (!operator.isEmpty() && operator.peek() == '÷') {
            operator.pop();
            BigDecimal arg1 = operand.pop();
            BigDecimal arg2 = operand.pop();
            if (arg1.toString().equals("0")) {
                return "0不能作为除数";
            }
            BigDecimal res = arg2.divide(arg1, 10, RoundingMode.HALF_EVEN).stripTrailingZeros();
            operand.push(res);
        }
        while (!operator.isEmpty()) {
            if (operator.peek() == '+') {
                operator.pop();
                BigDecimal arg1 = operand.pop();
                BigDecimal arg2 = operand.pop();
                BigDecimal res = arg1.add(arg2);
                operand.push(res);
            }else if(operator.peek() == '-') {
                operator.pop();
                BigDecimal arg1 = operand.pop();
                BigDecimal arg2 = operand.pop();
                BigDecimal res = arg2.subtract(arg1);
                operand.push(res);
            }
        }
        return operand.pop().toString();
    }

    /**
     * 处理非对称括号
     * @param expr
     * @return ans
     */
    public static String processParentheses(String expr) {
        Stack<Integer> leftParenthesis = new Stack<>();
        Stack<Integer> rightParenthesis = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                leftParenthesis.add(i);
            }
            if (expr.charAt(i) == ')') {
                if (!leftParenthesis.isEmpty()) {
                    leftParenthesis.pop();
                }else {
                    rightParenthesis.add(i);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            if (!leftParenthesis.isEmpty() && leftParenthesis.get(0) == i) {
                leftParenthesis.remove(0);
            }else if(!rightParenthesis.isEmpty() && rightParenthesis.get(0) == i){
                rightParenthesis.remove(0);
            } else {
                ans.append(expr.charAt(i));
            }
        }
        return ans.toString();
    }

    /**
     * 处理百分号
     * @param expr
     * @return ans
     */
    public static String processPercent(String expr) {
        for(int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '%') {
                int lastIdx = -1;
                for(int j = i - 1; j >= 0; j--){
                    if ((expr.charAt(j) > '9' || expr.charAt(j) < '0') && expr.charAt(j) != '.') {
                        lastIdx = j;
                        break;
                    }
                }
                BigDecimal num = new BigDecimal(expr.substring(lastIdx + 1, i));
                num = num.divide(new BigDecimal("100"));
                int idx = expr.substring(i + 1).length();
                expr = String.format("%s%s%s", expr.substring(0, lastIdx + 1), num.toString(), expr.substring(i + 1));
                i = expr.length() - idx - 1;
            }
        }
        return expr;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vibrator != null) {
            vibrator.cancel(); // 取消所有未完成的震动
            vibrator = null;
        }
    }
}