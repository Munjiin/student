<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-12
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>가익 '있'는 가익팀 / Do you understand?</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">회원가입</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="아이디" name="ID" type="ID"  value="${member.id}">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="비밀번호 (숫자만 입력)" name="password" type="password" value="${member.password}">
                            </div>
                                     <div class="form-group">
                                <input class="form-control" placeholder="비밀번호 확인 (숫자만 입력)" name="password2" type="password" value="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="닉네임" name="Nickname" type="Nickname" value="${member.name}">
                            </div>
                               <div class="form-group">
                                <input class="form-control" placeholder="IP주소 마지막 두자리를 입력하세요" name="Seatnum" type="Seatnum" value="${member.seatnum}">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            </fieldset>
                            </form>
                            

                            
                                <div style="text-align: center">
                                <form method="post">
                                    <a href="signup" class="btn btn-outline btn-success">회원가입</a>
                                </form>
                                       <a href="/index.jsp" class="btn btn-outline btn-primary">취소</a>
                                </div>
                        
                    
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/dist/js/sb-admin-2.js"></script>

</body>

</html>

<%@ include file="includes/footer.jsp" %>
