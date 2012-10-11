package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;

import cpu.CPU;

public class MainApp implements ActionListener, KeyListener {

	static private File file = new File("src/prog.txt");
	static private Scanner scanner;

	private JFrame mainFrame;
	private JTextArea tA_R3;
	private JTextArea tA_R2;
	private JTextArea tA_R6;
	private JTextArea tA_SR;
	private JTextArea tA_memory;
	private JTextArea tA_R5;
	private JTextArea tA_R7;
	private JTextArea tA_Acc;
	private JTextArea tA_R1;
	private JTextArea tA_Keyboard;
	private JTextArea tA_PC;
	private JTextArea tA_R4;
	private JTextArea tA_VB;
	private JTextArea tA_Display;
	private JTextArea tA_R0;
	private JTextArea tA_IR;
	private CPU cpu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					scanner = new Scanner(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					MainApp window = new MainApp();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});




	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		cpu = new CPU(scanner);

		mainFrame = new JFrame();
		mainFrame.setTitle("HGH SRC 5.0 Simulator");
		mainFrame.setBounds(100, 100, 435, 480);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 427, 453);
		mainFrame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		JLabel label = new JLabel("IR");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 20, 30, 20);
		panel.add(label);

		JLabel label_1 = new JLabel("PC");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 50, 30, 20);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Acc");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 80, 30, 20);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Registers");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(54, 141, 60, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("R0");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 170, 30, 20);
		panel.add(label_4);

		JLabel label_5 = new JLabel("R1");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 200, 30, 20);
		panel.add(label_5);

		JLabel label_6 = new JLabel("R2");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 230, 30, 20);
		panel.add(label_6);

		JLabel label_7 = new JLabel("R3");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(10, 260, 30, 20);
		panel.add(label_7);

		JLabel label_8 = new JLabel("R4");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(10, 290, 30, 20);
		panel.add(label_8);

		JLabel label_9 = new JLabel("R5");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(10, 320, 30, 20);
		panel.add(label_9);

		JLabel label_10 = new JLabel("R6");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(10, 350, 30, 20);
		panel.add(label_10);

		JLabel label_11 = new JLabel("R7");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(10, 380, 30, 20);
		panel.add(label_11);

		JLabel label_12 = new JLabel("SR");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(10, 110, 30, 20);
		panel.add(label_12);

		JLabel label_13 = new JLabel("Memory");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(326, 85, 60, 20);
		panel.add(label_13);

		JLabel label_14 = new JLabel("Vector Buffer");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(167, 132, 80, 20);
		panel.add(label_14);

		JLabel label_15 = new JLabel("Keyboard");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(166, 186, 80, 20);
		panel.add(label_15);

		JLabel label_16 = new JLabel("Display");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(166, 261, 80, 20);
		panel.add(label_16);

		tA_IR = new JTextArea();
		tA_IR.setEditable(false);
		tA_IR.setBounds(44, 20, 146, 20);
		panel.add(tA_IR);

		tA_PC = new JTextArea();
		tA_PC.setEditable(false);
		tA_PC.setBounds(44, 50, 86, 20);
		panel.add(tA_PC);

		tA_Acc = new JTextArea();
		tA_Acc.setEditable(false);
		tA_Acc.setBounds(44, 80, 86, 20);
		panel.add(tA_Acc);

		tA_SR = new JTextArea();
		tA_SR.setEditable(false);
		tA_SR.setBounds(44, 110, 86, 20);
		panel.add(tA_SR);

		tA_R0 = new JTextArea();
		tA_R0.setEditable(false);
		tA_R0.setBounds(44, 170, 86, 20);
		panel.add(tA_R0);

		tA_R1 = new JTextArea();
		tA_R1.setEditable(false);
		tA_R1.setBounds(44, 200, 86, 20);
		panel.add(tA_R1);

		tA_R2 = new JTextArea();
		tA_R2.setEditable(false);
		tA_R2.setBounds(44, 230, 86, 20);
		panel.add(tA_R2);

		tA_R3 = new JTextArea();
		tA_R3.setEditable(false);
		tA_R3.setBounds(44, 260, 86, 20);
		panel.add(tA_R3);

		tA_R4 = new JTextArea();
		tA_R4.setEditable(false);
		tA_R4.setBounds(44, 290, 86, 20);
		panel.add(tA_R4);

		tA_R5 = new JTextArea();
		tA_R5.setEditable(false);
		tA_R5.setBounds(44, 320, 86, 20);
		panel.add(tA_R5);

		tA_R6 = new JTextArea();
		tA_R6.setEditable(false);
		tA_R6.setBounds(44, 350, 86, 20);
		panel.add(tA_R6);

		tA_R7 = new JTextArea();
		tA_R7.setEditable(false);
		tA_R7.setBounds(44, 380, 86, 20);
		panel.add(tA_R7);

		tA_VB = new JTextArea();
		tA_VB.setEditable(false);
		tA_VB.setBounds(166, 155, 86, 20);
		panel.add(tA_VB);

		tA_Keyboard = new JTextArea();
		tA_Keyboard.setEditable(false);
		tA_Keyboard.setBounds(166, 219, 86, 20);
		panel.add(tA_Keyboard);

		tA_Display = new JTextArea();
		tA_Display.setEditable(false);
		tA_Display.setBounds(166, 290, 86, 20);
		panel.add(tA_Display);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(299, 113, 118, 317);
		panel.add(scrollPane);

		tA_memory = new JTextArea();
		tA_memory.setEditable(false);
		scrollPane.setViewportView(tA_memory);

		JButton b_STEP = new JButton("STEP");
		b_STEP.setToolTipText("Run one step of the program.");
		b_STEP.setActionCommand("STEP");
		b_STEP.setBounds(348, 31, 69, 39);
		panel.add(b_STEP);

		JButton b_RUN = new JButton("RUN");
		b_RUN.setToolTipText("Run the remainder of the program.");
		b_RUN.setActionCommand("RUN");
		b_RUN.setBounds(251, 31, 69, 39);
		panel.add(b_RUN);
		
		panel.addKeyListener(this);
		b_STEP.addActionListener(this);
		b_RUN.addActionListener(this);

		update();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("RUN".equals(e.getActionCommand())){
			cpu.run();
			update();
		}
		else{
			cpu.step();
			update();
		}

	}

	public void update(){
		tA_IR.setText(cpu.IR);
		tA_PC.setText(cpu.PC);
		tA_Acc.setText(cpu.acc.acc_value);
		tA_SR.setText(cpu.SR.getSR());
		tA_R0.setText(cpu.R0);
		tA_R1.setText(cpu.R1);
		tA_R2.setText(cpu.R2);
		tA_R3.setText(cpu.R3);
		tA_R4.setText(cpu.R4);
		tA_R5.setText(cpu.R5);
		tA_R6.setText(cpu.R6);
		tA_R7.setText(cpu.R7);
		tA_VB.setText(cpu.VBuff);
		mem_update();

	}

	public void mem_update(){
		String[] arr = cpu.mem.mem_array;
		tA_memory.setText("");
		for (int i=0; i < arr.length; i = i+2){
			tA_memory.append(String.format("%02X", i) + ": " + arr[i+1]+arr[i] +"\n");
		}


	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		cpu.store_input_key(e.getKeyChar());
		update();
	}

}

