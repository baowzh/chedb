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
		// ���ļ������ڣ��򷵻�-1
		if (!file.exists()) {
			return false;
		}
		return true;
	}

	static public boolean delFileImg(String filePath) {
		File file = new File(filePath);
		// ���ļ������ڣ��򷵻�-1
		if (!file.exists()) {
			return false;
		}
//		deleteFile(filePath);
		file.delete();
		return true;
	}
	// ����ļ��Ƿ���ʵ���ڣ��˶��������룬���ļ������ڻ���������򷵻�-1�����򷵻��ļ�����
	// �˴�ֻҪ���벻Ϊ�վ���Ϊ����ȷ��
	public long getFileLength(String filePath) {
		// ���ļ���������Ϊnull���򷵻�-1
		if ((filePath == null))
			return -1;
		// ���ļ��������볤��Ϊ0���򷵻�-1
		if ((filePath.length() == 0))
			return -1;
		System.out.println("DownloadServer getFileLength----->" + filePath);
		File file = new File(filePath);
		// ���ļ������ڣ��򷵻�-1
		if (!file.exists()) {
			return 0;
		}
		return file.length(); // �����ļ�����
	} // ��ָ�����������ָ���ļ�

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
	// // String objId = parameter[0]; // ��ȡ����ļ�·��
	// // String objType = parameter[0];
	// String objId = msg.getObjId();
	// String contentIdx = msg.getContentIdx(); // ��ȡ��������
	// int imgW = msg.getImgWidth();
	//
	// String path = essayRoot + objId + "\\" + contentIdx;
	//
	// SendObjContent(out, path, imgW);
	// }
	// public void SendGoodsContent(DataOutputStream out, Msg msg) throws
	// Exception {
	// // String[] parameter = param.split(":");
	// // String objId = parameter[0]; // ��ȡ����ļ�·��
	// // String objType = parameter[0];
	// String objId = msg.getObjId();
	// String contentIdx = msg.getContentIdx(); // ��ȡ��������
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
			BufferedImage src = ImageIO.read(new File(filePath)); // ����Դͼ��
			int width = src.getWidth(); // Դͼ��
			scale = ((float) imgW) / width;
			if (maxSide) {
				int height = src.getHeight(); // Դͼ��
				if (height < width) {
					scale = ((float) imgW) / height;
				}
			}
			// ��ȡһ��������ԭ��scale��ͼ��ʵ��
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
	// // ������ȡ���ļ��е����ݣ�ֱ�������ĳ���Ϊ-1
	// while ((len = in.read(buf, 0, maxlenread)) >= 0) {
	// out.write(buf, 0, len); // �����������ݣ��������ĳ���д�������
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
	// ��ָ�����������ָ���ļ�
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
	// // ��������ļ��������ļ�������
	// FileInputStream in = new FileInputStream(filePath);
	// byte[] buf = new byte[BufferLen];
	// int len;
	// // ������ȡ���ļ��е����ݣ�ֱ�������ĳ���Ϊ-1
	// while ((len = in.read(buf)) >= 0) {
	// out.write(buf, 0, len); // �����������ݣ��������ĳ���д�������
	// out.flush();
	// // System.out.println("sendFileData----->" + len);
	// }
	// in.close();
	// System.out.println("sendObjImg() exit");
	// }

}
