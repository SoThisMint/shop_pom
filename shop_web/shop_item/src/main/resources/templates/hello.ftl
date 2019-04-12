<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

    hello,${key.gname}
    <hr />
    <#if age < 18>
        未成年
        <#elseif (age>=18) >
        成年
    </#if>
    <#if (age >= 18)>
        成年
    </#if>
    <hr />
    <#list goodsList as goods>
        ${goods.gname}
    </#list>
    <hr />
    ${now?date}
    <hr>
    ${now?datetime}
    <hr>
    ${now?time}
    <hr>
    ${now?string("yyyy年MM月dd日 HH分mm分ss秒SS毫秒")}
</body>
</html>