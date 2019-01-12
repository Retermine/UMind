package version1;

import java.awt.Cursor;
import java.awt.event.*;


/*
 *Panel的鼠标事件处理类
*/
class PanMouseListener  extends MouseAdapter{
	private boolean isClickRightAndDrag = false;
	public PaintePanel pan = null;
	int oldX = 0;
	int oldY = 0;

	public PanMouseListener(PaintePanel pan) {
		this.pan = pan;
	}

	public void mouseClicked(MouseEvent e) {
		clearBoxs();
		int clickType = e.getButton();
		if (clickType == e.BUTTON3) {// 右键点击
			PaintePanel.rightClickMenu.componentE = e;// 传递产生右键菜单的组件位置，确定新建主题的位置
			PaintePanel.rightClickMenu.rightMenuForPan.show(MainWindow.pan, e.getX(), e.getY());
			;
		} else if (e.getClickCount() == 2)// 双击
		{
			ThemeLabel themeLabel = new ThemeLabel(e.getX(), e.getY());
			/*********** 新建主题时建立连接关系 ***********/
			new ThemeDetect(this.pan).addConnect(themeLabel);
			themeLabel.setRank(themeLabel.getRank());
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == e.BUTTON3) {
			this.oldX = e.getX();
			this.oldY = e.getY();
			isClickRightAndDrag = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clearBoxs();
		if (isClickRightAndDrag && e.getButton() == e.BUTTON3) {
			int x = e.getX();
			int y = e.getY();

			new ThemeDetect(MainWindow.pan).moveAll(x - this.oldX, y - this.oldY);
			isClickRightAndDrag = false;

			this.pan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isClickRightAndDrag) {

			int x = e.getX();
			int y = e.getY();

			new ThemeDetect(MainWindow.pan).moveAll(x - this.oldX, y - this.oldY);
			this.oldX = x;
			this.oldY = y;

			this.pan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	public void clearBoxs() {
		if (PaintePanel.rightClickMenu != null) {
			PaintePanel.rightClickMenu.fontTypeBox.setVisible(false);
			PaintePanel.rightClickMenu.fontColorBox.setVisible(false);
			PaintePanel.rightClickMenu.fontSizeBox.setVisible(false);
		}
	}
	


}
