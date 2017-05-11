### 常见问题

#### 1.Android Studio编译时提示 Error:Execution failed for task ':example:processDebugManifest'.

##### 问题详情: 
Manifest merger failed : uses-sdk:minSdkVersion 14 cannot be smaller than version 16 declared in library [com.xsf:zeusLog:1.0.0] J:\Android_Examples\BaseLibrary\example\build\intermediates\exploded-aar\com.xsf\zeusLog\1.0.0\AndroidManifest.xml

##### 解决方法:

在你项目module的 `AndroidManifest.xml` 文件中添加 `<uses-sdk tools:overrideLibrary="xxx.xxx.xxx"/>`，其中的xxx.xxx.xxx为第三方库包名，如果存在多个库有此异常，则用逗号分割它们， ,如 `<uses-sdk tools:overrideLibrary="com.wb.base"/>` ，这样做是为了项目中的AndroidManifest.xml和第三方库的AndroidManifest.xml合并时可以忽略最低版本限制。

#### 2.继承自BaseFragmentActivity的Activity类跳转过去会应用崩溃

##### 问题详情: 
跳转Activity时如果目标类继承自BaseFragmentActivity会导致应用程序内存泄漏进而引发崩溃

##### 解决方法:
请将base-library的依赖版本设置为 1.0.3 以上,即: `compile 'com.github.codeccc:base-libeary:1.0.3'` .由于堆栈处理原因导致继承BaseFragmentActivity会引发崩溃,故在1.0.3版本移除了BaseFragmentActivity的堆栈处理,继承自BaseActivity的Activity堆栈处理依然保留.

#### 3.无法在Activity,Fragment中直接调用i(obj); json(jsonStr);等日志打印方法

##### 问题详情:
在继承自BaseActivity,BaseFragmentActivity,BaseFragment的类中无法直接调用i(obj); d(obj); e(obj);等方法,该如何使用内置的日志打印呢?

##### 解决方法:
因为对ZeusLog日志调用再封装导致logcat中打印的日志点击方法跳转后无法跳转到真正打印日志的地方,故移除了这些打印日志的调用函数,打印日志的方法变为:
```
//tag可省略不传
ZeusLog.printJsonStr(tag,msg);
ZeusLog.d(tag,msg);
ZeusLog.e(tag,msg);
ZeusLog.i(tag,msg);
```


