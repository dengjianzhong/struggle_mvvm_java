package com.zhong.struggle_mvvm.view.activity;

import android.view.View;

import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.launcher.IOHandler;
import com.struggle.base.utils.print.NetPortPrinter;
import com.struggle.base.utils.print.PrintHelper;
import com.zhong.struggle_mvvm.R;
import com.zhong.struggle_mvvm.databinding.ActivityFramework7Binding;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/6 17:39
 * @Description 网口打印机
 */
public class FrameworkActivity7 extends BaseActivity<ActivityFramework7Binding> {

    @Override
    protected void initData() {
        super.initData();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                NetPortPrinter.getInstance().setHostAddress("192.168.1.138");
                break;
            case R.id.button2:
                IOHandler.postWork(() -> {
                    NetPortPrinter printer = NetPortPrinter.getInstance();
                    if (printer.connect()) {
                        PrintHelper.setOutputStream(printer.getOutputStream());
                        PrintHelper.selectCommand(PrintHelper.RESET);
                        PrintHelper.selectCommand(PrintHelper.LINE_SPACING_DEFAULT);
                        PrintHelper.selectCommand(PrintHelper.ALIGN_CENTER);
                        PrintHelper.printText(" \n");
                        PrintHelper.printText("**美食餐厅**\n\n");
                        PrintHelper.selectCommand(PrintHelper.DOUBLE_HEIGHT_WIDTH);
                        PrintHelper.printText("桌号：1号桌\n\n");
                        PrintHelper.selectCommand(PrintHelper.NORMAL);
                        PrintHelper.selectCommand(PrintHelper.ALIGN_LEFT);
                        PrintHelper.printText(PrintHelper.printTwoData("订单编号", "201507161515\n"));
                        PrintHelper.printText(PrintHelper.printTwoData("点菜时间", "2016-02-16 10:46\n"));
                        PrintHelper.printText(PrintHelper.printTwoData("上菜时间", "2016-02-16 11:46\n"));
                        PrintHelper.printText(PrintHelper.printTwoData("人数：2人", "收银员：张三\n"));
                    }
                    printer.reset();
                });
                break;
        }
    }
}