/**
 * 
 */
package version1;

import java.awt.*;

import java.util.*;
import javax.swing.*;

/**
 * @author sheng
 *
 */
public class ThemeLabel extends JLabel {

	/******** 图标，继承JL Abel ********/
	private int iconNum = 0;
	/******* 父亲孩子 *****/
	private ThemeLabel father = null;
	private Vector<ThemeLabel> child = null;
	/*********连接线******/
	private Vector<CurveLine>CurveLineList=null;
	/******* 修改及备注类 *****/
	private ThemeFrame myThemeFrame = null;
	/********* 颜色 ********/
	private Color bdcolor = Color.BLUE;
	private Color bkgcolor = Color.GREEN;
	private Color txtcolor = Color.BLACK;
	/********* 尺寸 ********/
	private int ThemeSizeX = Constent.ThemeSizeX;
	private int ThemeSizeY = Constent.ThemeSizeY;
	private int ThemeLeftX, ThemeRightX, ThemeMidY;
	/********* 颜色 ********/
	private String linkURL =null;
	//多级主题元素
	

	public boolean isLive = true;

	public ThemeLabel(int x, int y) {
		
		super("",JLabel.CENTER);
	
		this.child = new Vector<ThemeLabel>();
		this.CurveLineList=new Vector<CurveLine>();
		this.ThemeLeftX = x;
		this.ThemeRightX = x + Constent.ThemeSizeX;
		this.ThemeMidY = y + Constent.ThemeSizeY / 2;

		this.setFont(Constent.themeFont);
		this.setText("主题");
		this.setBounds(x, y, this.ThemeSizeX, this.ThemeSizeY);
		
		this.setStyle();
		
		this.setOpaque(true);
		this.setVisible(true);

		this.myThemeFrame = new ThemeFrame(this);
		/******** 设置鼠标的监听 ************/
		ComponentMouseListener componentMouseListener = new ComponentMouseListener(this);
		this.addMouseListener(componentMouseListener);// 加入鼠标事件监听
		this.addMouseMotionListener(componentMouseListener);
	}
	
	public boolean isInTree() {
		ThemeLabel temp=this;
		while(temp!=null&&temp.getFather()!=null) {
			temp=temp.getFather();
		}
		return temp==MainWindow.pan.getrootThemeLabel();
	}
	public Vector<CurveLine> getCurveLineList(){
		return this.CurveLineList;
	}
	public void addCurveLine(CurveLine curveLine) {
		this.CurveLineList.add(curveLine);
	}
	public void removeCurveLine(CurveLine curveLine) {
		this.CurveLineList.remove(curveLine);
		curveLine.isLive=false;
	}
	public void updateLocation(int x, int y) {
		this.ThemeLeftX = x;
		this.ThemeRightX = x + this.ThemeSizeX;
		this.ThemeMidY = y + this.ThemeSizeY / 2;
		this.setBounds(x, y, this.ThemeSizeX, this.ThemeSizeY);
	}


	public void updateSize() {
		int deltaSizeX = this.getNewLength() - this.ThemeSizeX;
		int deltaSizeY = 0;
		this.ThemeSizeX += deltaSizeX;
		this.ThemeSizeY += deltaSizeY;
		this.ThemeMidY +=deltaSizeY / 2;
		if (this.getThemeRightX() < MainWindow.pan.getRootThemeLeftX()) {
			this.ThemeLeftX = this.getX() - deltaSizeX;
			this.setBounds(this.getX() - deltaSizeX, this.getY(), this.ThemeSizeX, this.ThemeSizeY);
		} else {
			this.ThemeRightX = this.getX() + this.ThemeSizeX;
			this.setBounds(this.getX(), this.getY(), this.ThemeSizeX, this.ThemeSizeY);
		}

	}

