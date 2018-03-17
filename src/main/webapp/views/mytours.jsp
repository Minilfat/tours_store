<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
    <title>Available tours</title>

    <style>
        html, body {height: 100%}
        body {
            margin: auto;
            background: url('https://images.pexels.com/photos/460672/pexels-photo-460672.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb') no-repeat;
            background-size: cover;
        }
        .navigator {
            width: 100%;
            min-width: 440px;
            min-height: 70px;
            background-color: #31302B;
            opacity: 0.95;
        }
        .navigator .item {
            text-decoration:none;
            color: white;
            padding: 23px 18px;
            font-size: 1.2em;
        }
        .left{float: left}
        .right {float: right}
        .item:hover {color: #9fcdff}
        .navigator .item:nth-child(1) {background-color: black;}

        .tours_table {
            width:700px;
            height: 400px;
            position: fixed;
            top:55%;
            left:50%;
            transform: translate(-50%, -50%);
        }

        .tours_table thead{
            display: block;
            width: 700px;
            overflow: auto;
            color: #fff;
            background: #31302B;
        }

        .tours_table tbody {
            display: block;
            width: 700px;
            height: 450px;
            overflow: auto;
            opacity: 0.85;
            background: rgb(230, 213, 215);
        }

        .tours_table th,td {
            width: 2%;
            padding: .5em 1em;
            text-align: left;
            vertical-align: top;
            max-width: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            font-weight: 300;
        }

        .tours_table tbody tr:hover {
            background-color: rgb(80, 94, 80);
        }
        .tours_table tbody a {
            text-decoration: none;
            color: black;
            font-weight: 900;
        }
    </style>
</head>
<body>
<div class="navigator">
    <a class="item left" href="${pageContext.request.contextPath}/home">Home</a>
    <a class="item left" href="${pageContext.request.contextPath}/tours">Tours</a>
    <a class="item left" href="${pageContext.request.contextPath}/tours/my">My booked tours</a>
    <a class="item right" href="${pageContext.request.contextPath}/logout">Log out</a>
</div>

<table cellspacing="0" class="tours_table">
    <thead>
    <tr>
        <th>City</th>
        <th>Price</th>
        <th>Starts</th>
        <th>Ends</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tour" items="${mylist}">
        <tr>
            <td><c:out value="${tour.city}"  /></td>
            <td><c:out value="${tour.price}" /></td>
            <td><c:out value="${tour.from}" /></td>
            <td><c:out value="${tour.to}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
