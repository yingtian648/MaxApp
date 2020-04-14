package com.zjhj.maxapp.appcopy

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Environment
import com.zjhj.maxapp.utils.FileUtil
import com.zjhj.maxapp.utils.L
import java.io.File

/**
 * CreateTime 2020/4/9 14:45
 * Author LiuShiHua
 * Description：
 */
class PackageUtil(val context: Activity) {
    val pm = context.packageManager

    fun getAppList(): MutableList<AppInfo> {
        var results = mutableListOf<AppInfo>()
        val packages: List<PackageInfo> = pm.getInstalledPackages(0);
        for (packageInfo in packages) {
            // 判断系统/非系统应用
            if (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM === 0) {// 非系统应用
                results.add(
                    AppInfo(
                        packageInfo.applicationInfo.loadLabel(pm).toString(),
                        packageInfo.packageName,
                        packageInfo.applicationInfo.loadIcon(pm),
                        packageInfo.versionName,
                        packageInfo.versionCode
                    )
                )
            } else {// 系统应用
//                L.d("系统应用:::" + packageInfo.packageName)+"\t"+packageInfo.applicationInfo.loadLabel(pm).toString()
            }
        }
        return results
    }

    fun getApkPath(packageName: String): String? {
        var appDir: String? = null
        try {
            //通过包名获取程序源文件路径
            appDir = pm.getApplicationInfo(packageName, 0).sourceDir;
            L.d("app安装包路径：$appDir")
        } catch (e: PackageManager.NameNotFoundException) {
            L.e("获取包【" + packageName + "】路径失败：" + e.message)
        }
        return appDir;
    }

    /**
     * 复制APK弹框
     */
    fun showCopyApkDialog(context: Context, appInfo: AppInfo) {
        var savePath = Environment.getExternalStorageDirectory()?.absolutePath +
                File.separator +
                appInfo.appName + ".apk"
        AlertDialog.Builder(context).setTitle("复制APK提示")
            .setMessage("您正在复制:" + appInfo.appName + "\n复制到：手机SD存储目录下/" + appInfo.appName + ".apk" + "\n确定复制吗？")
            .setPositiveButton("复制APK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    val apkFilePath = getApkPath(appInfo.packageName)
                    FileUtil.copyFileN(context, apkFilePath, savePath)
                }
            })
            .setNegativeButton("取消", null)
            .show()
    }
}