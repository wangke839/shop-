package com.wangke_utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class File_Utils {

	//读取上传文件并将文件内容转存为集合
	public static List readFile(String url) throws Exception{
		File file = new File(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
		String readLine = in.readLine();
		List<String> list = new ArrayList<String>();
		while(readLine != null){
			list.add(readLine);
			readLine = in.readLine();
		}
		return list;
	}
	//	获取文件扩展名
	public static String getOther(String url){
		File file = new File(url);
		String fileName = file.getName();
		//求出扩展名
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		//生成新的文件名
		String newFileName = UUID.randomUUID().toString()+ suffix;
		return suffix;
	}
//		删除文件
//	如果是目录，则下面的文件和所有子目录中的文件都要删除
//	使用递归。涉及内容。判断目录的存在性，判断是否为目录或文件，删除。
	public static boolean delFile(File file){
		boolean delete = false;
		if (file.isFile() || file.list().length == 0) {
	        file.delete();
	        //System.out.println(file.getPath());
	    } else {
	        for (File f : file.listFiles()) {
	        	delFile(f); // 递归删除每一个文件
	 
	        }
	        delete = file.delete(); // 删除文件夹
	        //System.out.println(file.getPath());
	    }
		return delete;
	}
//		返回文件以指定单位大小表示
	public static void fileSize(File file){
		if (file.exists() && file.isFile()) {
            String fileName = file.getName();
            System.out.println("文件"+fileName+"的大小是："+file.length());
        }
		
	}
	/**
     * 按行读取文件
     */
    public static void ReadFileByLine(String filename) {
        File file = new File(filename);
        InputStream is = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            is = new FileInputStream(file);
            reader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader)
                    bufferedReader.close();
                if (null != reader)
                    reader.close();
                if (null != is)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
	 * 按字节读取文件
	 * 
	 * @param filename
	 */
	public static void ReadFileByBytes(String filename) {
		File file = new File(filename);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			int index = 0;
			while (-1 != (index = is.read())) {
				System.out.write(index);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("-----------------------------------");
		try {
			is = new FileInputStream(file);
			byte[] tempbyte = new byte[1000];
			int index = 0;
			while (-1 != (index = is.read(tempbyte))) {
				System.out.write(tempbyte, 0, index);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 按字符读取文件
	 * 
	 * @param filename
	 */
	public static void ReadFileByChar(String filename) {
		File file = new File(filename);
		InputStream is = null;
		Reader isr = null;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is);
			int index = 0;
			while (-1 != (index = isr.read())) {
				System.out.print((char) index);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != isr)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 通过BufferedWriter写文件
	 * 
	 * @param filename
	 */
	public static void Write2FileByBuffered(String filename) {
		File file = new File(filename);
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			bw.write("Write2FileByBuffered");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != osw) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 通过FileWriter写文件
	 * 
	 * @param filename
	 */
	public static void Write2FileByFileWriter(String filename) {
		File file = new File(filename);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			fw.write("Write2FileByFileWriter");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//	网络文件下载
	public static void download(String realPath,HttpServletRequest request,HttpServletResponse response,String filename) throws FileNotFoundException {
			 /* // 下载本地文件
		    String fileName = "Operator.doc".toString(); // 文件的默认保存名
	*/	    // 读到流中
		    InputStream inStream = new FileInputStream(realPath+filename);// 文件的存放路径
		    // 设置输出的格式
		    response.reset();
		    response.setContentType("bin");
		    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		   
		    // 循环取出流中的数据
		    byte[] b = new byte[1024];
		    int len;
		    try {
		      while ((len = inStream.read(b)) > 0)
		        response.getOutputStream().write(b, 0, len);
		      inStream.close();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		}


	/**
	 * 
	 * @param file
	 * @return  保存文件的相对路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
    public static String processFile(MultipartFile file) throws IllegalStateException, IOException {
    	
    	//1 求扩展名  "xxx.jpg"
    	String suffixName =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
    	String fileNamePre = UUID.randomUUID().toString();
    	// 计算出新的文件名称
    	String fileName = fileNamePre + suffixName;
    	
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	String path = dateFormat.format(new Date());
    	File pathFile  = new File("d:/pic/" + path);
    	if(!pathFile.exists()) {
    		pathFile.mkdirs();
    	}
    	
    	// 最终的新的文件名称
    	String newFileName = "d:/pic/" + path + "/" + fileName;
    	file.transferTo(new File(newFileName));
    	
    	return path + "/" + fileName ;
    }
    /**
	 * @Title: writeFile
	 * @Description: 按照指定的编码把内容写入指定的文件中
	 * @param path
	 * @param content
	 * @param charset
	 * @throws IOException
	 * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 判断父目录是否存在
		if (!file.getParentFile().exists()) {
			// 创建父目录
			file.getParentFile().mkdirs();
		}
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		bw.write(content);
		bw.flush();
		bw.close();
	}

	/**
	 * @Title: readFile
	 * @Description: 读取文件内容
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	 * @return: String
	 */
	public static String readFile(File file, String charset) throws IOException {
		// 创建输出流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		// 定义缓冲对象
		StringBuffer sb = new StringBuffer();
		// 定义读取每行的结果
		String content = null;
		// 循环读取
		while ((content = br.readLine()) != null) {
			// 加入缓冲对象
			sb.append(content);
		}
		// 关闭流
		br.close();
		// 返回结果
		return sb.toString();

	}
}



