package com.struggle.base.utils;

import android.app.Activity;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.struggle.base.launcher.TxToast;

import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/11 16:16
 * @Description 动态授权工具类
 */
public class PermissionsUtil {
    public static void request(Activity activity, List<String> permissions, IPermissions iPermissions) {
        if (XXPermissions.isHasPermission(activity, permissions)) {
            iPermissions.onSuccess(true);
        } else {
            XXPermissions
                    .with(activity)
                    .permission(permissions)
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            iPermissions.onSuccess(isAll);
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                TxToast.showToast("被永久拒绝授权，请手动授予权限");
                                XXPermissions.gotoPermissionSettings(activity);
                            } else {
                                TxToast.showToast("部分权限被拒绝");
                            }
                        }
                    });
        }
    }

    public interface IPermissions {
        /**
         * 权限组授权成功
         *
         * @param b false：部分权限被拒绝  true：所有权限组被授予
         */
        void onSuccess(boolean b);
    }
}
