package version1;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

public class nodeListener implements MouseInputListener{
	
	Point start=null;
	boolean isClickLeftAndDrag=false;
	
 CurveLine curveLine=null;
 public nodeListener(CurveLine curveLine) {
	 this.curveLine=curveLine;
 }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int clickType =e.getButton();
		if(clickType==e.BUTTON3) {//右键点击
			PaintePanel.rightClickMenu.rightMenuForNode.show(MainWindow.pan, ((JLabel)e.getSource()).getX(),((JLabel)e.getSource()).getY());
			PaintePanel.rightClickMenu.curveLine=this.curveLine;//传递产生右键菜单的连接线
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getButton()==e.BUTTON1) {
			start=e.getPoint();
			isClickLeftAndDrag=true;
			
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//((JLabel)e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		if(isClickLeftAndDrag) {
			JLabel node=(JLabel)e.getSource();
			Point now=e.getPoint();
			Point p=new Point(now.x-start.x+node.getX(),now.y-start.y+node.getY());
			if(node==this.curveLine.getNode1()) {
				
				this.curveLine.updateNode1(p);
			}
			else if(node==this.curveLine.getNode2()) {
				this.curveLine.updateNode2(p);
			}

		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isClickLeftAndDrag&&e.getButton()==e.BUTTON1) {
			JLabel node=(JLabel)e.getSource();
			Point now=e.getPoint();
			Point p=new Point(now.x-start.x+node.getX(),now.y-start.y+node.getY());
			if(node==this.curveLine.getNode1()) {
				
				this.curveLine.updateNode1(p);
			}
			else if(node==this.curveLine.getNode2()) {
				this.curveLine.updateNode2(p);
			}
	        isClickLeftAndDrag=false;
	      
		}	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
