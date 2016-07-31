
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 

public class MainJFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName="temp.txt";
	private DrawPanel drawPanel = new DrawPanel();
	
	public MainJFrame() {
		setTitle("�޿�С����");
	    setSize(670,465);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    JPanel myPanel=new JPanel();
	    Container container = this.getContentPane();
	    myPanel.setLayout(null);
	    
	    JButton UpdateButton = new JButton("��ʾ��ϵͼ");
	    UpdateButton.setBounds(30 ,395, 150, 30);
	    
	    JButton FindButton = new JButton("��ѯ���");
	    FindButton.setBounds(30 ,362, 150, 30);
	    
	    final JTextField findField=new JTextField();
	    findField.setBounds(30, 330, 150, 30);
	    
	   final  JLabel infoLable=new JLabel("<html>��ѯ״̬��ʾ</html>");
	    infoLable.setBounds(180,300,380,150);
	    int size=18;
	    infoLable.setFont(new Font("Serif",Font.PLAIN,size));
	    infoLable.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	    //HTML JLabel jl = new JLabel();
	    
	   myPanel.add(infoLable);
	   myPanel.add(FindButton);
	   myPanel.add(findField);
	   myPanel.add(drawPanel);
	   myPanel.add(UpdateButton);
	   container.add(myPanel);
	 
	    
	  //��ʾͼ����
	    UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graph temp = new Graph();
				temp.SetVertices(ReadFile.ReadGraph(fileName));		

				drawPanel.draw(temp.GetVertices());
				drawPanel.updateUI();
			}
		});
	    
	    //���Ұ�ť����
	    FindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=findField.getText();
				try {
					String result=FindInfo(name);
					infoLable.setText(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	    
	    
	    this.setVisible(true);
	    this.setResizable(true);
	    
	    //�����رմ��� �ر�ʱ�����ʱ�ļ�
	 addWindowListener(new WindowAdapter() {
		 public void windowClosing(WindowEvent e){
			 super.windowClosing(e);
			 ClearFile();
     }
	});
	}
	
	
	String FindInfo(String name) throws IOException{
		MyGraph myGraph=new MyGraph();
		myGraph.InitCourse();
        int length=myGraph.myCourse.size();
        String result="";
		 

		 for(int i=0;i<length;i++){
			 if(myGraph.myCourse.get(i).GetCourseName().contains(name)){
				 result=myGraph.myCourse.get(i).ShowInfo();
				 break;
			 }
			 else{
				 result="û�ҵ���ؿγ̣����������Ƿ���ȷ!";
			 }
	
		 }
		//System.out.println(result);
		 return result;
	}
	
	
	//���temp�ļ�����
	void ClearFile(){
		
		 File file =new File("temp.txt");   
	      if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      }
	      //true = append file
	      FileWriter fileWritter = null;
		try {
			fileWritter = new FileWriter(file.getName(),false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	             try {
					bufferWritter.write("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	             try {
					bufferWritter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}
	
	
	public static void main(String[] args) throws IOException {
		MyGraph myGraph=new MyGraph();
		myGraph.InitCourse();
		myGraph.GetResult();
		
		new MainJFrame();
		
	}

}