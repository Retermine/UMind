package version1;
import java.awt.*;
import javax.swing.*;

public class CurveLine implements java.io.Serializable {

	private Point[] points=null;
	private Color color=null;
	private JLabel node1=null,node2=null;
	public boolean  isLive=true;
    private ThemeLabel startLabel=null;
    private ThemeLabel endLabel=null;
    

	public CurveLine(Point start, Point end,ThemeLabel startLabel,ThemeLabel endLabel) {
		this.startLabel=startLabel;
		this.endLabel=endLabel;
		
		color=ThemeChooser.LocalStyle.getCurvelcolor();
		
		 points=new Point[4];
		 points[0]= new Point(start.x, start.y);
		 points[1]=new Point(start.x+(end.x-start.x)/3, start.y-50);
		 points[2]=new Point(start.x+2*(end.x-start.x)/3, end.y-50);
		 points[3]=new Point(end.x, end.y);
		 		
		node1=new JLabel();
		node2=new JLabel();
			
		node1.setOpaque(true);
		node1.setBounds(points[1].x,points[1].y, 12, 12);
		node1.setBorder(BorderFactory.createLineBorder(this.color));
		node1.setBackground(ThemeChooser.LocalStyle.gettxtcolor());
		node1.setVisible(true);
		nodeListener curveLineListener1=new nodeListener(this);
		node1.addMouseListener(curveLineListener1);
		node1.addMouseMotionListener(curveLineListener1);
		
		node2.setOpaque(true);
		node2.setBounds(points[2].x,points[2].y, 12,12);
		node2.setBorder(BorderFactory.createLineBorder(this.color));
		node2.setBackground(ThemeChooser.LocalStyle.gettxtcolor());
		node2.setVisible(true);
		nodeListener curveLineListener2=new nodeListener(this);
		node2.addMouseListener(curveLineListener2);
		node2.addMouseMotionListener(curveLineListener2);
		
				
	}
	
	
	
	public void updateFirstTwoNode(int deltax, int deltay) {
		
		 points[0]= new Point(points[0].x+deltax, points[0].y+deltay);
		 points[1]=new Point(points[1].x+deltax, points[1].y+deltay);
		
		 node1.setBounds(points[1].x,points[1].y,12,12);
	}
	
	public void mirrorFirstTwoNode(int x0) {
		
		 points[0]= new Point(2*x0-points[0].x, points[0].y);
		 points[1]=new Point(2*x0-points[1].x, points[1].y);
		
		 node1.setBounds(points[1].x,points[1].y,12,12);
	}
	
	public void mirrorLastTwoNode(int x0) {
		 points[2]=new Point(2*x0-points[2].x, points[2].y);
		 points[3]=new Point(2*x0-points[3].x, points[3].y);
		 node2.setBounds(points[2].x,points[2].y, 12,12);
	}
	public void updateLastTwoNode(int deltax, int deltay) {
		 points[2]=new Point(points[2].x+deltax, points[2].y+deltay);
		 points[3]=new Point(points[3].x+deltax, points[3].y+deltay);
		 node2.setBounds(points[2].x,points[2].y, 12,12);

	}
	
	public void updateLastNode(Point point3,Point point4) {
		
	}
	public void updateCurveLine(int deltax, int deltay) {
	
		 points[0]= new Point(points[0].x+deltax, points[0].y+deltay);
		 points[1]=new Point(points[1].x+deltax, points[1].y+deltay);
		 points[2]=new Point(points[2].x+deltax, points[2].y+deltay);
		 points[3]=new Point(points[3].x+deltax, points[3].y+deltay);	
		 node1.setBounds(points[1].x,points[1].y, 12,12);
		 node2.setBounds(points[2].x,points[2].y, 12,12);
	}
	
	public void updateNode1(Point p1) {
		 points[1]=p1;
	 	 node1.setBounds(p1.x,p1.y, 12,12);
		
	}
	
	public void updateNode2(Point p2) {
		 points[2]=p2;
		 node2.setBounds(p2.x,p2.y, 12,12);
		
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public Point getStartPoint() {
		return points[0];
	}
	public Point getEndPoint() {
		return points[3];
	}
	
	public JLabel getNode1() {
		return node1;
	}
	public JLabel getNode2() {
		return node2;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		node1.setBackground(ThemeChooser.LocalStyle.gettxtcolor());
		node2.setBackground(ThemeChooser.LocalStyle.gettxtcolor());
	}
	
	public ThemeLabel getstartLabel() {
		return startLabel;
	}

	public void setstartLabel(ThemeLabel startLabel) {
		this.startLabel = startLabel;
	}

	public ThemeLabel getendLabel() {
		return endLabel;
	}

	public void setendLabel(ThemeLabel endLabel) {
		this.endLabel = endLabel;
	}

}


