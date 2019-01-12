/**
 * 
 */
package version1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import version1.Constent.StyleEnum;

/**
 * @author sheng
 * 本类负责监听主题菜单以及修改主题
 *
 */
public class ThemeChooser implements ActionListener {
	private JMenuItem Dracula;
	private JMenuItem Sublime;
	private JMenuItem Classic;
	public static StyleEnum LocalStyle = StyleEnum.Classic; 
	public ThemeChooser (JMenuItem Dracula,JMenuItem Sublime,JMenuItem Classic)
	{
		this.Dracula=Dracula;
		this.Sublime=Sublime;
		this.Classic=Classic;		
		Dracula.addActionListener(this);
		Sublime.addActionListener(this);
		Classic.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.Dracula)
		{
			
			ThemeChooser.LocalStyle = StyleEnum.Dracula;
		}else if(e.getSource() == this.Sublime)
		{
			ThemeChooser.LocalStyle = StyleEnum.Sublime;
		}else if(e.getSource() == this.Classic)
		{
			ThemeChooser.LocalStyle = StyleEnum.Classic;
		}
		
		MainWindow.pan.changestyle();
	}

}
class saveStyle implements java.io.Serializable{
	public  StyleEnum LocalStyle=StyleEnum.Classic;

	public saveStyle(StyleEnum localStyle) {
		super();
		LocalStyle = localStyle;
	}

	
	
}