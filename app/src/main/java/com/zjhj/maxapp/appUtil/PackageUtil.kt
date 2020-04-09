package com.zjhj.maxapp.appUtil

import android.app.Activity
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.zjhj.maxapp.utils.L

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
}