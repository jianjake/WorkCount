package wordcount;
//扩展功能
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
public class extendedFun {
	//递归处理该目录下的同类型文件
	static void allfile(String[] list){
		int lenth=list.length;
		String message=null;
		String myfile=null;
		myfile=list[lenth-1];
		if(lenth==9)
			//-a   -w(-l,-c) -w(-l.-c)  -w(-l.-c) in.c -e stop.txt -o out.txt 	
			myfile=list[4];
		//  -l -w -c file.c -e stop.txt -o out.txt
		//-a -w -c file.c -e stop.txt -o out.txt
		if(lenth==8)
			  myfile=list[3];
	      //  -l -w file.c -e stop.txt -o out.txt
			//-a -w  file.c -e stop.txt -o out.txt
			if(lenth==7)
				  myfile=list[2];
		if(lenth==6){
			// -w in.c -e stop.txt -o out.txt
			if(list[0].equals("-w")&&list[1].endsWith(".c")&&list[2].equals("-e")
					&&list[3].endsWith(".txt")&&list[4].equals("-o")&&list[5].endsWith(".txt"))
			       myfile=list[1];
			//-w -l -c in.c-o out.txt
			//-a -l -c in.c-o out.txt
			else if((list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w")||list[0].equals("-a"))
					&&(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))
					&&(list[2].equals("-l")||list[2].equals("-c")||list[2].equals("-w"))&&
					list[3].endsWith(".c")&&list[4].equals("-o")&&list[5].endsWith(".txt"))
					myfile=list[3];
		}
			
		//-l(-c -w) in.c -o out.txt
		if(lenth==4&&(list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))&&
				list[1].endsWith(".c")&&list[2].equals("-o")&&list[3].endsWith(".txt"))
				myfile=list[1];
		//-w in.c -e stop.txt 
		if(lenth==4&&list[0].equals("-w")&&
				list[1].endsWith(".c")&&list[2].equals("-e")&&list[3].endsWith(".txt"))
			myfile=list[1];
		File directory = new File("");//参数为空
		String courseFile;
		try {
			courseFile = directory.getCanonicalPath();//获取当前目录路径
			 // 获得指定文件对象  
	        File file = new File(courseFile);   
	        // 获得该文件夹内的所有文件   
	        File[] array = file.listFiles();   
	        File afile=new File(myfile); 
	        String fileName=afile.getName();    
	        String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length()); //获取文件类型
	        for(int i=0;i<array.length;i++)
	        {   
	            if(array[i].isFile())//如果是文件
	            {   
	                if(array[i].getName().endsWith(fileTyle)){
	                	if(lenth==2){
	                	if(list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))
	                		//-l *.c
	                			basecount.putAtoB(basecount.print(list[0],array[i].getName()), null);//一操作，默认输出
	                	else if(list[0].equals("-a")){
	                		//-a *.c
	                		basecount.putAtoB(moredata(array[i].getName()),null);//详细内容
	                	}
	                	}
	                	else if(lenth==3&&(list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))&&
	                			(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))){//两操作，默认输出
	                		//-l -w *.c
	                		basecount.putAtoB(basecount.print(list[0],array[i].getName()), null);
	                		basecount.putAtoB(basecount.print(list[1], array[i].getName()), null);
	                	}
	                	else if(lenth==3&&list[0].equals("-a")&&(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))){//两操作，默认输出
	                		//-a -w *.c
	                		basecount.putAtoB(basecount.print(list[1],array[i].getName()), null);
	                		basecount.putAtoB(moredata(array[i].getName()), null);
	                	}
	                	else if(lenth==4){//三操作，默认输出
	                		////-l(-c -w) in.c -o out.txt
	                		if((list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))&&
				                  list[1].endsWith(".c")&&list[2].equals("-o")&&list[3].endsWith(".txt"))
	                			basecount.putAtoB(basecount.print(list[0], array[i].getName()),list[3]);
	                		//-l -w -c *.c 
	                		else if((list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))&&
	                					(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))&&
	                					(list[2].equals("-l")||list[2].equals("-c")||list[2].equals("-w"))
	                					&&list[3].endsWith(".c")){
	                		basecount.putAtoB(basecount.print(list[0],array[i].getName()), null);
	                		basecount.putAtoB(basecount.print(list[1], array[i].getName()), null);
	                		basecount.putAtoB(basecount.print(list[2],array[i].getName()), null);
	                		}
	                		//-a -w -c *.c 
	                		else if(list[0].equals("-a")&&(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))&&
	                					(list[2].equals("-l")||list[2].equals("-c")||list[2].equals("-w"))
	                					&&list[3].endsWith(".c")){
	                		basecount.putAtoB(basecount.print(list[1], array[i].getName()), null);
	                		basecount.putAtoB(basecount.print(list[2],array[i].getName()), null);
	                		basecount.putAtoB(moredata(array[i].getName()), null);
	                		}
	                		//-w in.c -e stop.txt 
	                		else 	if(lenth==4&&list[0].equals("-w")&&
	            				list[1].endsWith(".c")&&list[2].equals("-e")&&list[3].endsWith(".txt"))
	                			basecount.putAtoB(stopcount(array[i].getName(),list[3]), null);
	                	}
	                	else if(lenth==6)
	                	{
	                		//-w *.c -e stop.txt -o out.txt
	                		//System.out.println(array[i].getName()+list[3]);
	            			// -w in.c -e stop.txt -o out.txt
	            			if(list[0].equals("-w")&&list[1].endsWith(".c")&&list[2].equals("-e")
	            					&&list[3].endsWith(".txt")&&list[4].equals("-o")&&list[5].endsWith(".txt"))
	                		   basecount.putAtoB(extendedFun.stopcount(array[i].getName(),list[3]), list[5]);
	            			//-w -l -c in.c-o out.txt
	            			else if((list[0].equals("-l")||list[0].equals("-c")||list[0].equals("-w"))
	            					&&(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))
	            					&&(list[2].equals("-l")||list[2].equals("-c")||list[2].equals("-w"))&&
	            					list[3].endsWith(".c")&&list[4].equals("-o")&&list[5].endsWith(".txt"))
	            			{
	            				//System.out.println(list[0]+" "+list[1]+" "+list[2]+"  "+list[3]+"  "+list[4]+"  "+list[5]);
	            				basecount.putAtoB(basecount.print(list[0],array[i].getName()),list[5]);
	            				basecount.putAtoB(basecount.print(list[1],array[i].getName()),list[5]);
	            				basecount.putAtoB(basecount.print(list[1],array[i].getName()),list[5]);
	            			}
	            			//-a-l -c in.c-o out.txt
	            			else if(list[0].equals("-a")
	            					&&(list[1].equals("-l")||list[1].equals("-c")||list[1].equals("-w"))
	            					&&(list[2].equals("-l")||list[2].equals("-c")||list[2].equals("-w"))&&
	            					list[3].endsWith(".c")&&list[4].equals("-o")&&list[5].endsWith(".txt"))
	            			{
	            				//System.out.println(list[0]+" "+list[1]+" "+list[2]+"  "+list[3]+"  "+list[4]+"  "+list[5]);
	            				//basecount.putAtoB(basecount.print(list[0],array[i].getName()),list[5]);
	            				basecount.putAtoB(basecount.print(list[1],array[i].getName()),list[5]);
	            				basecount.putAtoB(basecount.print(list[1],array[i].getName()),list[5]);
	            				basecount.putAtoB(extendedFun.moredata(array[i].getName()),list[5]);
	            			}
	                	}
	                	else if(lenth==7)
	                	{
	                		if(list[0].equals("-a")){
	                			//-a -l   *.c -e stop.txt -o out.txt
                			basecount.putAtoB(basecount.print(list[1],array[i].getName()), list[6]);
	                		basecount.putAtoB(moredata(array[i].getName()),list[6]);
	                		basecount.putAtoB(stopcount(array[i].getName(),list[4]),list[6]);
	                		}   
	                		else {
	                			//-c -l   *.c -e stop.txt -o out.txt
	                			basecount.putAtoB(basecount.print(list[0],array[i].getName()), list[6]);
	                			basecount.putAtoB(basecount.print(list[1],array[i].getName()), list[6]);
		                		basecount.putAtoB(stopcount(array[i].getName(),list[4]),list[6]);
	                		}
	                			
	                	}
	                	else if(lenth==8)
	                	{
	                		if(list[0].equals("-a")){
	                			//-a -l -w   *.c -e stop.txt -o out.txt
                			basecount.putAtoB(basecount.print(list[1],array[i].getName()), list[7]);
	                		basecount.putAtoB(basecount.print(list[2], array[i].getName()),list[7]);
	                		basecount.putAtoB(moredata(array[i].getName()),list[7]);
	                		basecount.putAtoB(stopcount(array[i].getName(),list[5]),list[7]);
	                		}   
	                		else {
	                			//-c -l -w   *.c -e stop.txt -o out.txt
	                			basecount.putAtoB(basecount.print(list[0],array[i].getName()), list[7]);
	                			basecount.putAtoB(basecount.print(list[1],array[i].getName()), list[7]);
		                		basecount.putAtoB(basecount.print(list[2], array[i].getName()),list[7]);
		                		basecount.putAtoB(stopcount(array[i].getName(),list[5]),list[7]);
	                		}
	                			
	                	}
	                	else if(lenth==9)
	                	{
	                		if(list[0].equals("-a")){
	                			//-a -l -w   -c *.c -e stop.txt -o out.txt
                			basecount.putAtoB(basecount.print(list[1],array[i].getName()), list[8]);
	                		basecount.putAtoB(basecount.print(list[2], array[i].getName()),list[8]);
	                		basecount.putAtoB(basecount.print(list[3], array[i].getName()),list[8]);
	                		basecount.putAtoB(moredata(array[i].getName()),list[8]);
	                		basecount.putAtoB(stopcount(array[i].getName(),list[6]),list[8]);
	                		}          		
	                	}
	                	else System.out.println("输入格式错误");
	                }
	            }
	        }   
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
  //统计代码行/空行/注释行
	static String moredata(String myfile)throws FileNotFoundException {  
		File file=new File(myfile);
  	 // 记录注释行数  
       long annotationLine = 0;  
      // 记录空白行数  
      long blankLine = 0;  
      // 记录有效代码的行数  
      long codeLine = 0;   
      //假注释
      long notLine=0;
      if (file == null || !file.exists())   
          throw new FileNotFoundException(file + "，文件不存在！"); 
      BufferedReader br = null;  
      // 判断此行是否为注释行  
      boolean comment = false;  
      int whiteLines = 0;  
      int commentLines = 0;  
      int normalLines = 0;       
      try {  
          br = new BufferedReader(new FileReader(file));  
          String line = "";  
          while ((line = br.readLine()) != null) {  
              line = line.trim();  
              if (line.matches("^[//s&&[^//n]]*$")||line.equals("{")||line.equals("}")) {  
                  // 空行 :本行全部是空格或格式控制字符，如果包括代码，则只有不超过一个可显示的字符，例如“{”
                  whiteLines++;      
              } 
             /* 本行不是代码行，并且本行包括注释。一个有趣的例子是有些程序员会在单字符后面加注释：
              *  }//注释
              */
              else if (line.startsWith("/*") && !line.endsWith("*/")||((line.startsWith("{/*")||line.startsWith("}/*"))&&!line.endsWith("*/"))){
                  // 判断此行为"/*"开头的注释行  
                  commentLines++;  
                  comment = true;  
              } else if (comment == true && !line.endsWith("*/")&&!line.startsWith("*/")) {  
                  // 为多行注释中的一行（不是开头和结尾）
              	notLine++;
                  commentLines++;  
              } else if (comment == true && (line.endsWith("*/")||line.startsWith("*/"))) {  
                  // 为多行注释的结束行  
                  commentLines++;  
                  comment = false;  
              } else if (line.startsWith("//")|| line.startsWith("}//")||line.startsWith("{//")||
              		    ((line.startsWith("{/*") ||line.startsWith("}/*")||line.startsWith("/*")) && line.endsWith("*/"))) {  
                  // 单行注释行  
                  commentLines++;  
              } else {  
                  // 正常代码行  
              	//System.out.println(line);
                  normalLines++;  
              }  
          }    	
      } catch (FileNotFoundException e) {  
          e.printStackTrace();  
      } catch (IOException e) {  
          e.printStackTrace();  
      } finally {  
          if (br != null) {  
              try {  
                  br.close();  
                  br = null;  
              } catch (IOException e) {  
                  e.printStackTrace();  
              }  
          }  
             
      }    
      String message=(myfile+",代码行/空行/注释行:"+(normalLines+notLine)+
    		  "/"+whiteLines+"/"+(commentLines-notLine)+"\r\n");//换行"\r\n"不是"\n"
      return message;
  } 
  //调用停用词表，重写统计单词数
	static String  stopcount(String thefile,String txt){
  	int stopcount=0;
  	int wordcount=0;
  	File stopfile=new File(txt);
		File file=new File(thefile);
		ArrayList<String> stop=new ArrayList<String>(3);		
//     读入stopfile.txt的单词到一个动态string数组中保存	
		if(stopfile.exists()){
			try{
				FileInputStream fis=new FileInputStream(stopfile);
				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
				BufferedReader br=new BufferedReader(isr);
				String line=new String("");
				StringBuffer sb=new StringBuffer();
				 TreeMap<String, Integer> map = new TreeMap<>(); 
				String[] split =null;
				while((line=br.readLine())!=null){
					 sb.append(line);
			        split = line.split("\\s+");  
			       for (int i = 0; i < split.length; i++) {  
//			          获取到每一个单词  
			            Integer integer = map.get(split[i]);  
//			          如果这个单词在map中没有，赋值1  
			            if(null==integer){  
			                map.put(split[i], 1);  
			            }
			        }  
		        } 		
				Set<String> keySet = map.keySet();  
		        for (String string : keySet) {  
		        	stop.add(string);    
		        }
				br.close();
				isr.close();
				fis.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}			
		}
		//统计stop表的总数目
		if(file.exists()){
			try{
				FileInputStream fis=new FileInputStream(file);
				InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
				BufferedReader br=new BufferedReader(isr);
				String line=new String("");
				StringBuffer sb=new StringBuffer();
				 TreeMap<String, Integer> map = new TreeMap<>(); 
				while((line=br.readLine())!=null){
			        String[] split = line.split("\\s++|\\.|,|\\;|\\(|\\)|\\[|\\]|\\<|\\>|\\=|\\-|\\+|\\*|\\/|\\{|\\}");  //去除多个空格\\s+
			        for (int i = 0; i < split.length; i++) { 		        	
//			          获取到每一个单词  
			            Integer integer = map.get(split[i]);  
//			          如果这个单词在map中没有，赋值1  
			            if(null==integer){  
			                map.put(split[i], 1);  
			            }else{  
//			              如果有，在原来的个数上加上一  
			                map.put(split[i], ++integer);  
			            }  
			        }  
				}
//		      遍历，根据key获取所对应的value  
		        Set<String> keySet = map.keySet();  
		        for (String string : keySet) { 
		        	int i=0;
		        	if(!(string.equals(""))){
		        	wordcount+=map.get(string);
               while(i<stop.size()){
		        	   if(string.equalsIgnoreCase(stop.get(i++)))//不区分大小写判断
		           { 
		         		   stopcount+=map.get(string);
		                    //System.out.println(string+":"+map.get(string));  
		        	   }}
		        	   
		      }
		        } 
		        //System.out.println(wordcount+"  "+stopcount+"  "+(wordcount-stopcount));
				sb.append(line);
				br.close();
				isr.close();
				fis.close();
				
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}        
		String  message=(thefile+", 单词数(停用后）："+(wordcount-stopcount)+"\r\n");//换行"\r\n"不是"\n"
		return message;
  }	
} 