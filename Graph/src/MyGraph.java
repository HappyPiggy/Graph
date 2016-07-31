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
			//添加者添加前修
			if(!IsPreExist(name)){
				preCourse.add(name);
				
				//添加者的前修添加了后修
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
		
		//给当前课程 返回前修和后修
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
				preResult="无";
			}
			if(subSize!=0){
				for(int i=0;i<subSize;i++){
					subResult+=GetSubCourse().get(i)+",";
				}
				subResult=subResult.substring(0,subResult.length()-1);
			}else{
				subResult="无";
			}
			String result="<html>当前课程为:"+GetCourseName()+"<br>先修课:"+preResult+"<br>后修课:"+subResult+"</html>";
			return result;
		}
		
		
		public ArrayList<String>  GetSubCourse() {
			return this.subCourse;
		}
		
		public ArrayList<String>  GetPreCourse() {
			return this.preCourse;
		}
		
		//得到后继课程结果 便于写入文件画图
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
	 
	 
	 //初始化课程列表
	 public void InitCourse() throws IOException{
		 
		 
		 Course myCourse1=new Course("形式与政策（上)","公共基础","B0900171C");
		 Course myCourse2=new Course("形式与政策（下）","公共基础","B0900181C");
		 Course myCourse3=new Course("马克思主义基本原理概论","公共基础","B0900023S");
		 Course myCourse4=new Course("高等数学（上）","自然科学基础","B0600121S");
		 Course myCourse5=new Course("毛泽东思想","公共基础","B0900060S");
		 
//		 Course myCourse6=new Course("C语言","公共基础","B0900060S");
//		 Course myCourse7=new Course("C++","公共基础","B0900060S");
//		 Course myCourse8=new Course("数据结构","公共基础","B0900060S");
       //  AddItems(myCourse1,myCourse2,myCourse3,myCourse4,myCourse5,myCourse6,myCourse7,myCourse8);
		 

 
		 myCourse1.AddSubCourse("形式与政策（下）");
		 myCourse1.AddSubCourse("高等数学（上）");
		 myCourse2.AddSubCourse("马克思主义基本原理概论");
		 myCourse5.AddPreCourse("马克思主义基本原理概论");
		 
//		 myCourse6.AddSubCourse("数据结构");
//		 myCourse7.AddSubCourse("数据结构");
//		 myCourse8.AddSubCourse("形式与政策（下）");
		 //myCourse6.AddPreCourse("形式与政策（上）");
	//	 myCourse6.AddPreCourse("形式与政策（下）");
//		 myCourse6.AddPreCourse("高等数学（上）");
//		 myCourse6.AddSubCourse("毛泽东思想");
//		 myCourse6.AddSubCourse("马克思主义基本原理概论");

	
		 
		
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
	 
	 //如果可以找到对应名字 返回这个课程的所在类 如果没找到创建一个新的课程类
	 public void GetCourse(String name,String currentName,int flag){
		 int length=myCourse.size();
		 
		 //如果flag为0 则设置name的前修为currentName 
		 //flag为1 设置 name的后修为currentName
		 for(int i=0;i<length;i++){
			 if(myCourse.get(i).GetCourseName().equals(name)&&flag==0)
				myCourse.get(i).AddPreCourse(currentName);
			 if(myCourse.get(i).GetCourseName().equals(name)&&flag==1)
					myCourse.get(i).AddSubCourse(currentName);
		 }
		 

	 }
	 
	 //初始化时批量添加到全局list
	 void AddItems(Course ...items){
		 int length=items.length;
		 for(int i=0;i<length;i++)
			 myCourse.add(items[i]);
	 }
}
