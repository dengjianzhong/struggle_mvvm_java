package com.struggle.base.utils.print;

import android.text.TextUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author 邓建忠
 * @CreateTime 2021/10/13 14:01
 * @Description 网口打印机
 */
public class NetPortPrinter {
    private static NetPortPrinter printer;
    private Socket socket;
    private String host;
    private OutputStream outputStream;
    /*打印机固定通讯端口*/
    private int port = 9100;

    public static NetPortPrinter getInstance() {
        if (printer == null) {
            synchronized (NetPortPrinter.class) {
                if (printer == null) {
                    printer = new NetPortPrinter();
                }
            }
        }
        return printer;
    }

    private NetPortPrinter() {
        socket = new Socket();
    }

    /**
     * 设置网口打印机地址
     *
     * @param host 网口打印机地址
     */
    public NetPortPrinter setHostAddress(String host) {
        this.host = host;

        return this;
    }

    /**
     * 设置网口打印机地址
     *
     * @param host 网口打印机地址
     * @param port 网口打印机端口号
     */
    public NetPortPrinter setHostAddress(String host, int port) {
        this.host = host;
        this.port = port;

        return this;
    }

    /**
     * 连接网口打印机
     *
     * @return false :连接失败、true：连接成功
     */
    public boolean connect() {
        if (TextUtils.isEmpty(host)) {
            new RuntimeException("未打印机的IP地址");
        }
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        try {
            socket.connect(socketAddress);
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    /**
     * 获取打印机写入流通道
     *
     * @return
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * 关闭socket(断开打印机的连接)
     */
    public void close() {
        if (socket.isConnected() && socket.isBound()) {
            try {
                if (!socket.isInputShutdown()) {
                    socket.shutdownInput();
                }
                if (!socket.isOutputShutdown()) {
                    socket.shutdownOutput();
                }
                outputStream.flush();
                outputStream.close();

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重置与网口打印机的连接
     */
    public NetPortPrinter reset() {
        close();
        this.socket = new Socket();

        return this;
    }
}
