package com.personal;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTPUtil {

    private final static Log logger = LogFactory.getLog(FTPUtil.class);
    public static FTPClient ftpClient;

    /**
     * FTPClient对象初始化
     * @return
     */
    static {
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(Constant.HOST, Constant.PORT);// 连接FTP服务器
            ftpClient.login(Constant.ACCOUNT, Constant.PASSWORD);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                logger.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置。");
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     * Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
     *
     * @param path     FTP服务器保存目录
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String path, String filename, InputStream input) {
        boolean success = false;
        try {
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(filename, input);

            input.close();
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * 批量上传文件
     *
     * @param ftpPath
     * @return
     */
    public static boolean uploadFiles(String ftpPath, String path) {
        File file = new File(path);
        boolean success = false;
        File[] files = file.listFiles();
        try {
            ftpClient.makeDirectory(ftpPath);
            ftpClient.changeWorkingDirectory(ftpPath);
            for (File file2 : files) {
                //文件写入流
                InputStream is = new FileInputStream(file2.getAbsolutePath());
                ftpClient.storeFile(file2.getName(), is);
                is.close();
                success = true;
                logger.info("文件:" + file2.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            //连接关闭
            close();
        }
        return success;
    }

    /**
     * 从FTP服务器下载文件
     *
     * @param ftpPath FTP服务器中文件所在路径 格式： ftptest/aa
     */
    public static Map<String, List<InputStream>> downloadFtpFile(String ftpPath) {
        try {
            Map<String, List<InputStream>> inputStreamMap = new HashMap<>();
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            //多个输入流
            List<InputStream> shList = new ArrayList<>();
            List<InputStream> orderList = new ArrayList<>();
            InputStream is;
            String[] fs = ftpClient.listNames();
            for (String ff : fs) {
                //转化前文件名
                System.out.println("Front" + new String(ff.getBytes("iso-8859-1"), "GB2312"));
                if (StringUtils.contains(ff, "SH")) {
                    is = ftpClient.retrieveFileStream(ff);
                    shList.add(is);
                    ftpClient.completePendingCommand(); //处理多个文件
                } else {
                    is = ftpClient.retrieveFileStream(ff);
                    orderList.add(is);
                    ftpClient.completePendingCommand(); //处理多个文件
                }
            }
            inputStreamMap.put(Constant.SH_ORDER, shList);
            inputStreamMap.put(Constant.ORDER, orderList);
            //close();
            return inputStreamMap;
        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            logger.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误。");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除ftp文件
     *
     * @param srcFname
     * @return
     */
    public static boolean removeFiles(String srcFname) {
        boolean flag = false;
        try {
            String[] fs = getFileNameList(srcFname);
            for (String ff : fs) {
                flag = ftpClient.deleteFile(ff);
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }


    /**
     * 获取目录下所有的文件名称
     *
     * @param filePath 指定的目录
     * @return 文件列表, 或者null
     */
    public static FTPFile[] getFileList(String filePath) {
        try {
            ftpClient.changeWorkingDirectory(filePath);
            FTPFile[] files = ftpClient.listFiles();
            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回FTP目录下的文件列表
     *
     * @param pathName
     * @return
     */
    public static String[] getFileNameList(String pathName) {
        try {
            //更换目录到当前目录
            ftpClient.changeWorkingDirectory(pathName);
            return ftpClient.listNames();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭链接
     */
    public static void close() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            System.out.println("成功关闭连接，服务器");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