	public int getNewLength() {
		String str = this.getText();
		// 计算str中中文字符个数
		int count = 0;
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			String len = Integer.toBinaryString(c[i]);
			if (len.length() > 8)
				count++;
		}
		double chineseSize = 16.5/ 15;
		double englishSize = 15.0 / 25;
		int fontSize = this.getFont().getSize();
		if (fontSize == 10) {
			chineseSize = 18 / 15;
			englishSize = 16.0 / 25;
		}
		if (fontSize == 15) {
			chineseSize = 17.5 / 15;
			englishSize = 16.0 / 25;
		}
		if (count <6) {
			chineseSize = 20.0 / 15;
		}
		if (count <2) {
			chineseSize = 25.0 / 15;
		}
		if (str.length() - count <6) {
			englishSize = 18.0 / 25;
		}
		if (str.length() - count <2) {
			englishSize = 30.0 / 25;
		}
		return (int) ((str.length() - count) * (fontSize * englishSize)) + 38 * (this.getIconNum() == 1 ? 1 : 0)
				+ (int) (count * (fontSize * chineseSize));

	}

	public int getSizeX() {
		return this.ThemeSizeX;
	}

	public boolean isChild(ThemeLabel themeLabel) {
		return this.child.contains(themeLabel);
	}

	public void addChild(ThemeLabel child) {
		this.child.add(child);
	}

	public void removeChild(ThemeLabel child) {
		this.child.remove(child);
	}

	public void setFather(ThemeLabel father) {
		this.father = father;
	}

	public ThemeLabel getFather() {
		return this.father;
	}

	public Vector<ThemeLabel> getallChild() {
		return this.child;
	}

	public ThemeLabel getChild(int index) {
		return this.child.get(index);
	}

	public void setThemeLeftX(int themeLeftX) {
		ThemeLeftX = themeLeftX;
	}

	public void setThemeRightX(int themeRightX) {
		ThemeRightX = themeRightX;
	}

	public void setThemeMidY(int themeMidY) {
		ThemeMidY = themeMidY;
	}

	public int getThemeLeftX() {
		return ThemeLeftX;
	}

	public int getThemeRightX() {
		return ThemeRightX;
	}

	public int getThemeMidY() {
		return ThemeMidY;
	}

	public ThemeFrame getMyThemeFrame() {
		return myThemeFrame;
	}

	public void setMyThemeFrame(ThemeFrame myThemeFrame) {
		this.myThemeFrame = myThemeFrame;
	}

	public int getThemeSizeX() {
		return ThemeSizeX;
	}

	public void setThemeSizeX(int themeSizeX) {
		ThemeSizeX = themeSizeX;
	}

	public int getIconNum() {
		return iconNum;
	}

	public void setIconNum(int iconNum) {
		this.iconNum = iconNum;
	}

	public int getThemeSizeY() {
		return ThemeSizeY;
	}

	public void setThemeSizeY(int themeSizeY) {
		ThemeSizeY = themeSizeY;
	}
	
	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
	public int getRank() {
		if(this == MainWindow.pan.getrootThemeLabel()) {
			return -1;
		}
		else if(this.father == null)
		{
			return 0;
		}else if(this.father.father ==null)
		{
			return 1;
		}else {
			return 2;
		}
	}
	public void setRank(int Rank)
	{
		switch(Rank) {
			case -1:
				this.ThemeSizeX += 50;
				this.ThemeSizeY += 20;
				//this.updateSize();
				break;
			case 0:
				this.setBorder(BorderFactory.createLineBorder(ThemeChooser.LocalStyle.getbdcolor(),4,true));
				break;
			case 1:
				this.setBorder(BorderFactory.createLineBorder(ThemeChooser.LocalStyle.getbdcolor(),4,true));
				break;				
			case 2:
				this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ThemeChooser.LocalStyle.getBtncolor()));
				break;
		}
	}
	public void setStyle() {
		
		this.setBorder(BorderFactory.createLineBorder(ThemeChooser.LocalStyle.getbdcolor(),4,true));
		this.setBackground(ThemeChooser.LocalStyle.getbkgcolor());	
		this.setForeground(ThemeChooser.LocalStyle.gettxtcolor());
	}
}
