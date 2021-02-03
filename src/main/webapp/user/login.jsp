<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 08/10/2020
  Time: 10:18 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {
            border: 3px solid #f1f1f1;
            width: 60%;
            margin-left: 20%;
            margin-top: 4rem;

        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
            .cancelbtn {
                width: 100%;
            }
        }
        h2{
            text-align: center;
        }
    </style>
</head>
<body>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form action="/user?action=loginAdmin" method="post">
    <h2>Login</h2>
    <div class="imgcontainer">
    </div>

    <div class="container">
        <label ><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="userName" required>

        <label ><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>
        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
</form>

</body>
</html>