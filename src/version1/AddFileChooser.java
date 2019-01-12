package version1;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
/*
 * 本类 在主窗口菜单 创建“文件”菜单项是实例化
 * 负责构造文本选择框
 *     实现文本保存和打开的事件监听
 */
public class AddFileChooser implements ActionListener{
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JFileChooser fileChooser;
	public AddFileChooser(JMenuItem openFile,JMenuItem saveFile) {
		this.openFile=openFile;
		this.saveFile=saveFile;
		this.fileChooser =new JFileChooser();
		openFile.addActionListener(this);
		saveFile.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File file =null;
		int result =0;
		if(e.getSource()==this.openFile) {//设置“打开文件”选项的文件选择框内容，实现打开操作
			fileChooser.setApproveButtonText("确定");
			fileChooser.setDialogTitle("打开文件");
			result =fileChooser.showOpenDialog(MainWindow.frame);
			if(result == JFileChooser.APPROVE_OPTION) {//选择了确定键
				file = fileChooser.getSelectedFile();
				if((file!=null)&&file.getName().endsWith(".umind")) {
					loadFile(file);
				}
			}else if(result == JFileChooser.CANCEL_OPTION) {//选择了取消键
				
			}else if(result == JFileChooser.ERROR_OPTION) {//出错了
				
			}
		}else if(e.getSource()==this.saveFile) {//设置“保存文件”选项的文件选择框内容，实现保存操作
			result =fileChooser.showSaveDialog(MainWindow.frame);
			if(result == JFileChooser.APPROVE_OPTION) {//选择了确定键
				file = fileChooser.getSelectedFile();
				System.out.println(file.getName());
				if(file!=null){
					ObjectOutputStream oos=null;
					try {
						if((!file.exists())&&file.getName().endsWith(".umind")){
							file.createNewFile();
						}
						MainWindow.pan.getrootThemeLabel().getMyThemeFrame().getInput().setText(null);
						MainWindow.pan.getrootThemeLabel().getMyThemeFrame().getRemark().setText(null);
						for (Entry<ThemeLabel, ConnectLine> item : MainWindow.pan.getallConnectLine().entrySet()) {
							ThemeLabel key = item.getKey();
							key.getMyThemeFrame().getInput().setText(null);
							key.getMyThemeFrame().getRemark().setText(null);
							key.getMyThemeFrame().getLink().setText(null);
						}
						 oos=new ObjectOutputStream(new FileOutputStream(file));
						 saveStyle style =new saveStyle(ThemeChooser.LocalStyle);
						 oos.writeObject(MainWindow.pan.getrootThemeLabel());
						 oos.writeObject(MainWindow.pan.getallConnectLine());
						 oos.writeObject(MainWindow.pan.getcurveLineList());
						 oos.writeObject(style);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							oos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}else if(result == JFileChooser.CANCEL_OPTION) {//选择了取消键
				
			}else if(result == JFileChooser.ERROR_OPTION) {//出错了
				
			}
		}
		
	}
	
	//加载文件的方法单独列出
	public static void loadFile(File file){
		
		for (Entry<ThemeLabel, ConnectLine> item : MainWindow.pan.getallConnectLine().entrySet()) {
			ThemeLabel key = item.getKey();
			MainWindow.pan.remove(key);
			key.getMyThemeFrame().setVisible(false);
			key.getMyThemeFrame().getInput().setText(null);
			key.setMyThemeFrame(null);
		}
		MainWindow.pan.getrootThemeLabel().getMyThemeFrame().setVisible(false);
		MainWindow.pan.getrootThemeLabel().getMyThemeFrame().getInput().setText(null);
		MainWindow.pan.getrootThemeLabel().setMyThemeFrame(null);
		MainWindow.pan.remove(MainWindow.pan.getrootThemeLabel());
		MainWindow.pan.removeAllConnectLine();
		MainWindow.pan.setrootThemeLabel(null);
		
		
		ObjectInputStream ois=null;
		try {
			ois =new ObjectInputStream(new FileInputStream(file));
			MainWindow.pan.setrootThemeLabel((ThemeLabel) ois.readObject());			
			MainWindow.pan.setallConnectLine((HashMap<ThemeLabel, ConnectLine>)(ois.readObject()));
			MainWindow.pan.getrootThemeLabel().getMyThemeFrame().getButton1().addActionListener(new ThemeFrameActionListener(MainWindow.pan.getrootThemeLabel().getMyThemeFrame()));
			ComponentMouseListener componentMouseListener1=new ComponentMouseListener(MainWindow.pan.getrootThemeLabel());
			MainWindow.pan.getrootThemeLabel().addMouseListener(componentMouseListener1);//加入鼠标事件监听
			MainWindow.pan.getrootThemeLabel().addMouseMotionListener(componentMouseListener1);
			
			for (Entry<ThemeLabel, ConnectLine> item :MainWindow.pan.getallConnectLine().entrySet()) {
				ThemeLabel key = item.getKey();
				key.getMyThemeFrame().getButton1().addActionListener(new ThemeFrameActionListener(key.getMyThemeFrame()));
				ConnectLine val = item.getValue();
				ComponentMouseListener componentMouseListener=new ComponentMouseListener(key);
				key.addMouseListener(componentMouseListener);//加入鼠标事件监听
				key.addMouseMotionListener(componentMouseListener);
			}
			MainWindow.pan.add(MainWindow.pan.getrootThemeLabel());
			//new ThemeDetect(MainWindow.pan).updateConnect(MainWindow.pan.getrootThemeLabel(), 0, 0);
			
			for(int i=0;i<MainWindow.pan.getcurveLineList().size();i++) {
				CurveLine curveLine=MainWindow.pan.getcurveLineList().get(i);
			//	curveLine.isLive=false;
				JLabel node1=curveLine.getNode1();
				JLabel node2=curveLine.getNode2();
				MainWindow.pan.remove(node1);
				MainWindow.pan.remove(node2);
				
			}
			Vector<CurveLine> CurveLineList=(Vector<CurveLine>)ois.readObject();
			MainWindow.pan.setCurveLineList(CurveLineList);
			for(int i=0;i<CurveLineList.size();i++) {
				CurveLine curveLine=CurveLineList.get(i);
				
				JLabel node1=curveLine.getNode1();
				nodeListener curveLineListener1=new nodeListener(curveLine);
				node1.addMouseListener(curveLineListener1);
				node1.addMouseMotionListener(curveLineListener1);
				
				JLabel node2=curveLine.getNode2();
				nodeListener curveLineListener2=new nodeListener(curveLine);
				node2.addMouseListener(curveLineListener2);
				node2.addMouseMotionListener(curveLineListener2);
			}
			
			
			ThemeChooser.LocalStyle = ((saveStyle) ois.readObject()).LocalStyle;
			MainWindow.pan.changestyle();
			
			int deltaSizex=MainWindow.pan.getrootThemeLabel().getNewLength()-MainWindow.pan.getrootThemeLabel().getSizeX();
			MainWindow.pan.getrootThemeLabel().updateSize();
			new ThemeDetect(MainWindow.pan).themesizeChangeMove(MainWindow.pan.getrootThemeLabel(),deltaSizex);
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


  
}
