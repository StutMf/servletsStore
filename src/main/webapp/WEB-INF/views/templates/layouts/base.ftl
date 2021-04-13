<#macro mainMacro title, css=[], scripts=[]>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="/css/" rel="stylesheet" id="bootstrap-css">
        <link rel='stylesheet' href='/css/bootstrap.min.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/settings.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/swatches-and-photos.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/prettyPhoto.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/jquery.selectBox.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/font-awesome.min.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Karla:400,400italic,700,700italic%7CCrimson+Text:400,400italic,600,600italic,700,700italic' type='text/css' media='all'/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:400,700">
        <link rel='stylesheet' href='/css/elegant-icon.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/style.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/commerce.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/custom.css' type='text/css' media='all'/>
        <link rel='stylesheet' href='/css/magnific-popup.css' type='text/css' media='all'/>
        <#list css as style>
            <link rel="stylesheet" href="/css/${style}">
        </#list>
        <title>${title}</title>
    </head>
    <body>
    <#include "header.ftl">
    <#nested>
    <#include "footer.ftl">
    <script type='text/javascript' src='/js/jquery.js'></script>
    <script type='text/javascript' src='/js/fontawesome.js'></script>
    <script type='text/javascript' src='/js/bootstrap.min.js'></script>
    <#list scripts as script>
        <script src="/js/${script}"></script>
    </#list>
    </body>
    </html>
</#macro>