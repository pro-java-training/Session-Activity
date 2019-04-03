<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.codve.PageVisit" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!doctype html>
<html lang="en">
<head>
    <title>Session Activity Tracker</title>
</head>
<body>
    <h2>Session Properties</h2>
    Session id: <%= session.getId() %><br/> <%-- 获取sessionId --%>
    is new : <%= session.isNew() %><br/> <%-- 判断session是否是新创建的 --%>
    Session created: <%= format.format(new Date(session.getCreationTime())) %><br/>
    <h2>Activities of This Session</h2>
    <%
        @SuppressWarnings("unchecked")
        Vector<PageVisit> visits =
            (Vector<PageVisit>) session.getAttribute("activity");
        for (PageVisit visit : visits) {
            out.print(visit.getRequest() + " ");
            if (visit.getIpAddress() != null) {
                out.print("IP: " + visit.getIpAddress().getHostAddress() + " ");
            }
            out.print("Date: " + format.format(visit.getEnteredTimestamp()) + " ");
            if (visit.getLeftTimestamp() != null) {
                out.print("stayed for " +
                        (visit.getLeftTimestamp() - visit.getEnteredTimestamp()) / 1000
                        + " seconds");
            }
            out.print("<br/><br/>");
        }
    %>
</body>
</html>
