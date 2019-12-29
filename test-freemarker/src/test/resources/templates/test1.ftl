<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}
<br/>
遍历数据模型中的list学生信息(数据模型中的名称为stus)
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>金额</td>
        <td>出生日期</td>
    </tr>
    <#--判断某变量是否为空用 "??"
    用法为:Variable??,如果该变量存在,返回true,否则返回false-->
    <#if stus??>
    <#list stus as stu>
        <tr>
            <td>${stu_index+1}</td>
            <td <#if stu.name == '小明'>style="background: blueviolet" </#if>>${stu.name}</td>
            <td>${stu.age}</td>
             <#--   > 写成 gt  >= 写成 gte  < 写成 lt  <= 写成 lte  -->
            <td <#if stu.money gt 300>style="background: crimson" </#if>>${stu.money}</td>
            <td>${stu.birthday?date}</td>
        </tr>
    </#list>
    <br/>
    学生的个数: ${stus?size}
    </#if>
</table>
<br/>
遍历数据模型中的stuMap(map数据),第一种方法: 在中括号中填写map的key
                              第二种方法: 在map后边直接加"点 key"
<br/>
姓名: ${(stuMap['stu1'].name)!""}<br/>
年龄: ${(stuMap['stu1'].age)!""}<br/>
姓名: ${(stuMap.stu1.name)!""}<br/>
年龄: ${(stuMap.stu1.age)!""}<br/>
遍历map中的key.stuMap?keys就是key列表(是一个list列表)<br/>
<#list stuMap?keys as k>
姓名: ${stuMap[k].name}<br/>
年龄: ${stuMap[k].age}<br/>
</#list>
<br/>
${point?c}
<br/>
<#assign text="{'bank':'工商银行','account':'1010198020191215'}"/>
<#assign data=text?eval />
开户行: ${data.bank}  账号: ${data.account}
</body>
</html>