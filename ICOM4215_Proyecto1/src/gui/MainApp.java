package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class MainApp implements ActionListener {

	static private File file = new File("src/prog.txt");
	static private Scanner scanner;

	private JFrame frmHghSrcSimulator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frmHghSrcSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	


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
		frmHghSrcSimulator = new JFrame();
		frmHghSrcSimulator.setTitle("HGH SRC Simulator");
		frmHghSrcSimulator.setBounds(100, 100, 435, 480);
		frmHghSrcSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHghSrcSimulator.getContentPane().setLayout(null);

		JLabel lblIr = new JLabel("IR");
		lblIr.setHorizontalAlignment(SwingConstants.CENTER);
		lblIr.setBounds(10, 21, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblIr);

		JLabel lblPc = new JLabel("PC");
		lblPc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPc.setBounds(10, 52, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblPc);

		JLabel lblAcc = new JLabel("Acc");
		lblAcc.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcc.setBounds(10, 83, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblAcc);

		JTextArea tA_IR = new JTextArea();
		tA_IR.setEditable(false);
		tA_IR.setBounds(50, 20, 146, 20);
		frmHghSrcSimulator.getContentPane().add(tA_IR);

		JTextArea tA_PC = new JTextArea();
		tA_PC.setEditable(false);
		tA_PC.setBounds(50, 51, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_PC);

		JTextArea tA_Acc = new JTextArea();
		tA_Acc.setEditable(false);
		tA_Acc.setBounds(50, 82, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_Acc);

		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisters.setBounds(60, 157, 60, 20);
		frmHghSrcSimulator.getContentPane().add(lblRegisters);

		JLabel lblR = new JLabel("R0");
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		lblR.setBounds(10, 189, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR);

		JTextArea tA_R0 = new JTextArea();
		tA_R0.setEditable(false);
		tA_R0.setBounds(50, 188, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R0);

		JTextArea tA_R1 = new JTextArea();
		tA_R1.setEditable(false);
		tA_R1.setBounds(50, 220, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R1);

		JTextArea tA_R2 = new JTextArea();
		tA_R2.setEditable(false);
		tA_R2.setBounds(50, 252, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R2);

		JTextArea tA_R3 = new JTextArea();
		tA_R3.setEditable(false);
		tA_R3.setBounds(50, 284, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R3);

		JTextArea tA_R4 = new JTextArea();
		tA_R4.setEditable(false);
		tA_R4.setBounds(50, 315, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R4);

		JTextArea tA_R5 = new JTextArea();
		tA_R5.setEditable(false);
		tA_R5.setBounds(50, 347, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R5);

		JTextArea tA_R6 = new JTextArea();
		tA_R6.setEditable(false);
		tA_R6.setBounds(50, 379, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R6);

		JTextArea tA_R7 = new JTextArea();
		tA_R7.setEditable(false);
		tA_R7.setBounds(50, 411, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_R7);

		JTextArea tA_SR = new JTextArea();
		tA_SR.setEditable(false);
		tA_SR.setBounds(50, 114, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_SR);

		JTextArea tA_VB = new JTextArea();
		tA_VB.setEditable(false);
		tA_VB.setBounds(173, 114, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_VB);

		JTextArea tA_Keyboard = new JTextArea();
		tA_Keyboard.setEditable(false);
		tA_Keyboard.setBounds(173, 164, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_Keyboard);

		JTextArea tA_Display = new JTextArea();
		tA_Display.setEditable(false);
		tA_Display.setBounds(173, 214, 80, 20);
		frmHghSrcSimulator.getContentPane().add(tA_Display);

		JTextArea tA_memory = new JTextArea();
		tA_memory.setEditable(false);
		tA_memory.setBounds(296, 114, 90, 318);
		frmHghSrcSimulator.getContentPane().add(tA_memory);

		JLabel lblR_1 = new JLabel("R1");
		lblR_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_1.setBounds(10, 221, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_1);

		JLabel lblR_2 = new JLabel("R2");
		lblR_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_2.setBounds(10, 253, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_2);

		JLabel lblR_3 = new JLabel("R3");
		lblR_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_3.setBounds(10, 285, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_3);

		JLabel lblR_4 = new JLabel("R4");
		lblR_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_4.setBounds(10, 316, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_4);

		JLabel lblR_5 = new JLabel("R5");
		lblR_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_5.setBounds(10, 348, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_5);

		JLabel lblR_6 = new JLabel("R6");
		lblR_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_6.setBounds(10, 380, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_6);

		JLabel lblR_7 = new JLabel("R7");
		lblR_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_7.setBounds(10, 412, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblR_7);

		JLabel lblSr = new JLabel("SR");
		lblSr.setHorizontalAlignment(SwingConstants.CENTER);
		lblSr.setBounds(10, 115, 30, 20);
		frmHghSrcSimulator.getContentPane().add(lblSr);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(386, 114, 16, 318);
		frmHghSrcSimulator.getContentPane().add(scrollBar);

		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemory.setBounds(316, 86, 60, 20);
		frmHghSrcSimulator.getContentPane().add(lblMemory);

		JLabel lblVectorBuffer = new JLabel("Vector Buffer");
		lblVectorBuffer.setHorizontalAlignment(SwingConstants.CENTER);
		lblVectorBuffer.setBounds(173, 89, 80, 20);
		frmHghSrcSimulator.getContentPane().add(lblVectorBuffer);

		JLabel lblKeyboard = new JLabel("Keyboard");
		lblKeyboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyboard.setBounds(173, 139, 80, 20);
		frmHghSrcSimulator.getContentPane().add(lblKeyboard);

		JLabel lblDisplay = new JLabel("Display");
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setBounds(173, 189, 80, 20);
		frmHghSrcSimulator.getContentPane().add(lblDisplay);

		JButton btnSTEP = new JButton("STEP");
		btnSTEP.setBounds(330, 21, 69, 39);
		frmHghSrcSimulator.getContentPane().add(btnSTEP);
		btnSTEP.setActionCommand("STEP");
		btnSTEP.setToolTipText("Run one step of the program.");

		JButton btnRUN = new JButton("RUN");
		btnRUN.setBounds(237, 21, 69, 39);
		frmHghSrcSimulator.getContentPane().add(btnRUN);
		btnRUN.setActionCommand("RUN");
		btnRUN.setToolTipText("Run the remainder of the program.");

		btnSTEP.addActionListener(this);
		btnRUN.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("RUN".equals(e.getActionCommand())){
			while(scanner.hasNextLine()){
				System.out.println(scanner.next());
			}
		}
		else{
			if(scanner.hasNextLine()){
				System.out.println(scanner.next());
			}
		}

	}

}

