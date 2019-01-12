package version1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*********主题修改及备注实现******************/
public class ThemeFrame extends JFrame {
	private ThemeLabel owner = null;
	private JTextField input = null;
	private ThemeFrame me = null;
	private JTextArea remark = null;
	private JButton button1 = null;
	private JButton button2 = null;
	private String remarkContent = null;
	private JTextField link=null;

	public ThemeFrame(ThemeLabel from) {
		me = this;
		this.owner = from;
		this.setSize(Constent.themeFrameWidth, Constent.themeFrameHeight);
		this.setLocation((Constent.frameWidth - Constent.themeFrameWidth) / 2,
				(Constent.frameHeight - Constent.themeFrameHeight) / 2);
		this.setIconImage(new ImageIcon(Constent.imagesPath+"\\frame\\newfire.png").getImage());
		this.setTitle("编辑");
		this.setLayout(null);
		this.setBackground(Color.CYAN);

		Font myFont = new Font("黑体", Font.BOLD, 30);
		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 580, 50);
		
		pan1.setBorder(BorderFactory.createTitledBorder("标签内容"));
		pan1.setLayout(new GridLayout(1, 1));
		input = new JTextField("主题");
		pan1.add(input);

		JPanel pan2 = new JPanel();
		pan2.setBounds(0, 50, 580, 200);
		pan2.setBorder(BorderFactory.createTitledBorder("备注内容"));
		pan2.setLayout(new GridLayout(1, 1));
		remark = new JTextArea();
		remark.setLineWrap(true);
		JScrollPane scr = new JScrollPane(remark, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pan2.add(scr);

		JPanel pan3 = new JPanel();
		pan3.setBounds(0, 250, 580, 50);
		pan3.setBorder(BorderFactory.createTitledBorder("链接地址"));
		pan3.setLayout(new GridLayout(1, 1));
		link = new JTextField("");
		pan3.add(link);
		
		button1 = new JButton("确定");
		button1.setBounds(180, 300, 100, 40);
		button1.setBorder(BorderFactory.createRaisedBevelBorder());
		button1.setFont(myFont);
		button1.addActionListener(new ThemeFrameActionListener(this));
		
		button2 = new JButton("取消");
		button2.setBounds(320, 300, 100, 40);
		button2.setBorder(BorderFactory.createRaisedBevelBorder());
		button2.setFont(myFont);
		button2.addActionListener(new ThemeFrameActionListener(this));

		this.add(pan1);
		this.add(pan2);
		this.add(pan3);
		this.add(button1);
		this.add(button2);
		
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(false);
		this.setResizable(false);

	}

	public String getRemarkContent() {
		return remarkContent;
	}

	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}

	public JTextField getInput() {
		return input;
	}

	public void setInput(JTextField input) {
		this.input = input;
	}

	public ThemeFrame getMe() {
		return me;
	}

	public void setMe(ThemeFrame me) {
		this.me = me;
	}

	public JTextArea getRemark() {
		return remark;
	}

	public void setRemark(JTextArea remark) {
		this.remark = remark;
	}

	public JButton getButton1() {
		return button1;
	}
	
	public JButton getButton2() {
		return button2;
	}

	public void setButton1(JButton button1) {
		this.button1 = button1;
	}

	public ThemeLabel getowner() {
		return owner;
	}

	public void setOwner(ThemeLabel owner) {
		this.owner = owner;
	}

	public JTextField getLink() {
		return link;
	}

	public void setLink(JTextField link) {
		this.link = link;
	}
	
}

class ThemeFrameActionListener implements ActionListener {
	private ThemeFrame from = null;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == from.getButton1()) {
			from.getowner().setText(from.getInput().getText());
			from.setRemarkContent(from.getRemark().getText());
			int deltaSizex=from.getowner().getNewLength()-from.getowner().getSizeX();
			from.getowner().updateSize();
			from.getowner().setLinkURL(from.getLink().getText());
			new ThemeDetect(MainWindow.pan).themesizeChangeMove(from.getowner(),deltaSizex);
			
		}
		else if(e.getSource() ==from.getButton2()) {
			
		}
		from.setVisible(false);
		
	}

	public ThemeFrameActionListener(ThemeFrame from) {
		super();
		this.from = from;
	}
}
