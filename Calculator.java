import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {
	public static void main(String[] args) {
		CalFrame calframe = new CalFrame();
		calframe.show();
	}
	
}

class CalFrame implements ActionListener {  //窗口类
	private JFrame  mainFrame;              //窗口对象
	private JTextField text;                //文本框
 	private JButton[] buttons;				//按钮组
 	private char modifier = '\0';           //运算符
 	private double result;                  //运算结果
 	private boolean flag = false;           //标记是否单击了运算符按钮
 	public CalFrame() {
 		mainFrame = new JFrame("计算器v1.0"); //创建必要组件
 		text = new JTextField();
 		buttons = new JButton[16];
 		init();
 		setFontAndColor();                   //设置字体和颜色
 		addEventHandle();                    //添加事件
 	}
	private void init() {                    //初始化操作
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(4, 4, 2, 2)); //布局
		text = new JTextField(25);
		north.add(text);
		String str = "123+456-789*0.=/";     //用字符串来表示所有按钮字符
		for(int i = 0; i < 16; i++) {
			JButton jb = new JButton(String.valueOf(str.charAt(i)));
			buttons[i] = jb;
			center.add(jb);
		}
		mainFrame.add(north,BorderLayout.NORTH);
		mainFrame.add(center,BorderLayout.CENTER);
	}
	
	public void show() {						//显示窗口
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setFontAndColor() {           //设置字体和颜色
		Font f = new Font("黑体", Font.BOLD, 20);
		text.setFont(f);
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(f);
			buttons[i].setForeground(Color.BLUE);
			
		}
	}
	public void addEventHandle() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this); // 为每个按钮添加监听器对象
		}
	}
	//实现ActionListener接口，即可访问本对象的所有属性
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand(); //获得按钮上的文本
		if("0123456789.".indexOf(str) != -1) { //输入的是数字
			if(flag) {                    //如果已经单击了运算符按钮
				text.setText("");          //文本框为空
				flag = false;
			}
			text.setText(text.getText() + str);  //设置字符
		} else if("+-*/".indexOf(str) != -1) { //单击运算符按钮
			modifier = str.charAt(0);
			double num = Double.valueOf(text.getText());
			result = num;
			flag = true;
		} else if(str.charAt(0) == '=') {   //单击运算符按钮
			if(modifier == '+') {
				double num = Double.valueOf(text.getText());
				result += num;
			}
			if(modifier == '-') {
				double num = Double.valueOf(text.getText());
				result -= num;
			}
			if(modifier == '*') {
				double num = Double.valueOf(text.getText());
				result *= num;
			}
			if(modifier == '/') {
				double num = Double.valueOf(text.getText());
				result /= num;
			}
			text.setText(result + "");
			modifier = '\0';
			result = 0;
		}
	}
}