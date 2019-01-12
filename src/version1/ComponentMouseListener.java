package version1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

/*
 * 组件的鼠标事件处理类
 */

class ComponentMouseListener implements MouseInputListener{

	int startX=0;
	int startY=0;
	ThemeLabel Label=null;
	JLabel Mask=null;
	boolean isClickLeftAndDrag =false;
	
	
	public ComponentMouseListener(ThemeLabel themeLabel) {
		this.Label=themeLabel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==e.BUTTON1) {
			this.startX=e.getX();
			this.startY=e.getY();
			isClickLeftAndDrag=true;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clearBoxs();
		if(e.getButton()==e.BUTTON1) {
			int x=e.getX();
			int y=e.getY();
			if((ThemeLabel)this.Label==MainWindow.pan.getrootThemeLabel()) {
				new ThemeDetect(MainWindow.pan).moveAll(x-this.startX,y-this.startY);
				this.Label.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else {
				new ThemeDetect(MainWindow.pan).updateConnect((ThemeLabel)this.Label, x-this.startX,y-this.startY);
			}
			
	        isClickLeftAndDrag=false;
	        
		}	

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(isClickLeftAndDrag) {

			int x=e.getX();
			int y=e.getY();
			if((ThemeLabel)this.Label==MainWindow.pan.getrootThemeLabel()) {
				new ThemeDetect(MainWindow.pan).moveAll(x-this.startX,y-this.startY);
				this.Label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			else {
				new ThemeDetect(MainWindow.pan).updateConnect((ThemeLabel)this.Label, x-this.startX,y-this.startY);
			}
			Mask.setLocation(Label.getX()-5, Label.getY()-5);	

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clearBoxs();
		int clickType =e.getButton();
		if(clickType==e.BUTTON3) {//右键点击
			PaintePanel.rightClickMenu.rightMenuForComponent.show(MainWindow.pan, e.getX()+this.Label.getX(), e.getY()+this.Label.getY());
			PaintePanel.rightClickMenu.componentE=e;//传递产生右键菜单的组件位置，确定Box的位置
		}
		else if(clickType==e.BUTTON1)
		{
			if(e.getClickCount()==1) {
				if(PaintePanel.rightClickMenu.getisCureLine()) {
					PaintePanel.rightClickMenu.oldcomponentE = PaintePanel.rightClickMenu.componentE;
					PaintePanel.rightClickMenu.componentE = e;
					
					ThemeLabel startLabel=(ThemeLabel)(PaintePanel.rightClickMenu.oldcomponentE.getSource());
					ThemeLabel endLabel=(ThemeLabel)(PaintePanel.rightClickMenu.componentE.getSource());
//					Point start=new Point(startLabel.getX(),startLabel.getY());
//					Point end=new Point(endLabel.getX(),endLabel.getY());
//					CurveLine curveLine=new CurveLine(start,end,startLabel,endLabel);
//					startLabel.addCurveLine(curveLine);
//					endLabel.addCurveLine(curveLine);
//					MainWindow.pan.addCurveLine(curveLine);
					
					new ThemeDetect(MainWindow.pan).addCurveLine(startLabel,endLabel);
					
					
					PaintePanel.rightClickMenu.setisCureLine(false);
					MainWindow.pan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			else if(e.getClickCount()==2)//双击
			{
				for (Entry<ThemeLabel, ConnectLine> item : MainWindow.pan.getallConnectLine().entrySet()) {
					ThemeLabel key = item.getKey();
					key.getMyThemeFrame().setVisible(false);
				}
				this.Label.getMyThemeFrame().setVisible(true);
				this.Label.getMyThemeFrame().setLocation((Constent.frameWidth-Constent.themeFrameWidth)/2,(Constent.frameHeight-Constent.themeFrameHeight)/2);
				this.Label.getMyThemeFrame().setState(JFrame.NORMAL);
				this.Label.getMyThemeFrame().show();
				this.Label.getMyThemeFrame().getInput().setText(this.Label.getText());
				this.Label.getMyThemeFrame().getRemark().setText(this.Label.getMyThemeFrame().getRemarkContent());
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Mask = new JLabel();
		Mask.setLocation(Label.getX()-5, Label.getY()-5);
		Mask.setSize(Label.getSize());
		Mask.setBounds(Label.getX()-5,Label.getY()-5, Label.getSizeX()+10, Label.getThemeSizeY()+10);
		Mask.setOpaque(true);
		if(PaintePanel.rightClickMenu.getisCureLine()) {
			Mask.setBackground(new Color(0,0,255,60));
		}else {
			Mask.setBackground(Constent.Maskbkg);
		}
		Mask.setBorder(BorderFactory.createLineBorder(Constent.Maskbd));
		
		MainWindow.pan.add(Mask);
		Mask.setVisible(true);
		
		//System.out.print(((ThemeLabel)e.getSource()).getRank());

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Mask.setVisible(false);
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub	
		//
	}
	public void clearBoxs() {
		if(PaintePanel.rightClickMenu!=null) {
			PaintePanel.rightClickMenu.fontTypeBox.setVisible(false);
			PaintePanel.rightClickMenu.fontColorBox.setVisible(false);
			PaintePanel.rightClickMenu.fontSizeBox.setVisible(false);
		}
	}

}
