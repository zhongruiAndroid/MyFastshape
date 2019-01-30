### 主要功能：通过自定义属性或者代码实现shape效果  

[//]: >![github](https://github.com/zhongruiAndroid/MyFastshape/blob/master/app/src/main/res/drawable/demo.png=320x640)  
<img src="https://github.com/zhongruiAndroid/MyFastshape/blob/newViewHelper/app/src/main/res/drawable/demo.png" alt="image"  width="auto" height="640">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/zhongruiAndroid/MyFastshape/blob/newViewHelper/app/src/main/res/drawable/demoimage.jpg" alt="image"  width="auto" height="640">  
  
  
## [Demo.apk下载](https://github.com/zhongruiAndroid/MyFastshape/blob/newViewHelper/app/release/demo.apk "apk文件")
  
  
**不能有android:background属性,否则自定义属性不生效**  

**MyButton需要手动设置android:background="@null",否则自定义属性设置无效**  

**MyRelativeLayout，MyLinearLayout，MyFrameLayout，MyTextView，MyEditText，MyButton公共属性**  

|属性名                         | 说明                               |                     属性类型                     |                  |
|--------------------------------|------------------------------------|:------------------------------------------------:|------------------|
| drawable_normal<br/>drawable_press | 设置正常状态背景和press状态背景    |                     drawable                     | 覆盖以下所有属性 |
| pressColor                     | 设置press颜色,设置了点击事件才生效 |                       color                      |                  |
| left_line                      | 显示左边框                         |                      boolean                     |                  |
| top_line                       | 显示上边框                         |                      boolean                     |                  |
| right_line                     | 显示右边框                         |                      boolean                     |                  |
| bottom_line                    | 显示底边框                         |                      boolean                     |                  |
| all_line                       | 显示所有边框                       |                      boolean                     | 覆盖上面4个属性  |
| shapeType                      | 设置shape类型,默认rectangle        |      rectangle(默认):矩形<br/>oval:椭圆<br/>line:线      |                  |
| borderWidth                    | 边框宽度                           |                     dimension                    |                  |
| borderColor                    | 边框颜色                           |                       color                      |                  |
| borderDashWidth                | 边框虚线长度                       |                     dimension                    |                  |
| borderDashGap                  | 边框虚线间隔距离                   |                     dimension                    |                  |
| solidColor                     | view填充色(相当于背景色)           |                       color                      |                  |
| topLeftRadius                  | 左上方圆角                         |                     dimension                    |                  |
| topRightRadius                 | 右上方圆角                         |                     dimension                    |                  |
| bottomLeftRadius               | 左下方圆角                         |                     dimension                    |                  |
| bottomRightRadius              | 右下方圆角                         |                     dimension                    |                  |
| radius                         | view圆角                           |                     dimension                    | 覆盖上面4个属性  |
| gradientType                   | 渐变类型                           | linear:线性渐变<br/>radial:放射渐变<br/>sweep:扫描性渐变 |                  |
| gradientAngle                  | 渐变角度                           |        a0,a45,a90,a135<br/>a180,a225,a270,a315       |                  |
| gradientCenterX                | 渐变的X轴起始位置                  |                  范围0~1,默认0.5                 |                  |
| gradientCenterY                | 渐变的Y轴起始位置                  |                  范围0~1,默认0.5                 |                  |
| gradientStartColor             | 渐变起始颜色                       |                       color                      |                  |
| gradientCenterColor            | 渐变中间颜色                       |                       color                      |                  |
| gradientEndColor               | 渐变结束颜色                       |                       color                      |                  |
| gradientRadius                 | 渐变半径                           |         gradientType="radial"适用<br/>默认40         |                  |


<br/>
<br/>
  
  
**MyTextView，MyButton，MyCheckBox，MyRadioButton公共属性**  

|    属性名    | 说明                                                          | 属性类型  |
|----------------------------|---------------------------------------------------------------|---|
| left_width<br/>left_height     | 设置drawableLeft宽高,只设置其中一个属性自动适配另外一个属性   | dimension  |
| top_width<br/>top_height       | 设置drawableTop宽高,只设置其中一个属性自动适配另外一个属性    | dimension  |
| right_width<br/>right_height   | 设置drawableRight宽高,只设置其中一个属性自动适配另外一个属性  | dimension  |
| bottom_width<br/>bottom_height | 设置drawableBottom宽高,只设置其中一个属性自动适配另外一个属性 | dimension  |



<br/>
<br/>

| MyCheckBox,MyRadioButton           | 说明                                                                          | 属性类型 |
|------------------------------------|-------------------------------------------------------------------------------|----------|
| normal_drawable checked_drawable   | 设置normal和check状态的drawable                                               | drawable |
| normal_textColor checked_textColor | 设置normal和check状态的文字颜色                                               | color    |
| drawable_direction                 | 设置button所在方向left,top,right,bottom<br/>设置此属性需要:android:button="@null" |          |

<br/>
<br/>

| MyEditText                       | 说明                                                    | 属性类型  |
|----------------------------------|---------------------------------------------------------|-----------|
| clearIconDrawable                | 设置清除按钮drawable(点击清除内容)                                    | drawable  |
| hiddenClearIcon                  | 设置清除按钮是否隐藏,默认false                          | boolean   |
| clearIcon_width<br/>clearIcon_height | 设置清除按钮宽度,只设置其中一个属性自动适配另外一个属性 | dimension |



<br/>
<br/>  

#### 代码设置  

```java
MyTextView textView=new MyTextView(this);
BaseViewHelper viewHelper = textView.getViewHelper();
//viewHelper.clearAttribute();清除所有属性,按照实际情况使用
viewHelper.setAllLine(true);
viewHelper.setSolidColor(ContextCompat.getColor(this,R.color.white));
viewHelper.setRadius(20);//px
viewHelper.setShapeType(BaseViewHelper.shapeType_rectangle);
viewHelper.complete();//或者textView.complete();


//链式结构
MyLinearLayout linearLayout=new MyLinearLayout(this);
linearLayout.getViewHelper()
//.clearAttribute()
.setAllLine(true)
.setGradientType(BaseViewHelper.gradientType_linear)
.setSolidColor(ContextCompat.getColor(this,R.color.white))
.setRadius(20)//px
.complete();//或者linearLayout.complete();
```
<br/>
<br/>  

  
  [ ![Download](https://api.bintray.com/packages/zhongrui/mylibrary/fastshape/images/download.svg) ](https://bintray.com/zhongrui/mylibrary/fastshape/_latestVersion)  <--版本号
```xml
compile 'com.github:fastshape:版本号看上面'
```
