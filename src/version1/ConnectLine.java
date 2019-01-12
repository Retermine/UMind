/*连接线类
 * 11-4
 * wangxianke
 */
package version1;

import java.awt.*;

class ConnectLine implements java.io.Serializable{
    
	int startX,startY,endX,endY;
	int width;
	Color color;
	boolean isLive=true;
	
	public ConnectLine(int startX,int StartY,int endX,int endY)
	{
		this.startX=startX;
		this.startY=StartY;
		this.endX=endX;
		this.endY=endY;
		this.width=1;
		this.color=ThemeChooser.LocalStyle.getlinecolor();
		
	}
	
	public void setLocation(int startX,int startY,int endX,int endY ) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		
	}
	public void setLocation(int endX,int endY ) {
		this.endX=endX;
		this.endY=endY;
		
	}
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	

}
