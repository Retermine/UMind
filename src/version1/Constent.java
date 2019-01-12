package version1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;


public class Constent {
	
	/*******paintepanel参数******/
	public static int paintPanelWidth=3000;
	public static int paintPanelHight=3000;
	
	/*****frame参数*******/
	public static int frameHeight=Toolkit.getDefaultToolkit().getScreenSize().height*2/3;
	public static int frameWidth=Toolkit.getDefaultToolkit().getScreenSize().width*2/3;
	
	public static int frameLocationX=Toolkit.getDefaultToolkit().getScreenSize().width/2;
	public static int frameLOcationY=Toolkit.getDefaultToolkit().getScreenSize().height/2;
	
	/*******ThemeLabel参数*******/
	public static int ThemeSizeX=70;
	public static int ThemeSizeY=50;
	public static float LabelThickness=3.5f;
	public static Font themeFont=new Font("黑体",Font.BOLD,25);
	/*******ThemePan参数*******/
	public static int themeFrameHeight=400;
	public static int themeFrameWidth=600;
	

	/***********图片路径*********/
	public static String imagesPath;
	/*********连接参数*********/
	public static int minDistancex=180;
	public static int minDistancey=200; 
	public static int addRight=1;
	public static int addLeft=-1;
	/********颜色与主题*******/
	public final static Color Maskbkg     = new Color(0,0,255,20);
	public final static Color Maskbd     = new Color(0,0,0,60);	
	
	public final static Color LightGreen     = new Color(80, 250, 123);	
	public final static Color Pink     = new Color(255, 121, 198);	
	public final static Color DarkPurpule     = new Color(40, 42, 54);	
	public final static Color Purpule     = new Color(189,147,249);
	public final static Color LightRed     = new Color(255,85,85);
	
	public final static Color Orange     = new Color(204, 108, 29);	
	public final static Color Lime     = new Color(151, 236, 34);	
	public final static Color LightGery     = new Color(128,128,128);	
	public final static Color DarkGrey     = new Color(47, 47, 47);	

	public enum StyleEnum {
		Dracula(Purpule,DarkPurpule,Pink,
				LightRed,DarkPurpule,LightGreen,Color.WHITE),
		Sublime(Orange,DarkGrey,Lime,
				LightGery,DarkGrey,new Color(204, 129, 167),Color.WHITE),
		Classic(new Color(85, 142, 213),Color.WHITE,new Color(55, 96, 146),
				new Color(133, 174, 255),Color.WHITE,new Color(0, 0, 255,40),Color.BLACK);

	    private final Color bdcolor;
	    private final Color bkgcolor;
	    private final Color txtcolor;
	    private final Color linecolor;
	    private final Color panelcolor;
	    private final Color curvelcolor;
	    private final Color btncolor;
  

	    //private final String value;

	    private StyleEnum(Color bdcolor,Color bkgcolor,Color txtcolor,
	    		Color linecolor,Color panelcolor,Color curvelcolor,Color btncolor){
	        this.bdcolor = bdcolor;
	        this.bkgcolor = bkgcolor;
	        this.txtcolor = txtcolor;
	        this.linecolor = linecolor;
	        this.panelcolor = panelcolor;
	        this.curvelcolor = curvelcolor;
	        this.btncolor = btncolor;
	    }

	    public Color getbdcolor() {
	        return this.bdcolor;
	    }
	    public Color getbkgcolor() {
	        return this.bkgcolor;
	    }
	    public Color gettxtcolor() {
	        return this.txtcolor;
	    }
	    public Color getlinecolor() {
	        return this.linecolor;
	    }	    
	    public Color getpanelcolor() {
	        return this.panelcolor;
	    }

		public Color getCurvelcolor() {
			return curvelcolor;
		}

		public Color getBtncolor() {
			return btncolor;
		}	
	    
	    
	}
}
