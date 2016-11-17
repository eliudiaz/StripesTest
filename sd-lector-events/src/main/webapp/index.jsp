<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="jquerysee.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <script type="text/javascript">
            var sse = $.SSE('http://localhost:8080/sd-lector-events/events?sessionid=123', {
                onMessage: function (e) {
                    console.log("Message");
                    console.log(e);
                }
            });
            sse.start();
        </script>
    </body>
</html>
