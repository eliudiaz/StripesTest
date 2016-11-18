<%@ include file="/WEB-INF/jsp/taglibs.jsp" %>
<s:layout-definition>
    <!doctype html>
    <html>
        <head>
            <title>${title}</title>
        <s:layout-component name="head" />
        </head>
        <body>
            <div id="main">
                <s:layout-component name="body" />
            </div>
        </body>
    </html>
</s:layout-definition>