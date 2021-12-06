package com.struggle.base.base.plugins;

import android.app.Activity;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.struggle.base.launcher.TxToast;
import com.struggle.base.utils.PermissionsUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 邓建忠
 * @CreateTime 2021/12/6 15:34
 * @Description 动态授权插件
 */
public interface PermissionPlugin {
    Activity getActivity();

    /**
     * 请求权限
     *
     * @param permissions
     * @param iPermissions
     */
    default void requestPermission(String permissions, PermissionsUtil.IPermissions iPermissions) {
        requestPermission(Arrays.asList(permissions), iPermissions);
    }

    /**
     * 请求权限
     *
     * @param permissions
     * @param iPermissions
     */
    default void requestPermission(String[] permissions, PermissionsUtil.IPermissions iPermissions) {
        requestPermission(Arrays.asList(permissions), iPermissions);
    }

    /**
     * 请求权限
     *
     * @param permissions
     * @param iPermissions
     */
    default void requestPermission(List<String> permissions, PermissionsUtil.IPermissions iPermissions) {
        if (XXPermissions.isHasPermission(getActivity(), permissions)) {
            iPermissions.onSuccess(true);
        } else {
            XXPermissions
                    .with(getActivity())
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
                                XXPermissions.gotoPermissionSettings(getActivity());
                            } else {
                                TxToast.showToast("部分权限被拒绝");
                            }
                        }
                    });
        }
    }
}
