# generate-code
spring+springMVC+mybatis3以及H+ UI框架 相关的编辑和列表代码生成器，完全100%自己写的，可以定制

使用方法:<br/>
找到Main.java<br/>
![image](https://github.com/quanzhian/generate-code/blob/master/QQ%E6%88%AA%E5%9B%BE20160711130703.png)<br/>
设置类GenerateConfig值<br/>
![image](https://github.com/quanzhian/generate-code/blob/master/QQ%E6%88%AA%E5%9B%BE20160711130742.png)
<br/>
<h1>2016-07-22</h1><br/>
1.调整整体生成代码，使其容易扩展<br/>
2.去除BizType枚举，添加layer类，存储需要生成的模板和路径<br/>
3.添加nodejs model和route模板生成，配套使用nodejs+sequelize+express+arttemplate+mysql<br/>
