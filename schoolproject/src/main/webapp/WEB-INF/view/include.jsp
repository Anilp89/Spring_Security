<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>

<%-- <%@ page import="com.rc.constants.RCConstants"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="Java" import="com.rc.util.*, com.rc.ec.domain.*, java.util.* "%>
<%@ page language="Java" import="com.rc.ec.web.security.* "%> 
<%@ page language="Java" import="com.rc.util.security.*"%>
 --%>
