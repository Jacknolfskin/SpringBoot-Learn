package com.personal;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ReadText {

    private static StringBuffer sbOnline = new StringBuffer();
    private static StringBuffer sbOffLine = new StringBuffer();
    private static String result = "";

    public static void main(String[] args) {
        try {
            //FTPUtil.uploadFiles("/orderText/","D:\\test\\front");
            Map<String, List<InputStream>> inputStreamMap = FTPUtil.downloadFtpFile(Constant.FRONT_URL);
            //Order
            readFile(inputStreamMap.get(Constant.ORDER), Constant.ORDER);
            //ShOrder
            readFile(inputStreamMap.get(Constant.SH_ORDER), Constant.SH_ORDER);
            //转化后文件上传FTP
            FTPUtil.uploadFiles(Constant.BACK_URL, Constant.MOME_URL);
            //转化完成删除原文件
            FTPUtil.removeFiles(Constant.FRONT_URL);
            //转化后文件名
            /*FTPFile[] list = FTPUtil.getFileList("/orderText/");
            for (FTPFile file : list) {
                if (file.isFile()) {
                    System.out.println("Back" + new String(file.getName().getBytes("iso-8859-1"), "GB2312"));
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 逐行读取文本
     */
    private static void readFile(List<InputStream> inputStream, String code) {
        try {
            PrintWriter writerOnline = null;
            PrintWriter writerCancel = null;
            String oneline = "";
            String cancel = "";
            Vector<InputStream> v = new Vector<>();
            for (InputStream ips : inputStream) {
                v.add(ips);
            }
            Enumeration<InputStream> enumeration = v.elements();
            SequenceInputStream sis = new SequenceInputStream(enumeration);
            BufferedReader br = new BufferedReader(new InputStreamReader(sis));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                getResult(line);
            }
            //判断文件类型
            if (code.equals(Constant.ORDER)) {
                oneline = "Orders_online.txt";
                cancel = "Orders_cancel.txt";
            } else if (code.equals(Constant.SH_ORDER)) {
                oneline = "SH_Orders_online.txt";
                cancel = "SH_Orders_cancel.txt";
            }
            writerOnline = new PrintWriter(Constant.MOME_URL + oneline, "UTF-8");
            writerCancel = new PrintWriter(Constant.MOME_URL + cancel, "UTF-8");
            writerOnline.println(sbOnline.toString());
            writerCancel.println(sbOffLine.toString());
            writerOnline.close();
            writerCancel.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成结果
     *
     * @param line
     * @return
     */
    private static void getResult(String line) {

        //套餐替换
        if (StringUtils.containsAny(line, Constant.MOVE_FIVE_MUSIC, Constant.CARD_TEN_MUSIC)) {
            result = StringUtils.replaceEach(line, Constant.twoList, new String[]{"2", "2"});
        } else if (StringUtils.contains(line, Constant.CARD_TWENTY_MUSIC)) {
            result = StringUtils.replace(line, Constant.CARD_TWENTY_MUSIC, "3");
        } else if (StringUtils.contains(line, Constant.AB_MUSIC)) {
            result = StringUtils.replace(line, Constant.AB_MUSIC, "4");
        } else {
            result = StringUtils.replaceEach(line, Constant.oneList, new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"});
        }

        //运营商替换
        if (StringUtils.contains(result, Constant.MOVE)) {
            result = StringUtils.replace(result, Constant.MOVE, "1");
        } else if (StringUtils.contains(result, Constant.UNICOM)) {
            result = StringUtils.replace(result, Constant.UNICOM, "2");
        } else if (StringUtils.contains(result, Constant.TELECOM)) {
            result = StringUtils.replace(result, Constant.TELECOM, "3");
        }

        //空值替换
        if (StringUtils.contains(result, Constant.NULL)) {
            result = StringUtils.replace(result, Constant.NULL, "");
        }

        //在网或退订替换为空
        if (StringUtils.contains(result, Constant.ONLINE)) {
            result = StringUtils.replace(result, Constant.ONLINE, "");
            sbOnline.append(result + System.lineSeparator());
        } else {
            result = StringUtils.replace(result, Constant.OFFONLINE, "");
            sbOffLine.append(result + System.lineSeparator());
        }
    }


    /**
     * 一次性读取
     *
     * @param fileName
     * @return
     */
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
