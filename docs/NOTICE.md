### 常见问题

#### 1.Android Studio编译时提示 Error:Execution failed for task ':example:processDebugManifest'.

##### 问题详情: 
Manifest merger failed : uses-sdk:minSdkVersion 14 cannot be smaller than version 16 declared in library [com.xsf:zeusLog:1.0.0] J:\Android_Examples\BaseLibrary\example\build\intermediates\exploded-aar\com.xsf\zeusLog\1.0.0\AndroidManifest.xml

##### 解决方法:

在你项目module的 `AndroidManifest.xml` 文件中添加 `<uses-sdk tools:overrideLibrary="xxx.xxx.xxx"/>`，其中的xxx.xxx.xxx为第三方库包名，如果存在多个库有此异常，则用逗号分割它们， ,如 `<uses-sdk tools:overrideLibrary="com.wb.base"/>` ，这样做是为了项目中的AndroidManifest.xml和第三方库的AndroidManifest.xml合并时可以忽略最低版本限制。

