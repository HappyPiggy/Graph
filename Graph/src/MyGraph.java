import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyGraph {
	
	 ArrayList<Course>myCourse=new ArrayList<Course>();
	 
	 class Course {
		private String courseName="";
		private String family="";
		private String number="";
		private ArrayList<String> preCourse=new ArrayList<String>();
		private ArrayList<String> subCourse=new ArrayList<String>();
		
		public Course(String c,String f,String n){
			this.family=f;
			this.courseName=c;
			this.number=n;
			AddItems(this);
		}
		
		public void SetCourseName(String name){
			courseName=name;
		}
		
		public String GetCourseName(){
			return courseName;
		}
		
		public boolean IsPreExist(String name){
			int length=preCourse.size();
			for(int i=0;i<length;i++){
				if(preCourse.get(i).equals(name))
					return true;
			}
			return false;
		}
		
		public boolean IsSubExist(String name){
			int length=subCourse.size();
			for(int i=0;i<length;i++){
				if(subCourse.get(i).equals(name))
					return true;
			}
			return false;
		}
		
		public void AddPreCourse(String name){
			//��������ǰ��
			if(!IsPreExist(name)){
				preCourse.add(name);
				
				//����ߵ�ǰ������˺���
			//	Course temp=GetCourse(name);
				String currentName=GetCourseName();
				GetCourse(name,currentName,1);
			}
	
			
		}
		
		public void AddSubCourse(String name){
			if(!IsSubExist(name)){
				subCourse.add(name);
				
		//		Course temp=GetCourse(name);
				String currentName=GetCourseName();
				GetCourse(name,currentName,0);
			}
			
		}
		
		//����ǰ�γ� ����ǰ�޺ͺ���
		public String ShowInfo(){
			String preResult="";
			String subResult="";
			int preSize=GetPreCourse().size();
			int subSize=GetSubCourse().size();
			
			if(preSize!=0){
				for(int i=0;i<preSize;i++){
					preResult+=GetPreCourse().get(i)+",";
				}
				preResult=preResult.substring(0,preResult.length()-1);
			}else{
				preResult="��";
			}
			if(subSize!=0){
				for(int i=0;i<subSize;i++){
					subResult+=GetSubCourse().get(i)+",";
				}
				subResult=subResult.substring(0,subResult.length()-1);
			}else{
				subResult="��";
			}
			String result="<html>��ǰ�γ�Ϊ:"+GetCourseName()+"<br>���޿�:"+preResult+"<br>���޿�:"+subResult+"</html>";
			return result;
		}
		
		
		public ArrayList<String>  GetSubCourse() {
			return this.subCourse;
		}
		
		public ArrayList<String>  GetPreCourse() {
			return this.preCourse;
		}
		
		//�õ���̿γ̽�� ����д���ļ���ͼ
		public String CreateResult(){
			int length=subCourse.size();
			String result="";
			if(length>0){
				result= GetCourseName()+",";
				for(int i=0;i<length;i++){
					result+= subCourse.get(i)+",";
				}
			}else{
				 result=GetCourseName();
			}
			return result;
		}
		
	}
	 
	 
	 //��ʼ���γ��б�
	 public void InitCourse() throws IOException{
		 
		 
		 Course myCourse1=new Course("��ʽ�����ߣ���)","��������","B0900171C");
		 Course myCourse2=new Course("��ʽ�����ߣ��£�","��������","B0900181C");
		 Course myCourse3=new Course("���˼�������ԭ�����","��������","B0900023S");
		 Course myCourse4=new Course("�ߵ���ѧ���ϣ�","��Ȼ��ѧ����","B0600121S");
		 Course myCourse5=new Course("ë��˼��","��������","B0900060S");
		 
//		 Course myCourse6=new Course("C����","��������","B0900060S");
//		 Course myCourse7=new Course("C++","��������","B0900060S");
//		 Course myCourse8=new Course("���ݽṹ","��������","B0900060S");
       //  AddItems(myCourse1,myCourse2,myCourse3,myCourse4,myCourse5,myCourse6,myCourse7,myCourse8);
		 

 
		 myCourse1.AddSubCourse("��ʽ�����ߣ��£�");
		 myCourse1.AddSubCourse("�ߵ���ѧ���ϣ�");
		 myCourse2.AddSubCourse("���˼�������ԭ�����");
		 myCourse5.AddPreCourse("���˼�������ԭ�����");
		 
//		 myCourse6.AddSubCourse("���ݽṹ");
//		 myCourse7.AddSubCourse("���ݽṹ");
//		 myCourse8.AddSubCourse("��ʽ�����ߣ��£�");
		 //myCourse6.AddPreCourse("��ʽ�����ߣ��ϣ�");
	//	 myCourse6.AddPreCourse("��ʽ�����ߣ��£�");
//		 myCourse6.AddPreCourse("�ߵ���ѧ���ϣ�");
//		 myCourse6.AddSubCourse("ë��˼��");
//		 myCourse6.AddSubCourse("���˼�������ԭ�����");

	
		 
		
		// System.out.println(myCourse3.ShowInfo());
		 

	 }
	 
	 public void GetResult() throws IOException{
		 
		
		 File file =new File("temp.txt");
		 int length=myCourse.size();
		 for(int i=0;i<length;i++){
		      String data=myCourse.get(i).CreateResult();
		      
		      if(!file.exists()){
		       try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      }

		      //true = append file
		      FileWriter fileWritter = new FileWriter(file.getName(),true);
		             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		             bufferWritter.write(data+"\n");
		             bufferWritter.close();

		 }
   

		 
	 }
	 
	 //��������ҵ���Ӧ���� ��������γ̵������� ���û�ҵ�����һ���µĿγ���
	 public void GetCourse(String name,String currentName,int flag){
		 int length=myCourse.size();
		 
		 //���flagΪ0 ������name��ǰ��ΪcurrentName 
		 //flagΪ1 ���� name�ĺ���ΪcurrentName
		 for(int i=0;i<length;i++){
			 if(myCourse.get(i).GetCourseName().equals(name)&&flag==0)
				myCourse.get(i).AddPreCourse(currentName);
			 if(myCourse.get(i).GetCourseName().equals(name)&&flag==1)
					myCourse.get(i).AddSubCourse(currentName);
		 }
		 

	 }
	 
	 //��ʼ��ʱ������ӵ�ȫ��list
	 void AddItems(Course ...items){
		 int length=items.length;
		 for(int i=0;i<length;i++)
			 myCourse.add(items[i]);
	 }
}
