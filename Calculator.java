import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {
	public static void main(String[] args) {
		CalFrame calframe = new CalFrame();
		calframe.show();
	}
	
}

class CalFrame implements ActionListener {  //������
	private JFrame  mainFrame;              //���ڶ���
	private JTextField text;                //�ı���
 	private JButton[] buttons;				//��ť��
 	private char modifier = '\0';           //�����
 	private double result;                  //������
 	private boolean flag = false;           //����Ƿ񵥻����������ť
 	public CalFrame() {
 		mainFrame = new JFrame("������v1.0"); //������Ҫ���
 		text = new JTextField();
 		buttons = new JButton[16];
 		init();
 		setFontAndColor();                   //�����������ɫ
 		addEventHandle();                    //����¼�
 	}
	private void init() {                    //��ʼ������
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(4, 4, 2, 2)); //����
		text = new JTextField(25);
		north.add(text);
		String str = "123+456-789*0.=/";     //���ַ�������ʾ���а�ť�ַ�
		for(int i = 0; i < 16; i++) {
			JButton jb = new JButton(String.valueOf(str.charAt(i)));
			buttons[i] = jb;
			center.add(jb);
		}
		mainFrame.add(north,BorderLayout.NORTH);
		mainFrame.add(center,BorderLayout.CENTER);
	}
	
	public void show() {						//��ʾ����
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setFontAndColor() {           //�����������ɫ
		Font f = new Font("����", Font.BOLD, 20);
		text.setFont(f);
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(f);
			buttons[i].setForeground(Color.BLUE);
			
		}
	}
	public void addEventHandle() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(this); // Ϊÿ����ť��Ӽ���������
		}
	}
	//ʵ��ActionListener�ӿڣ����ɷ��ʱ��������������
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand(); //��ð�ť�ϵ��ı�
		if("0123456789.".indexOf(str) != -1) { //�����������
			if(flag) {                    //����Ѿ��������������ť
				text.setText("");          //�ı���Ϊ��
				flag = false;
			}
			text.setText(text.getText() + str);  //�����ַ�
		} else if("+-*/".indexOf(str) != -1) { //�����������ť
			modifier = str.charAt(0);
			double num = Double.valueOf(text.getText());
			result = num;
			flag = true;
		} else if(str.charAt(0) == '=') {   //�����������ť
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