package com.zhong.struggle_mvvm.view.activity;

import android.view.View;

import com.struggle.base.base.basics.BaseActivity;
import com.struggle.base.launcher.IOHandler;
import com.struggle.base.utils.print.EscPosCommand;
import com.struggle.base.utils.print.NetPortPrinter;
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
                        EscPosCommand.setOutputStream(printer.getOutputStream());
                        EscPosCommand.selectCommand(EscPosCommand.RESET);
                        EscPosCommand.selectCommand(EscPosCommand.LINE_SPACING_DEFAULT);
                        EscPosCommand.selectCommand(EscPosCommand.ALIGN_CENTER);
                        EscPosCommand.printText(" \n");
                        EscPosCommand.printText("**美食餐厅**\n\n");
                        EscPosCommand.selectCommand(EscPosCommand.DOUBLE_HEIGHT_WIDTH);
                        EscPosCommand.printText("桌号：1号桌\n\n");
                        EscPosCommand.selectCommand(EscPosCommand.NORMAL);
                        EscPosCommand.selectCommand(EscPosCommand.ALIGN_LEFT);
                        EscPosCommand.printText(EscPosCommand.printTwoData("订单编号", "201507161515\n"));
                        EscPosCommand.printText(EscPosCommand.printTwoData("点菜时间", "2016-02-16 10:46\n"));
                        EscPosCommand.printText(EscPosCommand.printTwoData("上菜时间", "2016-02-16 11:46\n"));
                        EscPosCommand.printText(EscPosCommand.printTwoData("人数：2人", "收银员：张三\n"));
                    }
                    printer.reset();
                });
                break;
        }
    }
}