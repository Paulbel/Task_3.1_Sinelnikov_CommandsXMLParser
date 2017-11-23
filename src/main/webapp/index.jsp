<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>


<form action="FrontController" method="get">
    <input type="hidden" name="command" value="chooseParser">
    <input type="submit" name="parserType" value="SAX">
    <input type="submit" name="parserType" value="STAX">
    <input type="submit" name="parserType" value="DOM">
</form>

</body>
</html>
