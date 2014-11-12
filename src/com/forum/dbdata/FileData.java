package com.forum.dbdata;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

//import com.example.model.Msg;

public class FileData {
	private static final int BufferLen = 1024;

	private String dirRoot = "D:\\data\\";
	private String classifyRoot = dirRoot + "classify\\";
	private String googsRoot = dirRoot + "obj\\";
	private String essayRoot = dirRoot + "essay\\";
	private String userRoot = dirRoot + "user\\";

	// private String getRootPath(String objType) {
	// if (objType.equals(Msg.OBJ_TYPE_CLASSIFY))
	// return classifyRoot;
	// if (objType.equals(Msg.OBJ_TYPE_ESSAY))
	// return essayRoot;
	// if (objType.equals(Msg.OBJ_TYPE_GOODS))
	// return googsRoot;
	// return null;
	// }
	static public boolean fileExists(String filePath) {
		File file = new File(filePath);
		// 若文件不存在，则返回-1
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	static public boolean delFileImg(String filePath) {
		File file = new File(filePath);
		// 若文件不存在，则返回-1
		if (!file.exists()) {
			return false;
		}
//		deleteFile(filePath);
		file.delete();
		return true;
	}
	// 检查文件是否真实存在，核对下载密码，若文件不存在或密码错误，则返回-1，否则返回文件长度
	// 此处只要密码不为空就认为是正确的
	public long getFileLength(String filePath) {
		// 若文件名或密码为null，则返回-1
		if ((filePath == null))
			return -1;
		// 若文件名或密码长度为0，则返回-1
		if ((filePath.length() == 0))
			return -1;
		System.out.println("DownloadServer getFileLength----->" + filePath);
		File file = new File(filePath);
		// 若文件不存在，则返回-1
		if (!file.exists()) {
			return 0;
		}
		return file.length(); // 返回文件长度
	} // 用指定输出流发送指定文件

	public void SendObjImg(DataOutputStream out/* , Msg msg */,
			String filePath, int w) throws Exception {
		System.out.println("sendObjImg() ...");

		// String objtype = msg.getType();
		// String objid = msg.getObjId();
		// int strImgW = msg.getImgWidth();

		// String filePath = getRootPath(objtype) + objid + "\\1i.jpg";
		if (!fileExists(filePath)) {
			System.out
					.println("ERROR : SendObjImg(), file not exist, filePath="
							+ filePath);
			// out.writeLong(Msg.MSG_DATA_NOT_EXIST);
			return;
		}
		//
		// out.writeLong(Msg.MSG_SEND_IMG);
		// out.flush();

		// SendFileImg(out, filePath, w, true);

	}

	// public void SendEssayContent(DataOutputStream out, Msg msg) throws
	// Exception {
	// // String[] parameter = param.split(":");
	// // String objId = parameter[0]; // 获取相对文件路径
	// // String objType = parameter[0];
	// String objId = msg.getObjId();
	// String contentIdx = msg.getContentIdx(); // 获取下载密码
	// int imgW = msg.getImgWidth();
	//
	// String path = essayRoot + objId + "\\" + contentIdx;
	//
	// SendObjContent(out, path, imgW);
	// }
	// public void SendGoodsContent(DataOutputStream out, Msg msg) throws
	// Exception {
	// // String[] parameter = param.split(":");
	// // String objId = parameter[0]; // 获取相对文件路径
	// // String objType = parameter[0];
	// String objId = msg.getObjId();
	// String contentIdx = msg.getContentIdx(); // 获取下载密码
	// int imgW = msg.getImgWidth();
	//
	// String path = googsRoot + objId + "\\" + contentIdx;
	//
	// SendObjContent(out, path, imgW);
	// }
	// private void SendObjContent(DataOutputStream out, String path, int imgW)
	// throws Exception {
	// // System.out.println("SendObjContent() ...param="+param);
	//
	// boolean hasfile = false;
	//
	// // String path = googsRoot + objId + "\\" + contentIdx;
	// String file = path + ".jpg";
	// if (fileExists(file)) {
	// System.out.println("SendObjContent(), file=" + file);
	//
	// out.writeLong(Msg.MSG_SEND_IMG);
	// SendFileImg(out, file, imgW, false);
	// hasfile = true;
	// }
	// file = path + ".txt";
	// if (fileExists(file)) {
	// System.out.println("SendObjContent(), file=" + file);
	//
	// out.writeLong(Msg.MSG_SEND_TXT);
	// SendFileTxt(out, file);
	// hasfile = true;
	// }
	// if (hasfile==false) {
	// System.out.println("ERROR : SendObjContent(), file not exist, path=" +
	// path);
	// out.writeLong(Msg.MSG_DATA_NOT_EXIST);
	// }
	// System.out.println("sendObjImg() exit");
	// }
	static public boolean dirCreate(String dirPath) {
		String filepath=dirPath;	
		File myPath=new File(filepath);		
		if(!myPath.exists()) {		
			myPath.mkdir();			
		}
		return true;
	}
	
	static public boolean writeFileImg(String imagePath, String imgString) {
		if (fileExists(imagePath)) {
			System.out.println("ERROR : SendObjImg(), file exist, filePath="
					+ imagePath);
			return false;
		}
		try {
			byte[] b = imgString.getBytes("ISO-8859-1");

			FileOutputStream fos = new FileOutputStream(imagePath);
			fos.write(b);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	static public String SendFileImg(String filePath, int imgW, boolean maxSide) {
		if (!fileExists(filePath)) {
			System.out
					.println("ERROR : SendObjImg(), file not exist, filePath="
							+ filePath);
			// out.writeLong(Msg.MSG_DATA_NOT_EXIST);
			return null;
		}
		try {
			// System.out.println("SendFileImg() ...1");
			float scale = (float) 1.0;
			BufferedImage src = ImageIO.read(new File(filePath)); // 读入源图像
			int width = src.getWidth(); // 源图宽
			scale = ((float) imgW) / width;
			if (maxSide) {
				int height = src.getHeight(); // 源图高
				if (height < width) {
					scale = ((float) imgW) / height;
				}
			}
			// 获取一个宽、长是原来scale的图像实例
			BufferedImage des = ImgOper.createZoomSizeImage(src, scale);
			// System.out.println("SendFileImg() ...3");
			ByteArrayOutputStream outt = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(des, "jpg", outt);
			// System.out.println("SendFileImg() ...4");
			byte[] b = outt.toByteArray();
			// return Base64.encodeToString(b);
			// String s = new String(b, "GB2312");
			String sendString = new String(b, "ISO-8859-1");

			return sendString;

			// return outt.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	// private void SendFileTxt (DataOutputStream out, String filePath) {
	//
	// System.out.println("SendFileTxt() filePath:" + filePath);
	// // int totlelen = 0;
	// try
	// {
	// FileInputStream in = new FileInputStream(filePath);
	// byte[] buf = new byte[1024];
	// int len;
	// int maxlenread = buf.length;
	// // if (maxlen>0) {
	// // maxlenread = maxlen;
	// // }
	// // 反复读取该文件中的内容，直到读到的长度为-1
	// while ((len = in.read(buf, 0, maxlenread)) >= 0) {
	// out.write(buf, 0, len); // 将读到的数据，按读到的长度写入输出流
	// out.flush();
	// //
	// // totlelen += len;
	// // if (maxlen>0) {
	// // if (totlelen>=maxlen) {
	// // break;
	// // }
	// // }
	// }
	// in.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// 用指定输出流发送指定文件
	// public void SendObjImg(DataOutputStream out, String fileName)
	// throws Exception {
	// System.out.println("sendObjImg() ...");
	// String filePath = "D:\\Dream\\pic\\android\\u_201401090926150443635.jpg";
	// System.out.println("sendObjImg(), sendFile----->" + filePath);
	// long filelen = getFileLength(filePath);
	// out.writeLong(filelen);
	// out.flush();
	// System.out.println("sendObjImg(), sendFileLen----->" + filelen);
	//
	// // 创建与该文件关联的文件输入流
	// FileInputStream in = new FileInputStream(filePath);
	// byte[] buf = new byte[BufferLen];
	// int len;
	// // 反复读取该文件中的内容，直到读到的长度为-1
	// while ((len = in.read(buf)) >= 0) {
	// out.write(buf, 0, len); // 将读到的数据，按读到的长度写入输出流
	// out.flush();
	// // System.out.println("sendFileData----->" + len);
	// }
	// in.close();
	// System.out.println("sendObjImg() exit");
	// }

}
